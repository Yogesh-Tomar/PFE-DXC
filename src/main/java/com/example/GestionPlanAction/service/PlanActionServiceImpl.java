package com.example.GestionPlanAction.service;

import com.example.GestionPlanAction.enums.StatutPlanAction;
import com.example.GestionPlanAction.model.PlanAction;
import com.example.GestionPlanAction.model.VariableAction;
import com.example.GestionPlanAction.repository.PlanActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanActionServiceImpl implements PlanActionService {

    @Autowired
    private PlanActionRepository repository;

    @Override
    public List<PlanAction> getAll() {
        return repository.findAll();
    }

    @Override
    public PlanAction getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlanAction non trouvé"));
    }

    @Override
    public PlanAction create(PlanAction planAction) {
        if (planAction.getStatut() == null) {
            planAction.setStatut(StatutPlanAction.EN_COURS_PLANIFICATION);
        }
        if (planAction.getVariableActions() != null) {
            for (var va : planAction.getVariableActions()) {
                va.setPlanAction(planAction); // Set parent reference
            }
        }
        return repository.save(planAction);
    }

    @Override
    public PlanAction update(Long id, PlanAction updated) {
        PlanAction existing = getById(id);
        existing.setTitre(updated.getTitre());
        existing.setDescription(updated.getDescription());
        existing.setStatut(updated.getStatut());
        existing.setExercice(updated.getExercice());

        // Collect incoming IDs
        List<Long> incomingIds = new ArrayList<>();
        if (updated.getVariableActions() != null) {
            for (VariableAction va : updated.getVariableActions()) {
                if (va.getId() != null) {
                    incomingIds.add(va.getId());
                }
            }
        }

        // Remove VariableActions not present in the update
        existing.getVariableActions().removeIf(eva -> eva.getId() != null && !incomingIds.contains(eva.getId()));

        // Update or add VariableActions
        if (updated.getVariableActions() != null) {
            for (VariableAction va : updated.getVariableActions()) {
                va.setPlanAction(existing);
                if (va.getId() != null) {
                    VariableAction existingVa = existing.getVariableActions().stream()
                        .filter(eva -> eva.getId().equals(va.getId()))
                        .findFirst()
                        .orElse(null);
                    if (existingVa != null) {
                        existingVa.setDescription(va.getDescription());
                        existingVa.setPoids(va.getPoids());
                        existingVa.setFige(va.isFige());
                        existingVa.setNiveau(va.getNiveau());
                        existingVa.setResponsable(va.getResponsable());
                        existingVa.setVaMere(va.getVaMere());
                    } else {
                        existing.getVariableActions().add(va);
                    }
                } else {
                    existing.getVariableActions().add(va);
                }
            }
        }

        return repository.save(existing);
    }

    public PlanAction updateStatus(Long id, String status) {
        PlanAction plan = getById(id);
        plan.setStatut(StatutPlanAction.valueOf(status));
        return repository.save(plan);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
