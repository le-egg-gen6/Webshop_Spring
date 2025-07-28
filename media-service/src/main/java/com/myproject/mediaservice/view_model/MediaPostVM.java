package com.myproject.mediaservice.view_model;

import com.myproject.commonlibs.annotation.validation.FileTypeValid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nguyenle
 * @since 2:10 PM Thu 7/24/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaPostVM {

	private String caption;

	@FileTypeValid(
		allowedTypes = {"image/png", "image/jpeg", "imag/gif"},
		message = "File type not allowed. Allowed types are: JPEG, PNG, GIF"
	)
	private MultipartFile multipartFile;

	private String fileNameOverride;
}
