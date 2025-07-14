package com.example.GestionPlanAction.dto;

import lombok.Data;

@Data
public class VariableReponseDTO {
    private Long id;
    private String description;
    private float poids;
    private boolean fige;
    private int niveau;
    private String planActionNom;
    private Long plan_action_id;
    private Long responsable_id;
    private String responsableNom;
    private String responsablePrenom;
    private String responsableServiceLineNom;
}