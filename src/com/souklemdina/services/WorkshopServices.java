/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.souklemdina.entities.Workshop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class WorkshopServices {

    public int nb = 0;

    public ArrayList<Workshop> getListWorks() {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/workshop/AfficheWorkshopWS";
        con.setUrl(Url);

        ArrayList<Workshop> lstWorks = new ArrayList<>();
        con.addResponseListener((e) -> {
            WorkshopServices ws = new WorkshopServices();
            String jsonj = new String(con.getResponseData());

            try {

                JSONParser jp = new JSONParser();

                Map<String, Object> events = jp.parseJSON(new com.codename1.io.CharArrayReader(jsonj.toCharArray()));
                System.out.println(events);

                java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) events.get("root");
// SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy ");

                for (Map<String, Object> obj : list) {//
                    Workshop w = new Workshop(
                            (int) Float.parseFloat(obj.get("id").toString()),
                            obj.get("nomWorkshop").toString(),
                            obj.get("adresse").toString(),
                            obj.get("type").toString(),
                            (int) Float.parseFloat(obj.get("nbPlace").toString()),
                            (int) ((double) obj.get("prix")),
                            format.format((new Date((long) Float.parseFloat(obj.get("dateDebut").toString())))),
                            format.format((new Date((long) Float.parseFloat(obj.get("dateFin").toString())))),
                            obj.get("description").toString(),
                            obj.get("image").toString(),
                            (int) Float.parseFloat(obj.get("idUser").toString())
                    );

                    System.out.println("aaaaaaaaaaa" + w.getIdUser());
                    lstWorks.add(w);

                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(lstWorks);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lstWorks;

    }

    public void Delete(Workshop w, Integer id, Integer idW) {
        try {

            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("http://localhost/SoukLemdina/web/workshop/DeleteMob/" + id + "/" + idW
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

    public void ajoutWork(Workshop p, Integer id) {
//            
//            Map<String, Object> df = (Map<String, Object>) Obj.get("finOffre");
//                        double t = (double) df.get("timestamp");
//                        long x = (long) (t * 1000L);
//                        String format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date(x));
        try {

            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("http://localhost/SoukLemdina/web/workshop/AjoutWorkshopWS/" + id
                    + "?nomWorkshop=" + p.getNomWorkshop()
                    + "&adresse=" + p.getAdresse()
                    + "&type=" + p.getType()
                    + "&dateDebut=" + p.getDateDebut()
                    + "&dateFin=" + p.getDateFin()
                    + "&nbPlace=" + p.getNbPlace()
                    + "&prix=" + p.getPrix()
                    + "&image=" + p.getImage()
                    + "&description=" + p.getDescription());
            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    public void modWork(Workshop p) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("http://localhost/SoukLemdina/web/workshop/UpdateWorkshopWS/"
                    + p.getId()
                    + "?nomWorkshop=" + p.getNomWorkshop()
                    + "&adresse=" + p.getAdresse()
                    + "&type=" + p.getType()
                    + "&dateDebut=" + p.getDateDebut()
                    + "&dateFin=" + p.getDateFin()
                    + "&nbPlace=" + p.getNbPlace()
                    + "&prix=" + p.getPrix()
                    + "&image=" + p.getImage()
                    + "&description=" + p.getDescription());

            connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });
            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    public int nbPar(int id) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/workshop/nbParticipantsW/" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            WorkshopServices ws = new WorkshopServices();
            String jsonj = new String(con.getResponseData());

            try {

                JSONParser jp = new JSONParser();

                Map<String, Object> events = jp.parseJSON(new com.codename1.io.CharArrayReader(jsonj.toCharArray()));
                System.out.println(events);

                java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) events.get("root");

                for (Map<String, Object> obj : list) {//
                    this.nb = (int) Float.parseFloat(obj.get("nb").toString());
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });
        System.out.println(nb);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return nb;

    }

}
