/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

import java.util.Date;

/**
 *
 * @author ACER
 */
public class Location {
    
    private int id;
    private int idLocal;
    private int idUser;
    private Date dateDebutLocation;
    private Date dateFinLocation;

    public Location() {
    }

    public Location(int idLocal, int idUser, Date dateDebutLocation, Date dateFinLocation) {
        this.idLocal = idLocal;
        this.idUser = idUser;
        this.dateDebutLocation = dateDebutLocation;
        this.dateFinLocation = dateFinLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDateDebutLocation() {
        return dateDebutLocation;
    }

    public void setDateDebutLocation(Date dateDebutLocation) {
        this.dateDebutLocation = dateDebutLocation;
    }

    public Date getDateFinLocation() {
        return dateFinLocation;
    }

    public void setDateFinLocation(Date dateFinLocation) {
        this.dateFinLocation = dateFinLocation;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", idLocal=" + idLocal + ", idUser=" + idUser + ", dateDebutLocation=" + dateDebutLocation + ", dateFinLocation=" + dateFinLocation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Location other = (Location) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
