package com.myproject.mediaservice.dto;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;

/**
 * @author nguyenle
 * @since 2:05 PM Thu 7/24/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDTO {
	private InputStream content;
	private MediaType mediaType;
}
