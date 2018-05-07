/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;


import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Profile;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class FosUserServices {
    public Profile findProfileById(Integer id){
        return null;
    }
public boolean login(String username,String password){
        ArrayList<FosUser> listuser= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukLemdina/web/fos/loginWS/"+username+"/"+password);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

                   Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(user);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");

            for (Map<String, Object> obj : list) {
                FosUser u = new FosUser();
                u.setId(Integer.parseInt(obj.get("id").toString()));
                u.setUsername(obj.get("username").toString());
                u.setEmail(obj.get("email").toString());
                u.setEnabled(Boolean.valueOf(obj.get("id").toString()));
                
                u.setPassword(obj.get("password").toString());
                u.setConfirmationToken(obj.get("confirmation_token").toString());
                u.setLastname(obj.get("nom").toString());
                u.setFirstname(obj.get("prenom").toString());
                u.setVille(obj.get("addresse").toString());
                u.setPhone((obj.get("phone").toString()));
                listuser.add(u);

             

            }

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        return listuser.isEmpty();
    }
    public FosUser getuser(String username,String password){
        ArrayList<FosUser> listuser= new ArrayList<>();
          ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukLemdina/web/fos/loginWS/"+username+"/"+password);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json=new String(con.getResponseData());
               try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(user);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
                   System.out.println("");

                FosUser u = new FosUser();
                u.setId((int)Float.parseFloat(user.get("id").toString()));
                u.setUsername(user.get("username").toString());
                u.setEmail(user.get("email").toString());
                u.setEnabled(Boolean.valueOf(user.get("enabled").toString()));
                
                u.setPassword(user.get("password").toString());
                u.setConfirmationToken(user.get("emailCanonical").toString());
                u.setLastname(user.get("lastname").toString());
                u.setFirstname(user.get("firstname").toString());
                u.setVille(user.get("ville").toString());
                u.setPhone((user.get("phone").toString()));
                listuser.add(u);
                System.out.println(listuser.get(0));

        } catch (IOException ex) {
                   System.out.println(ex.getMessage());
        }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listuser.get(0);
    }
}
