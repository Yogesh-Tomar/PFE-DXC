package com.example.GestionPlanAction.config;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.GestionPlanAction.enums.AuditOperation;
import com.example.GestionPlanAction.service.AuditService;

import jakarta.persistence.Id;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;

@Component
public class AuditEntityListener {
    
    private static AuditService auditService;
    
    @Autowired
    public void setAuditService(AuditService auditService) {
        AuditEntityListener.auditService = auditService;
    }
    
    @PrePersist
    public void prePersist(Object entity) {
        auditService.logAudit(
            entity.getClass().getSimpleName(),
            getEntityId(entity),
            AuditOperation.CREATE,
            null,
            entity
        );
    }
    
    @PostUpdate
    public void postUpdate(Object entity) {
        auditService.logAudit(
            entity.getClass().getSimpleName(),
            getEntityId(entity),
            AuditOperation.UPDATE,
            null, // For old values, you'd need to implement @PreUpdate with state tracking
            entity
        );
    }
    
    @PreRemove
    public void preRemove(Object entity) {
        auditService.logAudit(
            entity.getClass().getSimpleName(),
            getEntityId(entity),
            AuditOperation.DELETE,
            entity,
            null
        );
    }
    
    private Object getEntityId(Object entity) {
        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return field.get(entity);
                }
            }
        } catch (Exception e) {
            // Handle exception
        }
        return null;
    }
}
