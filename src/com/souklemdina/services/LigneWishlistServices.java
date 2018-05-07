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
import com.souklemdina.entities.LigneWishlistProduit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class LigneWishlistServices {
    public ArrayList<LigneWishlistProduit> getLignesWishlistProduit(Integer id) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("id"+id);
        String Url = "http://localhost/SoukLemdina/web/ecommerce/afficherLWJson/"+id;
        con.setUrl(Url);
        ArrayList<LigneWishlistProduit> maplwishlist = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> lwishlists = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) lwishlists.get("root");
                for (Map<String, Object> obj : list) {
                    
                    /*       double t = (double) obj.get("dateLivraison");
                        long x = (long) (t * 1000L);
                    String format = new SimpleDateFormat("MM/dd/yyyy").format(new Date(x));
*/                    

                    LigneWishlistProduit lwp = new LigneWishlistProduit();
                    lwp.setIdLigneWishlist((int)Float.parseFloat(obj.get("idWishlist").toString()));
                    lwp.setLibelle(obj.get("libelle").toString());
                    lwp.setDescription(obj.get("description").toString());
                    lwp.setImage(obj.get("img").toString());
                    lwp.setQuantiteEnStock(obj.get("qte").toString());
                    lwp.setIdProduit(obj.get("idp").toString());
                    lwp.setPrix(obj.get("prix").toString());
                    
                    System.out.println("qte "+lwp.getQuantiteEnStock());
                    
                    maplwishlist.add(lwp);
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return maplwishlist;
    }
    
      public void addLigneWishlist(Integer idu, Integer idP) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
connReq.setUrl("http://localhost/SoukLemdina/web/ecommerce/ajouterLWJson/"+idu+"/"+idP );  
connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });

            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
      
      public void deleteLigneWishlist(Integer idW)
      {
            try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
connReq.setUrl("http://localhost/SoukLemdina/web/ecommerce/supprimerLWJson/"+idW);  
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
