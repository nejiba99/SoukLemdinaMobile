/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

/**
 *
 * @author lenovo
 */
public class ParticipantWork {
    
    
    
    public Integer id;
   
    public Integer idUser;
   
    public Integer idWorkshop;
    
     public ParticipantWork() {
       
    }

    public ParticipantWork(Integer id, Integer idUser, Integer idWorkshop) {
        this.id = id;
        this.idUser = idUser;
        this.idWorkshop = idWorkshop;
    }
public ParticipantWork( Integer idWorkshop) {
      
        this.idWorkshop = idWorkshop;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdWorkshop() {
        return idWorkshop;
    }

    public void setIdWorkshop(Integer idWorkshop) {
        this.idWorkshop = idWorkshop;
    }
    
    
}
