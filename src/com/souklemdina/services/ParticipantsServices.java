/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.souklemdina.entities.Evenement;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ParticipantsServices {
    
    String bb="";
    public void Switch(Integer idEvent, Integer idUser) {
       
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/evenement/SwitchEV/" + idEvent + "/" + idUser;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
//      public boolean SwitchBTn(Integer idE, Integer idU) {
//          
//      
//        ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://localhost/SoukLemdina/web/event/SwitchBTn/" +
//                idE + "/" 
//                + idU;
//        con.setUrl(Url);
//                           
//        con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//
//             try {
//
//                JSONParser jp = new JSONParser();
//
//                Map<String, Object> events = jp.parseJSON(new com.codename1.io.CharArrayReader(str.toCharArray()));
//                System.out.println(events.toString());
//
//                java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) events.get("root");
//                for (Map<String, Object> obj : list) {
//               String b=obj.get("saf").toString();
//               if (b.equals("true")){
//                                      bb=true;
//
//               }
//               else 
//                   bb=false;
//
//                  
//               
//                }
//
//           } catch (IOException ex) {
//                System.err.println(ex.getMessage());
//            }
//        } );
//        NetworkManager.getInstance().addToQueueAndWait(con);
//    
//return bb;
//                }
      
      
      
    public String SwitchBTn(Integer idE, Integer idU) {
          
      
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/evenement/SwitchBTn/" +
                idE + "/" 
                + idU;
        con.setUrl(Url);
                           
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());

             try {

                JSONParser jp = new JSONParser();

                Map<String, Object> events = jp.parseJSON(new com.codename1.io.CharArrayReader(str.toCharArray()));
                System.out.println(events.toString());
         bb=events.toString();
             
           } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } );
        NetworkManager.getInstance().addToQueueAndWait(con);
    
return bb;
                }  
      
      
      
}
