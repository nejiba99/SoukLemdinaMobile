/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.souklemdina.entities.Workshop;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ParticipantWorkService {

    String bb = "";

    public void Switch(Integer idWork, Integer idUser) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/workshop/SwitchWS/" + idWork + "/" + idUser;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            System.out.println("idW: " + idWork + " idU : " + idUser);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public String SwitchBTn(Integer idWork, Integer idUser) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/workshop/SwitchBTn/"
                + idWork + "/"
                + idUser;
        System.out.println(idWork);
        System.out.println(idUser);
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

            try {

                JSONParser jp = new JSONParser();

                Map<String, Object> events = jp.parseJSON(new com.codename1.io.CharArrayReader(str.toCharArray()));
                System.out.println(events.toString());
                bb = events.toString();
                //  java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) events.get("root");

                /*  for (Map<String, Object> obj : list) {
               String b=obj.get("zeineb").toString();
                                System.out.println("zeeeeneib1"+obj.get("zeineb").toString());

               if (b.equals("true")){
                   
                                      bb=true;
               }
               else 
                   bb=false;
                }
                 System.out.println("zeeeeneib"+bb);*/
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return bb;
    }
}
