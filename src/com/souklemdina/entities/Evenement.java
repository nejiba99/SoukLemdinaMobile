/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author asus
 */
public class Evenement implements Serializable {

    private Integer id;
    private String nomEvenement;
    private Date dateDebut;
    private String dateDebuts;
    private String datefins;

    public String getDatefins() {
        return datefins;
    }

    public void setDatefins(String datefins) {
        this.datefins = datefins;
    }

    private Date dateFin;
    private String adresse;
    private double prix;

   
    private String description;
    private int nbPlace;
    private String type;
    private Date heure;
    private String heures;

    public String getHeures() {
        return heures;
    }

    public void setHeures(String heures) {
        this.heures = heures;
    }
    private String photo;
    private int rating;
    private Integer idUser;

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(Integer id, String nomEvenement, String type,String dateDebuts,String datefins, String photo, Double prix, Integer nbPlace, String description,int rating,Integer idUser) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.type = type;
        this.dateDebuts = dateDebuts;
         this.datefins = datefins;
        this.photo = photo;
        this.prix = prix;
        this.nbPlace = nbPlace;
        this.description = description;
        this.rating=rating;
        this.idUser = idUser;

    }
    

    
    
    
        public Evenement(Integer id, String nomEvenement, Date dateDebut, Date dateFin, String adresse, Double prix, String description, Integer nbPlace, String type, String photo) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.adresse = adresse;
        this.prix = prix;
        this.description = description;
        this.nbPlace = nbPlace;
        this.type = type;
        this.photo = photo;
     
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nomEvenement=" + nomEvenement + ", dateDebut=" + dateDebut + ", dateDebuts=" + dateDebuts + ", datefins=" + datefins + ", dateFin=" + dateFin + ", adresse=" + adresse + ", prix=" + prix + ", description=" + description + ", nbPlace=" + nbPlace + ", type=" + type + ", heure=" + heure + ", photo=" + photo + ", rating=" + rating + ", idUser=" + idUser + '}';
    }

  

    public Evenement(Integer id, String nomEvenement, Date dateDebut, Date heure, Date dateFin, String adresse, Double prix, String description, Integer nbPlace, String type, String photo, Integer idUser) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.dateDebut = dateDebut;
        this.heure = heure;
        this.dateFin = dateFin;
        this.adresse = adresse;
        this.prix = prix;
        this.description = description;
        this.nbPlace = nbPlace;
        this.type = type;
        this.photo = photo;
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
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

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    
    
     public String getDateDebuts() {
        return dateDebuts;
    }

    public void setDateDebuts(String dateDebuts) {
        this.dateDebuts = dateDebuts;
    }

}
