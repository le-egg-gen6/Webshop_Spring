package com.myproject.productservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 2:38 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_option"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductOption)) {
			return false;
		}
		return id != null && id.equals(((ProductOption) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
