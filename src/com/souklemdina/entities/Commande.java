/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.util.Date;

/**
 *
 * @author hatem
 */
public class Commande {
    private Integer id;
    
    private String dateCommande;
    
    private String dateMax;
    
    private Integer idUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getDateMax() {
        return dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Commande(Integer id, String dateCommande, String dateMax) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.dateMax = dateMax;
    }

    public Commande(Integer id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", Date Commande=" + dateCommande + ", Date Max=" + dateMax + '}';
    }
}
