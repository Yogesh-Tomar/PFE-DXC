package com.example.GestionPlanAction.dto;

import java.util.HashSet;
import java.util.Set;

public class UserWithProfilesDTO {
    private Long id;
    private String nom;
    private String prenom;


	public String getServiceLineName() {
		return serviceLineName;
	}
	public void setServiceLineName(String serviceLineName) {
		this.serviceLineName = serviceLineName;
	}
	public void setServiceLineId(Long serviceLineId) {
		this.serviceLineId = serviceLineId;
	}
	private String email;
    private String username;
    private Boolean actif;
    private String serviceLineName;
    private Long serviceLineId;
    
    private Set<ProfilDTO> profils = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public UserWithProfilesDTO(){}
	public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    @Override
	public String toString() {
		return "UserWithProfilesDTO [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", username=" + username + ", actif=" + actif + ", serviceLineName=" + serviceLineName
				+ ", serviceLineId=" + serviceLineId + ", profils=" + profils + "]";
	}
	public Long getServiceLineId() {
		return serviceLineId;
	}
	public UserWithProfilesDTO(
	    Long id,
	    String nom,
	    Boolean actif,
	    String email,
	    String prenom,
	    String username,
	    Long serviceLineId,
	    String serviceLineName,
	    Long profilId,
	    String profilNom
	) {
	    this.id = id;
	    this.nom = nom;
	    this.actif = actif;
	    this.email = email;
	    this.prenom = prenom;
	    this.username = username;
	    this.serviceLineId = serviceLineId;
	    this.serviceLineName = serviceLineName;
	    this.profilId = profilId;
	    this.profilNom = profilNom;
	    this.profils = new HashSet<>();
	}
	public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }
   // public String getServiceLineId() { return serviceLineId; }
   // public void setServiceLineId(String serviceLineId) { this.serviceLineId = serviceLineId; }
    public Set<ProfilDTO> getProfils() { return profils; }
    public void setProfils(Set<ProfilDTO> profils) { this.profils = profils; }

	private Long profilId;
	private String profilNom;

	public Long getProfilId() { return profilId; }
	public void setProfilId(Long profilId) { this.profilId = profilId; }
	public String getProfilNom() { return profilNom; }
	public void setProfilNom(String profilNom) { this.profilNom = profilNom; }
}