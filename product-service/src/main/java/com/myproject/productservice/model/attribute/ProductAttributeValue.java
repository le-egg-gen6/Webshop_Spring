package com.myproject.productservice.model.attribute;

import com.myproject.productservice.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 3:16 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_attribute_value"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttributeValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "product_attribute_id", nullable = false)
	private ProductAttribute productAttribute;

	private String value;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductAttributeValue)) {
			return false;
		}
		return id != null && id.equals(((ProductAttributeValue) o).id);
	}

	@Override
	public int hashCode() {
		return getProduct().hashCode();
	}
}