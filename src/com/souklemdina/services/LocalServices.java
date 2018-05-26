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
import com.souklemdina.entities.Local;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class LocalServices {

    
    public ArrayList<Local> afficheer(){
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/local/afficheMobile" ;
        con.setUrl(Url);
        ArrayList<Local> mapLocx = new ArrayList<>();
        con.addResponseListener((e) -> {
       
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> locx = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));
                System.out.println(locx);
                List<Map<String, Object>> lista = (List<Map<String, Object>>) locx.get("root");
                System.out.println("locauxSouk"+lista);
                for (Map<String, Object> obj : lista) {
                    System.out.println("aaaaaaaaaaaaaaa"+obj.get("superficieJ"));
                        Local loc = new Local( ((int)Float.parseFloat((obj.get("idJ").toString()))),
                                ((int) Float.parseFloat(obj.get("superficieJ").toString())),
                           ((int) Float.parseFloat(obj.get("prixJ").toString())),
                            obj.get("typeJ").toString(),
                            obj.get("descriptionJ").toString(),
                            obj.get("imageJ").toString(),
                               obj.get("adresseJ").toString(),
                                obj.get("telephoneJ").toString()
                              
                                );
                    System.out.println("souuk"+loc.getId());
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
    

    public ArrayList<Local> afficherUser(int idUser){
          ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/local/AfficheUserMobile"+idUser;
        con.setUrl(Url);
        ArrayList<Local> mapLocx = new ArrayList<>();
        con.addResponseListener((e) -> {
            
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> locx = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));
                System.out.println(locx);
                List<Map<String, Object>> lista = (List<Map<String, Object>>) locx.get("root");
                System.out.println("pfffffffffff"+lista);
                for (Map<String, Object> obj : lista) {
                    
                        Local loc = new Local(((int)Float.parseFloat(obj.get("idJ").toString())),
                                ((int) Float.parseFloat(obj.get("superficieJ").toString())),
                           ((int) Float.parseFloat(obj.get("prixJ").toString())),
                            obj.get("typeJ").toString(),
                            obj.get("descriptionJ").toString(),
                            obj.get("imageJ").toString(),
                               obj.get("adresseJ").toString(),
                                obj.get("telephoneJ").toString()
                          
                                );
                    System.out.println("pppp"+loc.getId());
                    System.out.println(loc.getImage());
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
   

    public void supprimerLocal(int id) {
        try {
            ConnectionRequest cnx = new ConnectionRequest();
            cnx.setPost(true);
            cnx.setContentType("application/json");
            String url = "http://localhost/SoukLemdina/web/local/supprimeMobile"+id;
            cnx.setUrl(url);
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

    public void modifierLocal(Local l) {
        try {
            ConnectionRequest cnx = new ConnectionRequest();
            cnx.setPost(true);
            cnx.setContentType("application/json");
            int id =l.getId();
            String adr = l.getAdresse();
            String tel = l.getTelephone();
            int prix = l.getPrix();
            String type = l.getType();
            String desc = l.getDescription();
            int sup = l.getSuperficie();
            String url = "http://localhost/SoukLemdina/web/local/modifierMobile"+id+"/"+adr+"/"+tel+"/"+type+"/"+prix+"/"+desc+"/"+sup;
            cnx.setUrl(url);
            System.out.println(l.getId());
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
    
    public void ajouterLocal(Local l,int idUser ){
        try {
            ConnectionRequest cnx = new ConnectionRequest();
            cnx.setPost(true);
            cnx.setContentType("application/json");
            int id = idUser;
            String adr = l.getAdresse();
            String tel = l.getTelephone();
            String type = l.getType();
            int prix = l.getPrix();
            String image = l.getImage();
            int superficie = l.getSuperficie();
            String desc = l.getDescription();
            String url = "http://localhost/SoukLemdina/web/local/ajoutMobile"+id+"/"+adr+"/"+tel+"/"+type+"/"+prix+"/"+image+"/"+superficie+"/"+desc;
            cnx.setUrl(url);
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

}
