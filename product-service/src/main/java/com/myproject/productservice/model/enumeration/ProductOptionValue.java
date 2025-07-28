package com.myproject.productservice.model.enumeration;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import com.myproject.productservice.model.Product;
import com.myproject.productservice.model.ProductOption;
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
 * @since 2:55 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product_option_value"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionValue extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "product_option_id", nullable = false)
	private ProductOption productOption;

	private String displayType;

	private int displayOrder;

	private String value;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProductOptionValue)) {
			return false;
		}
		return id != null && id.equals(((ProductOptionValue) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
