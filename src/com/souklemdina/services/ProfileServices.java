/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.souklemdina.entities.FosUser;

/**
 *
 * @author rkader
 */
public class ProfileServices {

    public void follow(Integer idfollower, Integer idfollowed) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/social/followSwitchWS/" + idfollower + "/" + idfollowed;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    public FosUser findUserById(Integer id){
        return null;
    }
}
