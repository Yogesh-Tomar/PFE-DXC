package com.example.GestionPlanAction.model;
import java.time.LocalDateTime;

import com.example.GestionPlanAction.enums.AuditOperation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "entity_name")
    private String entityName;
    
    @Column(name = "entity_id")
    private String entityId;
    
    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    private AuditOperation operation;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    
    @Column(name = "old_values", columnDefinition = "TEXT")
    private String oldValues;
    
    @Column(name = "new_values", columnDefinition = "TEXT")
    private String newValues;
    
    @Column(name = "ip_address")
    private String ipAddress;
    
    @Column(name = "user_agent")
    private String userAgent;
    
    // Constructors
    public AuditLog() {}
    
    public AuditLog(String entityName, String entityId, AuditOperation operation, 
                   String userId, String oldValues, String newValues, 
                   String ipAddress, String userAgent) {
        this.entityName = entityName;
        this.entityId = entityId;
        this.operation = operation;
        this.userId = userId;
        this.timestamp = LocalDateTime.now();
        this.oldValues = oldValues;
        this.newValues = newValues;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }
    
    public String getEntityId() { return entityId; }
    public void setEntityId(String entityId) { this.entityId = entityId; }
    
    public AuditOperation getOperation() { return operation; }
    public void setOperation(AuditOperation operation) { this.operation = operation; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getOldValues() { return oldValues; }
    public void setOldValues(String oldValues) { this.oldValues = oldValues; }
    
    public String getNewValues() { return newValues; }
    public void setNewValues(String newValues) { this.newValues = newValues; }
    
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
}
