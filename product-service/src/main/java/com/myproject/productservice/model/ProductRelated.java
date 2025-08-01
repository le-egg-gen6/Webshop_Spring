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
 * @since 3:01 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_related"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRelated {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "related_product_id")
	private Product relatedProduct;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductRelated)) {
			return false;
		}
		return id != null && id.equals(((ProductRelated) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
