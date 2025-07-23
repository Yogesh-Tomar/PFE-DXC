package com.example.GestionPlanAction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GestionPlanAction.model.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    
	/*
	 * List<Audit> findByUtilisateurOrderByDateDesc(User utilisateur);
	 * 
	 * Page<Audit> findByUtilisateurOrderByDateDesc(User utilisateur, Pageable
	 * pageable);
	 * 
	 * List<Audit> findByActionContainingIgnoreCaseOrderByDateDesc(String action);
	 * 
	 * List<Audit> findByDateBetweenOrderByDateDesc(LocalDateTime startDate,
	 * LocalDateTime endDate);
	 * 
	 * @Query("SELECT a FROM Audit a WHERE a.entityType = :entityType AND a.entityId = :entityId ORDER BY a.date DESC"
	 * ) List<Audit> findByEntityTypeAndEntityIdOrderByDateDesc(@Param("entityType")
	 * String entityType,
	 * 
	 * @Param("entityId") Long entityId);
	 * 
	 * @Query("SELECT COUNT(a) FROM Audit a WHERE a.utilisateur = :user AND a.date >= :since"
	 * ) Long countUserActionsAfter(@Param("user") User user, @Param("since")
	 * LocalDateTime since);
	 */
}