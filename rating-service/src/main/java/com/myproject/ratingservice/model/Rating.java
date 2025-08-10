package com.myproject.ratingservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 3:28 PM Sat 7/26/2025
 */
@Entity
@Table(
	name = "rating"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, name = "content")
	private String content;

	private int ratingStar;

	private Long productId;

	private String productName;

	private String firstName;

	private String lastName;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Rating)) {
			return false;
		}
		return id != null && id.equals(((Rating) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
