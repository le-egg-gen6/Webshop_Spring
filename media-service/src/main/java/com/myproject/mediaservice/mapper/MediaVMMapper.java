package com.myproject.mediaservice.mapper;

import com.myproject.commonlibs.mapper.BaseMapper;
import com.myproject.mediaservice.model.Media;
import com.myproject.mediaservice.view_model.MediaVM;
import org.mapstruct.Mapper;

/**
 * @author nguyenle
 * @since 2:16 PM Thu 7/24/2025
 */
@Mapper(componentModel = "spring")
public interface MediaVMMapper extends BaseMapper<Media, MediaVM> {

}
