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
public class Produit implements Serializable {
    
    private Integer id;

    private String libelle;

    private String description;
 
    private Integer quqntite;

    private int promotion;

    private int nbSignal;
 
    private String type;

    private String categorie;

    private double prix;
 
    private Date updatedAt;

    private String image;

    private Integer idUser;

    public Produit(Integer id, String libelle, String description, Integer quqntite, int promotion, int nbSignal, String type, String categorie, double prix, Date updatedAt, String image, Integer idUser) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.quqntite = quqntite;
        this.promotion = promotion;
        this.nbSignal = nbSignal;
        this.type = type;
        this.categorie = categorie;
        this.prix = prix;
        this.updatedAt = updatedAt;
        this.image = image;
        this.idUser = idUser;
    }

    public Produit() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuqntite() {
        return quqntite;
    }

    public void setQuqntite(Integer quqntite) {
        this.quqntite = quqntite;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public int getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(int nbSignal) {
        this.nbSignal = nbSignal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + '}';
    }



    
    
    
    
}
