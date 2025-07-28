package com.myproject.productservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 10:51 AM Wed 7/23/2025
 */
@Entity
@Table(
	name = "brand"
)
@Getter
@Setter
public class Brand extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String slug;

	private boolean isPublished;

	@OneToMany(mappedBy = "brand")
	private List<Product> products;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Brand)) {
			return false;
		}
		return id != null && id.equals(((Brand) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
