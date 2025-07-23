package com.example.GestionPlanAction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionPlanAction.dto.ExerciseDTO;
import com.example.GestionPlanAction.dto.PlanActionDTO;
import com.example.GestionPlanAction.dto.ResponsableDTO;
import com.example.GestionPlanAction.dto.VariableActionDTO;
import com.example.GestionPlanAction.enums.StatutPlanAction;
import com.example.GestionPlanAction.model.PlanAction;
import com.example.GestionPlanAction.model.VariableAction;
import com.example.GestionPlanAction.repository.PlanActionRepository;

@Service
public class PlanActionServiceImpl implements PlanActionService {

	@Autowired
	private PlanActionRepository repository;

	@Autowired
	private VariableActionService variableActionServicerepo;

	@Override
	public List<PlanActionDTO> getAll() {

		List<Object[]> plans = repository.findAllplans();

		List<PlanActionDTO> pdto = new ArrayList<>();

		for (Object[] plan : plans) {
			List<VariableActionDTO> variableActions = new ArrayList<>();

			PlanActionDTO planActionDTO = new PlanActionDTO();
			ResponsableDTO responsableDTO = new ResponsableDTO();
			VariableActionDTO variableAction = new VariableActionDTO();
			ExerciseDTO exerciceDTO = new ExerciseDTO();
			planActionDTO.setId((Long) plan[1]);
			planActionDTO.setDescription((String) plan[2]);
			planActionDTO.setTitre((String) plan[3]);
			planActionDTO.setStatut((String) plan[4]);
			exerciceDTO.setAnnee((Integer) plan[13]);
			exerciceDTO.setVerrouille((Boolean) plan[14]);
			exerciceDTO.setId((Long) plan[15]);
		planActionDTO.setCreatedBy((String)plan[21]);
			planActionDTO.setExercice(exerciceDTO);
			// exerciceDTO.setAnnee((Integer) plan[5]);
			// exerciceDTO.setVerrouille((Integer) plan[6]);
			// exerciceDTO.setId((Long) plan[7]);

			if (plan[7] == null) {
				variableActions.add(null);
				planActionDTO.setVariableActions(variableActions);
				pdto.add(planActionDTO);
				// Skip if the first element is null
			} else {
				Optional<PlanActionDTO> result = pdto.stream().filter(plant -> plant.getId().equals((Long) plan[1]))
						.findFirst();
				if (result.isPresent()) {

					
					//planActionDTO=result.get().getVariableActions();
					variableAction.setDescription((String) plan[12]);
					variableAction.setVaMere((String) plan[11]);
					variableAction.setId((Long) plan[8]);
					variableAction.setPoids((float) plan[7]);
					variableAction.setFige((Boolean) plan[5]);

					variableAction.setResponsible(responsableDTO);
					// variableAction.setNiveau((int) plan[6]);
					responsableDTO.setId((Long) plan[9]);
					responsableDTO.setCreatedBy((String)plan[21]);
					//responsableDTO.setUserName(null);
					//variableActions.add(variableAction);
					
					variableActions = result.get().getVariableActions();

			
				
					variableActions.add(variableAction);
					
					
					

				} else {
					variableAction.setDescription((String) plan[12]);
					variableAction.setVaMere((String) plan[11]);
					variableAction.setId((Long) plan[8]);
					variableAction.setPoids((float) plan[7]);
					variableAction.setFige((Boolean) plan[5]);
					planActionDTO.setCreatedBy((String)plan[21]);

					variableAction.setResponsible(responsableDTO);
					// variableAction.setNiveau((int) plan[6]);
					responsableDTO.setId((Long) plan[9]);
					responsableDTO.setCreatedBy((String)plan[21]);
					variableActions.add(variableAction);
					planActionDTO.setVariableActions(variableActions);
					pdto.add(planActionDTO);

				}
			}
			
		

			System.out.println("PlanAction récupéré: " + planActionDTO.toString());
		}

		return pdto;
	}

	@Override
	public PlanActionDTO getByIddto(Long id) {
		// PlanAction planaction=new PlanAction();
	Optional<PlanAction>	planaction=repository.findById(id);
	PlanActionDTO planActionDTO = new PlanActionDTO();
	planActionDTO.setId(planaction.get().getId());
//planActionDTO.setCreatedBy(planaction.get().getVariableActions().get(0).getResponsable().getEmail());
	planActionDTO.setTitre(planaction.get().getTitre());
	planActionDTO.setDescription(planaction.get().getDescription());
	planActionDTO.setStatut(planaction.get().getStatut().toString());
	planActionDTO.setCreatedBy(planaction.get().getVariableActions().get(0).getResponsable().getEmail());	
//	planActionDTO.setCreatedBy(planaction.get().getVariableActions().get(0).getResponsable().getEmail());
	planActionDTO.setExercice(new ExerciseDTO(planaction.get().getExercice().getId(), 
			planaction.get().getExercice().getAnnee(), planaction.get().getExercice().isVerrouille()));
//	planActionDTO.setCreatedBy(planaction.get());
	planActionDTO.setVariableActions(new ArrayList<>());
	planaction.get().getVariableActions().forEach(va -> {
		
		VariableActionDTO variableActionDTO = new VariableActionDTO();
		variableActionDTO.setId(va.getId());
		variableActionDTO.setDescription(va.getDescription());
		variableActionDTO.setPoids(va.getPoids());
		variableActionDTO.setFige(va.isFige());
		variableActionDTO.setNiveau(va.getNiveau());
		planActionDTO.setCreatedBy(va.getResponsable().getEmail());
		//variableActionDTO.setVaMere(va.getVaMere() != null ? va.getVaMere().getDescription() : null);
	//	variableActionDTO.setResonableid);//, va.getResponsable().getCreatedBy(va.get
		planActionDTO.getVariableActions().add(variableActionDTO);
	});
		return planActionDTO;
	}

	@Override
	public PlanAction create(PlanAction planAction) {
		if (planAction.getStatut() == null) {
			planAction.setStatut(StatutPlanAction.EN_COURS_PLANIFICATION);
		}
		System.out.println("Création d'un nouveau PlanAction: " + planAction.toString());
		PlanAction getplan = new PlanAction();
		// getplanaction(planAction.getTitre(), planAction.getDescription(),
		// planAction.getStatut(), planAction.getExercice());
		// getplanaction.setVariableActions(new ArrayList<VariableAction>());
		getplan.setDescription(planAction.getDescription());
		getplan.setTitre(planAction.getTitre());
		getplan.setStatut(planAction.getStatut());
		getplan.setExercice(planAction.getExercice());
		PlanAction pp = repository.save(getplan);
		System.out.println("PlanAction créé avec succès: " + pp.toString());
		List<VariableAction> variableActions = new ArrayList<>();
		variableActions.addAll(planAction.getVariableActions());

		if (variableActions.isEmpty()) {
			variableActions.add(new VariableAction());
		} else {
			System.out.println("Nombre de VariableAction associées: " + variableActions.toString());

			List<VariableAction> variableActionsToSave = new ArrayList<>();

			for (VariableAction va : variableActions) {
				PlanAction planActionEntity = new PlanAction();
				planActionEntity.setId(pp.getId());

				va.setPlanAction(planActionEntity);

				variableActionsToSave.add(va);
			}

			variableActionServicerepo.save(variableActionsToSave);
			System.out.println("VariableActions associées au PlanAction: " + variableActionsToSave.toString());
			return getplan;
		}
		return getplan;

	}

	@Override
	public PlanAction update(Long id, PlanAction updated) {
		PlanAction existing = getById(id);
		existing.setTitre(updated.getTitre());
		existing.setDescription(updated.getDescription());
		existing.setStatut(updated.getStatut());
		existing.setExercice(updated.getExercice());
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

	@Override
	public PlanAction getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
