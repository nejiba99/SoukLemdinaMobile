/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.entities;

/**
 *
 * @author rkader
 */
public class PostHome {
    private Profile pr;
    private Post pos;
    private String firstname;
    private String lastname;
    private Boolean mfollowi;

    public PostHome(Profile pr, Post pos, String fname, String lname, Boolean mfollowi) {
        this.pr = pr;
        this.pos = pos;
        this.lastname = lname;
        this.firstname = fname;
        this.mfollowi = mfollowi;
    }

    public Boolean getMfollowi() {
        return mfollowi;
    }

    public void setMfollowi(Boolean mfollowi) {
        this.mfollowi = mfollowi;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Profile getPr() {
        return pr;
    }

    public void setPr(Profile pr) {
        this.pr = pr;
    }

    public Post getPos() {
        return pos;
    }

    public void setPos(Post pos) {
        this.pos = pos;
    }
    
    
}
