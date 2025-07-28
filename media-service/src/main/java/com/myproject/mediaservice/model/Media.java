package com.myproject.mediaservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 9:46 AM Thu 7/24/2025
 */
@Entity
@Table(
	name = "media"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String caption;

	private String fileName;

	private String filePath;

	private String mediaType;

}
