package com.myproject.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 2:33 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_category"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int displayOrder;

	private boolean isFeaturedProduct;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductCategory)) {
			return false;
		}
		return id != null && id.equals(((ProductCategory) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
