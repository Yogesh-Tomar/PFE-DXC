package com.example.GestionPlanAction.dto;

import java.util.List;

import com.example.GestionPlanAction.enums.StatutPlanAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariableActionDTO {
	
	
	
    private Long id;
    private String description;
    private float weight;
    private Boolean fige;
    private int niveau;
    private float poids;
    private String vaMere;
    private StatutPlanAction status; 
    private List<String> sousVAs;
    
    

  //  private Responsable responsable;
    private ResponsableDTO responsible;
    private String planAction;
   
    
    private String planActionNom;
    
	    

}
