package com.myproject.commonlibs.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author nguyenle
 * @since 11:52 AM Wed 7/23/2025
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditEntity {

	@CreatedDate
	private Date createdAt;

	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private Date updatedAt;

	@LastModifiedBy
	private String updatedBy;
}
