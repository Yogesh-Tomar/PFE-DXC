package com.example.GestionPlanAction.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariableAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private float poids;
    private boolean fige;
    private int niveau;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private User responsable;

    @ManyToOne
    @JoinColumn(name = "plan_action_id")
    @JsonBackReference
    private PlanAction planAction;

    // Prevent recursion for parent-child VariableAction
    @ManyToOne
    @JoinColumn(name = "va_mere_id")
    @JsonBackReference("vaMere")
    private VariableAction vaMere;

    @OneToMany(mappedBy = "vaMere", cascade = CascadeType.ALL)
    @JsonManagedReference("vaMere")
    private List<VariableAction> sousVAs;
}
