package com.myproject.productservice.model;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 2:23 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "category"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private String slug;

	private String metaKeyword;

	private String metaDescription;

	private Short displayOrder;

	private Boolean isPublished;

	private Long imageId;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	private List<Category> categories = new ArrayList<>();

	@OneToMany(mappedBy = "category")
	private List<ProductCategory> productCategories = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Category)) {
			return false;
		}
		return id != null && id.equals(((Category) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
