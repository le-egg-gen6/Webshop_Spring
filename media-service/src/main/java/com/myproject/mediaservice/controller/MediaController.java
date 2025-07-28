package com.myproject.mediaservice.controller;

import com.myproject.commonlibs.annotation.validation.RegexMatchMode;
import com.myproject.commonlibs.constant.StringRegexConstant;
import com.myproject.commonlibs.view_model.ErrorVM;
import com.myproject.mediaservice.dto.MediaDTO;
import com.myproject.mediaservice.model.Media;
import com.myproject.mediaservice.service.MediaService;
import com.myproject.mediaservice.view_model.MediaPostVM;
import com.myproject.mediaservice.view_model.MediaVM;
import com.myproject.mediaservice.view_model.NoFileMediaVM;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 4:38 PM Thu 7/24/2025
 */
@RestController
@RequiredArgsConstructor
public class MediaController {

	private final MediaService mediaService;

	@PostMapping(path = "/medias", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = NoFileMediaVM.class))),
		@ApiResponse(responseCode = "400", description = "Bad request",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))})
	public ResponseEntity<?> create(
		@ModelAttribute @Valid MediaPostVM mediaPostVM
	) {
		Media media = mediaService.saveMedia(mediaPostVM);
		NoFileMediaVM noFileMediaVm = NoFileMediaVM.builder()
			.id(media.getId())
			.fileName(media.getFileName())
			.caption(media.getCaption())
			.mediaType(media.getMediaType())
			.build();
		return ResponseEntity.ok().body(noFileMediaVm);
	}

	@DeleteMapping(path = "/medias/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Deleted",
			content = @Content(schema = @Schema(implementation = MediaVM.class))),
		@ApiResponse(responseCode = "400", description = "Media not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))
	})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		mediaService.removeMedia(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/medias/{id}")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = MediaVM.class))),
		@ApiResponse(responseCode = "404", description = "Media not found",
			content = @Content(schema = @Schema(implementation = ErrorVM.class)))
	})
	public ResponseEntity<MediaVM> get(@PathVariable @Min(1) Long id) {
		MediaVM media = mediaService.getMediaById(id);
		return ResponseEntity.ok(media);
	}

	@GetMapping("/medias")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ok",
			content = @Content(schema = @Schema(implementation = MediaVM.class)))})
	public ResponseEntity<?> getByIds(@RequestParam @NotEmpty List<Long> ids) {
		List<MediaVM> medias = mediaService.getMediaByIds(ids);
		if (medias.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(medias);
	}

	@Hidden
	@GetMapping("/medias/{id}/file/{fileName}")
	public ResponseEntity<InputStreamResource> getFile(
		@PathVariable @Min(1) Long id,
		@PathVariable String fileName
	) {
		MediaDTO mediaDto = mediaService.getFile(id, fileName);

		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
			.contentType(mediaDto.getMediaType())
			.body(new InputStreamResource(mediaDto.getContent()));
	}

}
