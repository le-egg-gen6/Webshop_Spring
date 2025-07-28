package com.myproject.productservice.model.enumeration;

import lombok.Getter;

/**
 * @author nguyenle
 * @since 11:17 AM Wed 7/23/2025
 */
@Getter
public enum MeasurementUnit {

	CM("cm"),
	INCH("inch")
	;

	private final String value;

	MeasurementUnit(String value) {
		this.value = value;
	}

}
