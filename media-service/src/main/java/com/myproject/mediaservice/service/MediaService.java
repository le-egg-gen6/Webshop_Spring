package com.myproject.mediaservice.service;

import com.myproject.mediaservice.dto.MediaDTO;
import com.myproject.mediaservice.model.Media;
import com.myproject.mediaservice.view_model.MediaPostVM;
import com.myproject.mediaservice.view_model.MediaVM;
import java.util.List;

/**
 * @author nguyenle
 * @since 2:17 PM Thu 7/24/2025
 */
public interface MediaService {

	Media saveMedia(MediaPostVM mediaPostVm);

	MediaVM getMediaById(Long id);

	void removeMedia(Long id);

	MediaDTO getFile(Long id, String fileName);

	List<MediaVM> getMediaByIds(List<Long> ids);

}
