package com.example.GestionPlanAction.dto;

import java.util.List;

import com.example.GestionPlanAction.model.Exercice;
import com.example.GestionPlanAction.model.VariableAction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlanActionDTO {
	

	    private Long id;
	    private String titre;
	    private String description;
	    private String statut;
	    private ExerciseDTO exercice;
	    private String createdBy;
	//    @JsonInclude(Include.NON_NULL)
	    private List<VariableActionDTO> variableActions;
	    

	    // Getters and Setters
	}



