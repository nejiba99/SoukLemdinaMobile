/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

/**
 *
 * @author asus
 */
public class ParticipantsEvents {
    private int id;
    private int idEvenement;
    private int idUser;

    public ParticipantsEvents() {
    }

    
    public ParticipantsEvents(int id, int idEvenement, int idUser) {
        this.id = id;
        this.idEvenement = idEvenement;
        this.idUser = idUser;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
