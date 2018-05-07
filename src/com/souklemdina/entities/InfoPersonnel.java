/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

/**
 *
 * @author hatem
 */
public class InfoPersonnel {
        private String adresse;
        private String adresse2;
        private String ville;
        private Integer codePostal;
        private String numTel;

    public InfoPersonnel() {
    }

    public InfoPersonnel(String adresse, String adresse2, String ville, Integer codePostal, String numTel) {
        this.adresse = adresse;
        this.adresse2 = adresse2;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
        
        

}
