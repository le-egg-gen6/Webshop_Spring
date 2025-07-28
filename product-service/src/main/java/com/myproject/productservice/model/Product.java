package com.myproject.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myproject.commonlibs.model.AbstractAuditEntity;
import com.myproject.productservice.model.attribute.ProductAttributeValue;
import com.myproject.productservice.model.enumeration.MeasurementUnit;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 12:05 PM Wed 7/23/2025
 */
@Entity
@Table(
	name = "product"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends AbstractAuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String shortDescription;

	private String description;

	private String specification;

	private String sku;

	private String gtin;

	private String slug;

	private Double price;

	private boolean hasOptions;

	private boolean isAllowedToOrder;

	private boolean isPublished;

	private boolean isFeatured;

	private boolean isVisibleIndividually;

	private boolean stockTrackingEnabled;

	private Long stockQuantity;

	private Long taxClassId;

	private String metaTitle;

	private String metaKeyword;

	private String metaDescription;

	private Long thumbnailMediaId;

	private Double weight;

	@Enumerated(EnumType.STRING)
	private MeasurementUnit measurementUnit;

	private Double length;

	private Double width;

	private Double height;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST})
	@Builder.Default
	private List<ProductCategory> productCategories = new ArrayList<>();

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	@Builder.Default
	private List<ProductAttributeValue> attributeValues = new ArrayList<>();

	@OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST})
	@Builder.Default
	private List<ProductImage> productImages = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Product parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	@JsonIgnore
	@Builder.Default
	private List<Product> products = new ArrayList<>();

	private boolean taxIncluded;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Product)) {
			return false;
		}
		return id != null && id.equals(((Product) o).getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
