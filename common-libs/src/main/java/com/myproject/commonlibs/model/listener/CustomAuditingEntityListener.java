package com.myproject.commonlibs.model.listener;

import com.myproject.commonlibs.model.AbstractAuditEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.NonNull;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author nguyenle
 * @since 11:54 AM Wed 7/23/2025
 */
@Configurable
public class CustomAuditingEntityListener extends AuditingEntityListener {

	public CustomAuditingEntityListener(ObjectFactory<AuditingHandler> handler) {
		super.setAuditingHandler(handler);
	}

	@Override
	@PrePersist
	public void touchForCreate(@NonNull Object target) {
		AbstractAuditEntity entity = (AbstractAuditEntity) target;
		if (entity.getCreatedBy() == null) {
			super.touchForCreate(entity);
		} else {
			if (entity.getUpdatedAt() == null) {
				entity.setUpdatedAt(entity.getCreatedAt());
			}
		}
	}

	@Override
	@PreUpdate
	public void touchForUpdate(@NonNull Object target) {
		AbstractAuditEntity entity = (AbstractAuditEntity) target;
		if (entity.getUpdatedBy() == null) {
			super.touchForUpdate(entity);
		}
	}
}
