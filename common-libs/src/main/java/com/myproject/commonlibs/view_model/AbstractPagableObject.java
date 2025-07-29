package com.myproject.commonlibs.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

/**
 * @author nguyenle
 * @since 5:39 PM Mon 7/28/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractPagableObject {
	private int pageIndex;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean lastPage;

	public void applyPageableMetadata(Page<?> page) {
		this.pageIndex = page.getNumber();
		this.pageSize = page.getSize();
		this.totalElements = Math.toIntExact(page.getTotalElements());
		this.totalPages = Math.toIntExact(page.getTotalPages());
		this.lastPage = page.isLast();
	}
}
