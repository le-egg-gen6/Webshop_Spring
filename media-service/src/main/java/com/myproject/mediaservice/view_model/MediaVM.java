package com.myproject.mediaservice.view_model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 2:09 PM Thu 7/24/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaVM {

	private Long id;

	private String caption;

	private String fileName;

	private String mediaType;

	private String url;
}
