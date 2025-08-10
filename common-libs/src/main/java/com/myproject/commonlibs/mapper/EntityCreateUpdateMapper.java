package com.myproject.commonlibs.mapper;

/**
 * @author nguyenle
 * @since 4:35 PM Wed 7/23/2025
 */

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * A generic base interface for mapping between model, view model, and view model response objects. In case we are implementing create/update operation, we need 3 objects "request", "entity",
 * "response". Then this base mapper will help us to manage mapping them.
 *
 * @param <M> The entity type that represents the model.
 * @param <C_VM> The type that represents the create/update view model.
 * @param <VM> The type that represents the view model response.
 */
public interface EntityCreateUpdateMapper<M, C_VM, VM> {

	/**
	 * Converts the provided view model to its corresponding model entity.
	 *
	 * @param vm The view model object to convert.
	 * @return The model entity corresponding to the view model.
	 */
	M toModel(C_VM vm);

	/**
	 * Partially updates the provided model entity with values from the given view model. Fields in the view model that are null will be ignored.
	 *
	 * @param m The model entity to update.
	 * @param v The view model with updated values.
	 */
	@Mapping(target = "id", ignore = true)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void partialUpdate(@MappingTarget M m, C_VM v);

	/**
	 * Converts the provided model entity to its corresponding view model response.
	 *
	 * @param m The model entity to convert.
	 * @return The view model response corresponding to the model entity.
	 */
	@BeanMapping(ignoreByDefault = true)
	VM toVmResponse(M m);

}
