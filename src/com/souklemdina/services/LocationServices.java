/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import com.souklemdina.entities.Location;
import java.io.IOException;

import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class LocationServices {
    
    public void ajoutLocation (int id,int idUser,String dateDeb,String dateFin){
       try {
            ConnectionRequest cnx = new ConnectionRequest();
            cnx.setPost(true);
            cnx.setContentType("application/json");
            cnx.setUrl("http://localhost/SoukLemdina/web/local/AjoutLocation" +id+"/"+ dateDeb + "/" + dateFin + "/"+idUser );
            cnx.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
                    String str = new String(cnx.getResponseData());
                    System.out.println(str);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(cnx);
        } catch (Exception e) {
            e.getMessage();
        }
    }
   
        public ArrayList<Location> afficherLocations(int idLoc){
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/local/AfficheLocMobile"+idLoc ;
        con.setUrl(Url);
        ArrayList<Location> mapLocx = new ArrayList<>();
        con.addResponseListener((e) -> {
        
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> locx = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));
                System.out.println(locx);
                List<Map<String, Object>> lista = (List<Map<String, Object>>) locx.get("root");
                System.out.println("pfff"+lista);
                for (Map<String, Object> obj : lista) {
                    System.out.println("nounou"+obj.get("idUserLoc"));
                        Location loc = new Location( ((int)Float.parseFloat((obj.get("idUserLoc").toString()))),
                                ((int) Float.parseFloat(obj.get("idUs").toString())),
                            new Date((long) Float.parseFloat(obj.get("dateD").toString())),
                            new Date((long) Float.parseFloat(obj.get("dateF").toString()))
                                );
                    System.out.println("lll"+loc.getIdUser());
                    mapLocx.add(loc);
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(mapLocx);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return mapLocx;
    }
        
        
        public boolean verif (Date dd,Date df,int id){
            boolean b = true;
            LocationServices ls = new LocationServices ();
        ArrayList<Location> listLocal = ls.afficherLocations(id);
        for (Location l :listLocal){
            
        if (((l.getDateDebutLocation().toString().equals(dd.toString()))&& (l.getDateDebutLocation().toString().equals(df.toString()))) ||((l.getDateFinLocation().toString().equals(dd.toString())) && (l.getDateFinLocation().toString().equals(df.toString())))){
        b=false;
        }
        }
        return b;
        }
    
}
