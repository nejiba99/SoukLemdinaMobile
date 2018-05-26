/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;

import com.souklemdina.entities.Evenement;
import com.souklemdina.entities.ParticipantsEvents;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class EventServices {

//    public ArrayList<Evenement> getlistEvents(Integer id) {
//        ArrayList<Evenement> listEvents = new ArrayList<>();
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/SoukLemdina/web/evenement/find"+id);
//        con.addResponseListener((NetworkEvent evt) -> {
//            JSONParser jsonp = new JSONParser();
//            //JSON est un type de réponse, c'est le format le plus souple et flexible en treme de manipulation;
//            //Résponse rapide
//            try {
//                Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
//                System.out.println(events);
////                    
//                List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
//                for (Map<String, Object> obj : list) {
//                    Evenement event = new Evenement();
//                    event.setId((int) Float.parseFloat(obj.get("id").toString()));
//                    event.setNomEvenement(obj.get("nomEvenement").toString());
//                    event.setAdresse(obj.get("adresse").toString());
//                    new Date((long) Float.parseFloat(obj.get("dateDebut").toString()));
//                    new Date((long) Float.parseFloat(obj.get("dateFin").toString()));
//                    event.setPhoto(obj.get("photo").toString());
//                    event.setDescription(obj.get("description").toString());
//                    event.setPrix((int) (double) obj.get("prix"));
//                    event.setNbPlace((int) obj.get("nbPlace"));
//                    event.setPhoto(obj.get("photo").toString());
//                    event.setIdUser((int) Float.parseFloat(obj.get("idUser").toString()));
//
//                    listEvents.add(event);
//
//                }
//            } catch (IOException ex) {
//                System.err.println(ex.getMessage());
//
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return listEvents;
//    }
//
//}
    public ArrayList<Evenement> getListEvents() {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/evenement/all";
        con.setUrl(Url);

        ArrayList<Evenement> listEvents = new ArrayList<>();
        con.addResponseListener((e) -> {
            EventServices Evs = new EventServices();
            String jsonr = new String(con.getResponseData());

            try {

                JSONParser j = new JSONParser();
                Map<String, Object> events = j.parseJSON(new CharArrayReader(jsonr.toCharArray()));
                System.out.println(events);
                List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
                
                for (Map<String, Object> obj : list) {
               SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                    Evenement ev = new Evenement(
                            (int) Float.parseFloat(obj.get("id").toString()),
                            obj.get("nomEvenement").toString(),
                            obj.get("type").toString(),
                          format.format(new Date((long) Float.parseFloat(obj.get("dateDebut").toString()))),
                         format.format(new Date((long) Float.parseFloat(obj.get("dateFin").toString()))),

                            obj.get("photo").toString(),
                            ((Double) obj.get("prix")),
                            (int) Float.parseFloat(obj.get("nbPlace").toString()),
                            obj.get("description").toString(),
                            (int) Float.parseFloat(obj.get("rating").toString()),


                            (int) Float.parseFloat(obj.get("idUser").toString()));
                    listEvents.add(ev);

                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(listEvents);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;

    }

    public void addEvent(Evenement ev, Integer id) {
        
        try {
            
          //  ev.setRating(0);
            
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("http://localhost/SoukLemdina/web/evenement/addM/" + id
                    + "?nomEvenement=" + ev.getNomEvenement()
                    + "&dateDebut=" + ev.getDateDebut()
                 + "&dateFin=" + ev.getDateFin()
                + "&heure=" + ev.getHeures()
                    + "&adresse=" + ev.getAdresse()
                    + "&prix=" + ev.getPrix()
                    + "&description=" + ev.getDescription()
                    + "&nbPlace=" + ev.getNbPlace()
                    + "&type=" + ev.getType()
                    + "&photo=" + ev.getPhoto()
                    + "&rating=" + 0

                    
            );
       

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
    
    
 public void edEvent(Evenement ev,Boolean rat) {
        try {
                       // ev.setRating(0);

            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("http://localhost/SoukLemdina/web/evenement/modM/"
                    + ev.getId()+"/"+rat
             + "?nomEvenement=" + ev.getNomEvenement()
                    + "&dateDebut=" + ev.getDateDebut()
                    + "&dateFin=" + ev.getDateFin()
                    + "&heure=" + ev.getHeures()
                    + "&adresse=" + ev.getAdresse()
                    + "&prix=" + ev.getPrix()
                    + "&description=" + ev.getDescription()
                    + "&nbPlace=" + ev.getNbPlace()
                    + "&type=" + ev.getType()
                    + "&photo=" + ev.getPhoto()
                    + "&rating=" + ev.getRating()
                    
            );
            

            
            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    } 
 
    
    
   public void Participer(ParticipantsEvents pev,Integer idUser,Integer idEvenement){
       try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://localhost/SoukLemdina/web/evenement/participerEM/" + idUser + "/" +idEvenement
            );
        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
       }
   
   
   
   public void nParticiper(ParticipantsEvents pev,Integer id){
       try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://localhost/SoukLemdina/web/evenement/participerEM/" + id
            );
        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
       }
   
    
   public void Delete(Evenement ev,Integer id,Integer idE){
       try{
           
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
             connReq.setUrl("http://localhost/SoukLemdina/web/evenement/DeleteMob/" + id+"/"+idE
            );
        

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
       }
   
   
   
       
   } 
    
    
    
    


