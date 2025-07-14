package com.example.GestionPlanAction.service;

import com.example.GestionPlanAction.dto.VariableActionDTO;
import com.example.GestionPlanAction.dto.VariableReponseDTO;
import com.example.GestionPlanAction.model.VariableAction;
import com.example.GestionPlanAction.repository.VariableActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariableActionService {

    @Autowired
    private VariableActionRepository variableActionRepository;

    // ✅ Récupérer toutes les variables d'action
    public List<VariableAction> getAllVariableActions() {
        return variableActionRepository.findAll();
    }

    // ✅ Récupérer une variable d'action par ID
    public VariableAction getVariableActionById(Long id) {
        return variableActionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variable d'action non trouvée avec l'ID : " + id));
    }

    public VariableReponseDTO getVariableActionEditById(Long id) {
        VariableAction dto =  variableActionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variable d'action non trouvée avec l'ID : " + id));
        VariableReponseDTO response = new VariableReponseDTO();
        response.setId(dto.getId());
        response.setDescription(dto.getDescription());
        response.setPoids(dto.getPoids());
        response.setFige(dto.isFige());
        response.setNiveau(dto.getNiveau());
        response.setPlanActionNom(dto.getPlanAction() != null ? dto.getPlanAction().getTitre() : null);
        response.setPlan_action_id(dto.getPlanAction() != null ? dto.getPlanAction().getId() : null);
        response.setResponsableNom(dto.getResponsable() != null ? dto.getResponsable().getNom() : null);
        response.setResponsablePrenom(dto.getResponsable() != null ? dto.getResponsable().getPrenom() : null);
        response.setResponsable_id(dto.getResponsable() != null ? dto.getResponsable().getId() : null);
        return response;
    }

    public List<VariableActionDTO> getAllVariableActionDTOs() {
        List<VariableAction> entities = variableActionRepository.findAll();
        List<VariableActionDTO> dtos = new ArrayList<>();
        for (VariableAction va : entities) {
            VariableActionDTO dto = new VariableActionDTO();
            dto.setId(va.getId());
            dto.setDescription(va.getDescription());
            dto.setPoids(va.getPoids());
            dto.setFige(va.isFige());
            dto.setNiveau(va.getNiveau());
            dto.setPlanActionNom(va.getPlanAction() != null ? va.getPlanAction().getTitre() : null);
            dto.setPlanActionId(va.getPlanAction() != null ? va.getPlanAction().getId() : null);
            dto.setResponsableNom(va.getResponsable() != null ? va.getResponsable().getNom() : null);
            dto.setResponsablePrenom(va.getResponsable() != null ? va.getResponsable().getPrenom() : null);
            dto.setResponsableId(va.getResponsable() != null ? va.getResponsable().getId() : null);
            dtos.add(dto);
        }
        return dtos;
    }

    // ✅ Créer une nouvelle variable d'action
    public VariableAction createVariableAction(VariableAction variableAction) {
        return variableActionRepository.save(variableAction);
    }

    // ✅ Mettre à jour une variable d'action existante
    public VariableAction updateVariableAction(Long id, VariableAction updated) {
        VariableAction existing = getVariableActionById(id);

        existing.setDescription(updated.getDescription());
        existing.setPoids(updated.getPoids());
        existing.setFige(updated.isFige());
        existing.setNiveau(updated.getNiveau());
        existing.setVaMere(updated.getVaMere());
        existing.setResponsable(updated.getResponsable());
        existing.setPlanAction(updated.getPlanAction());

        return variableActionRepository.save(existing);
    }

    // ✅ Supprimer une variable d'action par ID
    public void deleteVariableAction(Long id) {
        variableActionRepository.deleteById(id);
    }

    // ✅ Mettre à jour l'état "fige" d'une variable d'action
    public VariableAction updateFige(Long id, boolean fige) {
        VariableAction existing = getVariableActionById(id);
        existing.setFige(fige);
        return variableActionRepository.save(existing);
    }
}
