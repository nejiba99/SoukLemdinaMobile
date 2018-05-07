/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.io.Serializable;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author ACER
 */
public class Local implements Serializable {
    
    private int id ;
    private int superficie ;
    private int prix ;
    private String type;
    private String description;
    private String image;
    private String adresse;
    private String telephone;
    private String titre;
    private int nbSignal;
    private Image located;
    private int idUser;
    private List<Location> locationList;

    public Local() {
    }

    public Local(int id, int superficie, int prix, String type, String description, String image, String adresse, String telephone, int nbSignal, int idUser, List<Location> locationList) {
        this.id = id;
        this.superficie = superficie;
        this.prix = prix;
        this.type = type;
        this.description = description;
        this.image = image;
        this.adresse = adresse;
        this.telephone = telephone;
        this.nbSignal = nbSignal;
        this.idUser = idUser;
        this.locationList = locationList;
    }
        public Local(int id, int superficie, int prix, String type, String description, String image, String adresse, String telephone) {
        this.id = id;
        this.superficie = superficie;
        this.prix = prix;
        this.type = type;
        this.description = description;
        this.image = image;
        this.adresse = adresse;
        this.telephone = telephone;
  
      
      
       
    }
        
          public Local( int id,String type,  String image) {
        this.id = id;
        this.type = type;
        this.image = image;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(int nbSignal) {
        this.nbSignal = nbSignal;
    }

    public Image getLocated() {
        return located;
    }

    public void setLocated(Image located) {
        this.located = located;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public String toString() {
        return "Local{" + "id=" + id + ", superficie=" + superficie + ", prix=" + prix + ", type=" + type + ", description=" + description + ", image=" + image + ", adresse=" + adresse + ", telephone=" + telephone + ", titre=" + titre + ", nbSignal=" + nbSignal + ", located=" + located + ", idUser=" + idUser + ", locationList=" + locationList + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Local other = (Local) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
