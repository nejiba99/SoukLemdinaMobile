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
 * @author ramyk
 */
public class Post implements Serializable {

    private Integer id;
    private Date date;
    private String texte;
    private String titre;
    private Integer nbSignal;
    private String image;
    private Date updatedAt;
    private Integer idUser;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Post(Integer id, Date date, String titre, Date updatedAt) {
        this.id = id;
        this.date = date;
        this.titre = titre;
        this.updatedAt = updatedAt;
    }

    public Post(Integer id, Date date, String texte, String titre, Integer nbSignal, String image, Date updatedAt, Integer idUser) {
        this.id = id;
        this.date = date;
        this.texte = texte;
        this.titre = titre;
        this.nbSignal = nbSignal;
        this.image = image;
        this.updatedAt = updatedAt;
        this.idUser = idUser;
    }
    
    public Post(Date date, String texte, String titre, Integer nbSignal, String image, Date updatedAt, Integer idUser) {
        this.date = date;
        this.texte = texte;
        this.titre = titre;
        this.nbSignal = nbSignal;
        this.image = image;
        this.updatedAt = updatedAt;
        this.idUser = idUser;
    }
    
    public Post(Integer id, Date date, String texte, String titre, String image, Date updatedAt, Integer idUser) {
        this.id = id;
        this.date = date;
        this.texte = texte;
        this.titre = titre;
        this.image = image;
        this.updatedAt = updatedAt;
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getNbSignal() {
        return nbSignal;
    }

    public void setNbSignal(Integer nbSignal) {
        this.nbSignal = nbSignal;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.souklemdina.entities.Post[ id=" + id + " ]";
    }
    
}
