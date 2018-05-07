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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.souklemdina.entities.Commande;
import com.souklemdina.entities.FosUser;
import com.souklemdina.util.SessionUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class CommandeServices {
  /*  public ArrayList<Commande> getCommande(String json)
    {

        
        ArrayList<Commande> lstC = new ArrayList<>();
           // String jsonRes = new String(con.getResponseData()); 
            try {
            System.out.println("bbbbbbbbbbbbbb"+json);
            JSONParser j = new JSONParser();

            Map<String, Object> commandes = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("aaaaaaaaaa"+commandes);
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) commandes.get("root");

            for (Map<String, Object> obj : list) {
                Commande c = new Commande();
                               float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                c.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
               // c.setDateCommande(new Date((long)Float.parseFloat(obj.get("dateCommande").toString())));
               // c.setDateMax(new Date((long)Float.parseFloat(obj.get("dateMax").toString())));
                System.out.println("&&&&&&&"+c);
                lstC.add(c);
           
    }
            } catch (IOException ex) {
        }
        
        //NetworkManager.getInstance().addToQueue(con);
        return lstC;
}
                                    ArrayList<Commande> lstCmd = new ArrayList<>();

    public ArrayList<Commande> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/SoukLemdina5/web/commande/afficherCommandeJson/3");  

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                CommandeServices cs = new CommandeServices();
                lstCmd = cs.getCommande(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return lstCmd;
    }
    */
    
     public ArrayList<Commande> getCommandes(Integer id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina/web/commande/afficherCommandeJson/" + id;
        con.setUrl(Url);
        ArrayList<Commande> mapCommande = new ArrayList<>();
        con.addResponseListener((e) -> {
            CommandeServices cs = new CommandeServices();
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> commandes = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) commandes.get("root");

                for (Map<String, Object> obj : list) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    System.out.println(sdf.format(new Date((long)Float.parseFloat(obj.get("dateCommande").toString()))));
                    System.out.println(sdf.format(new Date((long)Float.parseFloat(obj.get("dateMax").toString()))));
                    //String dt= obj.get("dateCommande").toString().substring(4, 12)+obj.get("dateCommande").toString().substring(21, 25);
                    Commande c = new Commande((int)Float.parseFloat(obj.get("id").toString()),
                            sdf.format(new Date((long)Float.parseFloat(obj.get("dateCommande").toString()))),
                            sdf.format(new Date((long)Float.parseFloat(obj.get("dateMax").toString())))
                            );
                    mapCommande.add(c);
             
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return mapCommande;
    }
}
