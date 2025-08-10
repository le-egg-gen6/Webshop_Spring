package com.myproject.locationservice.view_model.address;

import com.myproject.commonlibs.view_model.AbstractPagableObject;
import lombok.*;

import java.util.List;

/**
 * @author nguyenle
 * @since 3:22 PM Sun 8/10/2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressListVM extends AbstractPagableObject {

    private List<AddressVM> addresses;

}
