package com.myproject.mediaservice.service.impl;

import com.myproject.commonlibs.config.ServiceUrlConfig;
import com.myproject.commonlibs.exception.NotFoundException;
import com.myproject.mediaservice.dto.MediaDTO;
import com.myproject.mediaservice.mapper.MediaVMMapper;
import com.myproject.mediaservice.model.Media;
import com.myproject.mediaservice.repository.FileSystemRepository;
import com.myproject.mediaservice.repository.MediaRepository;
import com.myproject.mediaservice.service.MediaService;
import com.myproject.mediaservice.view_model.MediaPostVM;
import com.myproject.mediaservice.view_model.MediaVM;
import com.myproject.mediaservice.view_model.NoFileMediaVM;
import java.io.InputStream;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author nguyenle
 * @since 2:17 PM Thu 7/24/2025
 */
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

	private final ServiceUrlConfig serviceUrlConfig;

	private final FileSystemRepository fileSystemRepository;
	private final MediaRepository mediaRepository;

	private final MediaVMMapper mediaVMMapper;

	@Override
	@SneakyThrows
	public Media saveMedia(MediaPostVM mediaPostVm) {
		Media media = new Media();
		String mediaType = mediaPostVm.getMultipartFile().getContentType();
		byte[] content = mediaPostVm.getMultipartFile().getBytes();

		media.setCaption(mediaPostVm.getCaption());
		media.setMediaType(mediaType);

		if (StringUtils.hasText(mediaPostVm.getFileNameOverride())) {
			media.setFileName(mediaPostVm.getFileNameOverride().trim());
		} else {
			media.setFileName(mediaPostVm.getMultipartFile().getOriginalFilename());
		}
		String filePath = fileSystemRepository.persistFile(
			media.getFileName(),
			content
		);
		media.setFilePath(filePath);

		return mediaRepository.save(media);
	}

	@Override
	public MediaVM getMediaById(Long id) {
		NoFileMediaVM noFileMediaVm = mediaRepository.findByIdWithoutFileInReturn(id);
		if (noFileMediaVm == null) {
			return null;
		}
		String url = getMediaUrl(noFileMediaVm.getId(), noFileMediaVm.getFileName());

		return MediaVM.builder()
			.id(noFileMediaVm.getId())
			.caption(noFileMediaVm.getCaption())
			.mediaType(noFileMediaVm.getMediaType())
			.fileName(noFileMediaVm.getFileName())
			.url(url)
			.build();
	}

	@Override
	public void removeMedia(Long id) {
		NoFileMediaVM noFileMediaVm = mediaRepository.findByIdWithoutFileInReturn(id);
		if (noFileMediaVm == null) {
			throw new NotFoundException(String.format("Media %s is not found", id));
		}
		mediaRepository.deleteById(id);
	}

	@Override
	public MediaDTO getFile(Long id, String fileName) {
		Media media = mediaRepository.findById(id).orElse(null);
		if (media == null || !fileName.equalsIgnoreCase(media.getFileName())) {
			return new MediaDTO();
		}
		MediaType mediaType = MediaType.valueOf(media.getMediaType());
		InputStream fileContent = fileSystemRepository.getFile(media.getFilePath());
		return MediaDTO.builder()
			.mediaType(mediaType)
			.content(fileContent)
			.build();
	}

	@Override
	public List<MediaVM> getMediaByIds(List<Long> ids) {
		return mediaRepository.findAllById(ids).stream()
			.map(mediaVMMapper::toViewModel)
			.peek(media -> {
				String url = getMediaUrl(media.getId(), media.getFileName());
				media.setUrl(url);
			}).toList();
	}

	private final String PATH_PATTERN = "/medias/%1$s/file/%2$s";

	private String getMediaUrl(Long id, String fileName) {
		return UriComponentsBuilder.fromUriString(serviceUrlConfig.getServiceUrl())
			.path(String.format(PATH_PATTERN, id, fileName))
			.build().toUriString();
	}
}
