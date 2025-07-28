package com.myproject.ratingservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
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

	private String content;

	private int ratingStar;

	private Long productId;

	private String productName;

	private String lastName;

	private String firstName;

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
