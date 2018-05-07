/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.util.Date;
import java.util.List;


/**
 *
 * @author lenovo
 */
public class Workshop {
    
     private Integer id;
    private String nomWorkshop;
    private String adresse;
    private String type;
    private Date dateDebut;
    private Date dateFin;
   
    private int nbPlace;
    private double prix;
   
    private Integer nbSignal;
    
    private String description;
   
    private String dateDeb;
    private String dateF;
    private String image;
  
    private Date updatedAt;
   
    private Integer rating;
   
    private Integer nbrrating;
  
    private List<ParticipantWork> participantWorkList;
   
    private Integer idUser;
    
    public Workshop(Integer id){
         this.id = id;
    }

    public Workshop() {
    }
//pr pie
    public Workshop(Integer id, int nbPlace) {
        this.id = id;
        this.nbPlace = nbPlace;
    }

    public Workshop(Integer id, String nomWorkshop,  String image ,Integer nbPlace, double prix, String description ,
            String dateDeb, String dateF  ) {
        this.id = id;
        this.nomWorkshop = nomWorkshop;
         this.image = image;
          this.nbPlace = nbPlace;
        this.prix = prix;
               this.description = description;
                this.dateDeb = dateDeb;
        this.dateF = dateF;
        
        
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateF() {
        return dateF;
    }

    public void setDateF(String dateF) {
        this.dateF = dateF;
    }

   

    public Workshop(Integer id, String nomWorkshop, String adresse, String type,
            int nbPlace, double prix, String dateDeb, String dateF,String description, String image, Integer idUser) {
        this.id = id;
        this.nomWorkshop = nomWorkshop;
        this.adresse = adresse;
        this.type = type;
        this.dateDeb = dateDeb;
        this.dateF = dateF;
        this.nbPlace = nbPlace;
        this.prix = prix;
      
        this.description = description;
        this.image = image;

        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        
        return hash;
    }

    public Workshop(Integer id, String nomWorkshop, String adresse, Date dateDebut, Date dateFin, int nbPlace, double prix, String description, String image) {
        this.id = id;
        this.nomWorkshop = nomWorkshop;
        this.adresse = adresse;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbPlace = nbPlace;
        this.prix = prix;
        this.description = description;
        this.image = image;
       // this.idUser = idUser;
    }

  
    @Override
    public String toString() {
        return "Workshop{" + "id=" + id + ", nomWorkshop=" + nomWorkshop + ", adresse=" + adresse + ", type=" + type + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nbPlace=" + nbPlace + ", prix=" + prix + ", nbSignal=" + nbSignal + ", description=" + description + ", image=" + image + ", updatedAt=" + updatedAt + ", rating=" + rating + ", nbrrating=" + nbrrating + ", participantWorkList=" + participantWorkList + ", idUser=" + idUser + '}';
    }


    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomWorkshop() {
        return nomWorkshop;
    }

    public void setNomWorkshop(String nomWorkshop) {
        this.nomWorkshop = nomWorkshop;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Integer getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(Integer nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNbrrating() {
        return nbrrating;
    }

    public void setNbrrating(Integer nbrrating) {
        this.nbrrating = nbrrating;
    }

    public List<ParticipantWork> getParticipantWorkList() {
        return participantWorkList;
    }

    public void setParticipantWorkList(List<ParticipantWork> participantWorkList) {
        this.participantWorkList = participantWorkList;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    
    
    
}
