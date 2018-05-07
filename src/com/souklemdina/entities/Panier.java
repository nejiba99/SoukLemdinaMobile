/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author hatem
 */
public class Panier {
    private Integer id;
    private Integer idu;
    private String prodImg;
    private String libelle;
    private String qte;
    private Double prix;
    private Double prixTot;

    public Panier() {
    }

    public Panier(Integer id, Integer idu, String prodImg, String libelle, String qte,Double prix) {
        this.id = id;
        this.idu = idu;
        this.prodImg = prodImg;
        this.libelle = libelle;
        this.qte = qte;
        this.prix=prix;
    }

    public Panier(Integer id, Integer idu, String prodImg, String libelle, String qte, Double prix, Double prixTot) {
        this.id = id;
        this.idu = idu;
        this.prodImg = prodImg;
        this.libelle = libelle;
        this.qte = qte;
        this.prix = prix;
        this.prixTot = prixTot;
    }
    
    

    public Panier(String prodImg, String libelle, String qte) {
        this.prodImg = prodImg;
        this.libelle = libelle;
        this.qte = qte;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getIdu() {
        return idu;
    }

    public void setIdu(Integer idu) {
        this.idu = idu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }

    public Double getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(Double prixTot) {
        this.prixTot = prixTot;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", idu=" + idu + ", prodImg=" + prodImg + ", libelle=" + libelle + ", qte=" + qte + ", prixTot=" + prixTot + '}';
    }
    
    
}
