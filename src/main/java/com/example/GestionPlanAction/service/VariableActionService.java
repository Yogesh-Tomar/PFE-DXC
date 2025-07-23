package com.example.GestionPlanAction.service;

import com.example.GestionPlanAction.dto.ResponsableDTO;
import com.example.GestionPlanAction.dto.VariableActionDTO;
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
    public List<VariableActionDTO> getAllVariableActions() {
    	List<VariableAction> vv=variableActionRepository.findAll();
    
    	List<VariableActionDTO> actionDTOs=new ArrayList<>();
    	
    	if (!vv.isEmpty()) {
			
    		
      
    		for(VariableAction action: vv) {
    		  	ResponsableDTO dto=new ResponsableDTO();
            	VariableActionDTO actionDTO=new VariableActionDTO();
            	actionDTO.setDescription(action.getDescription());
            	actionDTO.setId(action.getId());
            	actionDTO.setNiveau(action.getNiveau());
            	actionDTO.setWeight(action.getPoids());
            	actionDTO.setFige(action.isFige());
            	actionDTO.setVaMere(action.getPlanAction().getTitre());
            	actionDTO.setStatus(action.getPlanAction().getStatut());
    			//.actionDT.setWeight(action.get);
            	actionDTO.setPlanAction(action.getPlanAction().getTitre());
            	actionDTO.setPlanActionNom(action.getPlanAction().getTitre());
            	dto.setId(action.getResponsable().getId());
            	dto.setCreatedBy(action.getResponsable().getUsername());
            	dto.setNom(action.getResponsable().getEmail());
      //  action.setResponsable().setId((Long)action.getResponsable().getId());
        actionDTO.setResponsible(dto);
            	actionDTOs.add(actionDTO);
    		}
    		System.out.println("======>>>>>"+actionDTOs.toString());
		}
    	
    	
    	
        return actionDTOs;
    }

    // ✅ Récupérer une variable d'action par ID
    public VariableActionDTO getVariableActionByIddto(Long id) {
    	
    	
        return variableActionRepository.findById(id)
				.map(variableAction -> {
					VariableActionDTO actionDTO = new VariableActionDTO();
					actionDTO.setId(variableAction.getId());
					actionDTO.setDescription(variableAction.getDescription());
					actionDTO.setNiveau(variableAction.getNiveau());
					actionDTO.setWeight(variableAction.getPoids());
					actionDTO.setFige(variableAction.isFige());
					
				//.actionDTO.	actionDTO.setVaMere(var);
					actionDTO.setPlanAction(variableAction.getPlanAction().getTitre());
					actionDTO.setPlanActionNom(variableAction.getPlanAction().getTitre());
					
					ResponsableDTO dto = new ResponsableDTO();
					dto.setId(variableAction.getResponsable().getId());
					dto.setCreatedBy(variableAction.getResponsable().getUsername());
					dto.setNom(variableAction.getResponsable().getEmail());
					
					actionDTO.setResponsible(dto);
					
					return actionDTO;
				})
				.orElseThrow(() -> new RuntimeException("Variable Action not found with id: " + id));
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

    private VariableAction getVariableActionById(Long id) {
		// TODO Auto-generated method stub
		return variableActionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Variable Action not found with id: " + id));
	}

	// ✅ Supprimer une variable d'action par ID
    public void deleteVariableAction(Long id) {
        variableActionRepository.deleteById(id);
    }

    
	public void save(List<VariableAction> variableActionsToSave) {
		
		variableActionRepository.saveAll(variableActionsToSave);
		// TODO Auto-generated method stub
		
	}
}
