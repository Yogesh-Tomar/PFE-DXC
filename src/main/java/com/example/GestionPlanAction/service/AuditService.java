package com.example.GestionPlanAction.service;
//4. Audit Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.GestionPlanAction.enums.AuditOperation;
import com.example.GestionPlanAction.model.AuditLog;
import com.example.GestionPlanAction.repository.AuditLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuditService {
 
 @Autowired
 private AuditLogRepository auditLogRepository;
 
 @Autowired
 private ObjectMapper objectMapper;
 
 public void logAudit(String entityName, Object entityId, AuditOperation operation, 
                     Object oldValues, Object newValues) {
     try {
         String currentUser = getCurrentUser();
         String ipAddress = getClientIpAddress();
         String userAgent = getUserAgent();
         
         String oldValuesJson = oldValues != null ? objectMapper.writeValueAsString(oldValues) : null;
         String newValuesJson = newValues != null ? objectMapper.writeValueAsString(newValues) : null;
         
         AuditLog auditLog = new AuditLog(
             entityName,
             entityId != null ? entityId.toString() : null,
             operation,
             currentUser,
             oldValuesJson,
             newValuesJson,
             ipAddress,
             userAgent
         );
         
         auditLogRepository.save(auditLog);
     } catch (Exception e) {
         // Log error but don't fail the main operation
         System.err.println("Failed to save audit log: " + e.getMessage());
     }
 }
 
 private String getCurrentUser() {
     // Implement based on your security configuration
     // Example for Spring Security:
     /*
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     if (authentication != null && authentication.isAuthenticated()) {
         return authentication.getName();
     }
     */
     return "system"; // Default fallback
 }
 
 private String getClientIpAddress() {
     ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
     if (attrs != null) {
         HttpServletRequest request = attrs.getRequest();
         String xForwardedFor = request.getHeader("X-Forwarded-For");
         if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
             return xForwardedFor.split(",")[0].trim();
         }
         return request.getRemoteAddr();
     }
     return "unknown";
 }
 
 private String getUserAgent() {
     ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
     if (attrs != null) {
         return attrs.getRequest().getHeader("User-Agent");
     }
     return "unknown";
 }
}
