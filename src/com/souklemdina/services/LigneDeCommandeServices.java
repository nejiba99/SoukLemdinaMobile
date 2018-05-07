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
import com.souklemdina.entities.InfoPersonnel;
import com.souklemdina.entities.LigneDeCommandeProduit;
import com.souklemdina.entities.Panier;
import com.souklemdina.gui.AffichageCommande;
import com.souklemdina.gui.AjoutCommandeForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class LigneDeCommandeServices {
    
    
    public ArrayList<LigneDeCommandeProduit> getLignesCommandeProduit(Integer id) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("id"+id);
        String Url = "http://localhost/SoukLemdina/web/commande/afflcJson/"+id;
        con.setUrl(Url);
        ArrayList<LigneDeCommandeProduit> mapPanier = new ArrayList<>();
        con.addResponseListener((e) -> {
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> lcommandes = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) lcommandes.get("root");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (Map<String, Object> obj : list) {
                    
                    /*       double t = (double) obj.get("dateLivraison");
                        long x = (long) (t * 1000L);
                    String format = new SimpleDateFormat("MM/dd/yyyy").format(new Date(x));
*/                    

                    LigneDeCommandeProduit lcp = new LigneDeCommandeProduit();
                    lcp.setIdLigneDeCommande((int)Float.parseFloat(obj.get("id").toString()));
                    lcp.setLibelle(obj.get("libelle").toString());
                    lcp.setDescription(obj.get("description").toString());
                    lcp.setPrixTotal((double)obj.get("prixTotal"));
                    lcp.setDateLivraison(sdf.format(new Date((long)Float.parseFloat(obj.get("dateLivraison").toString()))));
                    lcp.setAdresse(obj.get("adresse1").toString());
                    lcp.setAdresse2(obj.get("adresse2").toString());
                    lcp.setVille(obj.get("ville").toString());
                    lcp.setCodePostalforAff(obj.get("codePostal").toString());
                    lcp.setQuantiteCommander(obj.get("qte").toString());
                    lcp.setNumTel(obj.get("numTel").toString());
                  //  System.out.println(obj.get("numTel").toString());
              
                    
                    mapPanier.add(lcp);
               //    System.out.println("id commande : "+lcp.getPrixTotal());
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return mapPanier;
    }
    
     public void addCommandeAndLigneDeCommande(Integer idu) {
        try {
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
connReq.setUrl("http://localhost/SoukLemdina/web/commande/ajoutCommandeJson/"+idu );  
connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });

            NetworkManager.getInstance().addToQueueAndWait(connReq);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
               //  AjoutCommandeForm acf = new AjoutCommandeForm().ip;
            InfoPersonnel ip = AjoutCommandeForm.ip;

    public void addLigneDeCommande(Integer idu, Panier p) {
                    System.out.println("idUUUUUUUUU"+idu);

        try {
            
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            PanierServices ps = new PanierServices();
            
                        System.out.println("idUUUUUUUUU"+idu);

          //  InfoPersonnel ip = new InfoPersonnel(/*"gamra","b3id barcha","coin fi lgamra",2047,"25262728"*/);


            System.out.println("hédhom les infos : "+ip);
connReq.setUrl("http://localhost/SoukLemdina/web/commande/ajoutLigneCommandeJson/" + idu +"/"+ p.getId()+ 
                    "?prixtot=" + p.getPrixTot()+ 
                    "&qte=" + p.getQte()+ 
                    "&adresse=" + ip.getAdresse()
                    + "&adresse2=" + ip.getAdresse2()+ 
                    "&ville=" + ip.getVille()+ 
                    "&codepostal=" + ip.getCodePostal()+
                    "&numtel=" + ip.getNumTel());  

        /*    System.out.println("http://localhost/SoukLemdina5/web/commande/ajoutLigneCommandeJson/" + idu +"/"+ p.getId()+ 
                    "?prixtot=" + p.getPrixTot()+ 
                    "&qte=" + p.getQte()+ 
                    "&adresse=" + ip.getAdresse()
                    + "&adresse2=" + ip.getAdresse2()+ 
                    "&ville=" + ip.getVille()+ 
                    "&codepostal=" + ip.getCodePostal()+
                    "&numtel=" + ip.getNumTel()); 
           */
connReq.addResponseListener((e) -> {
                String str = new String(connReq.getResponseData());
                System.out.println(str);
            });

            NetworkManager.getInstance().addToQueue(connReq);
            
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
    
    public void updateLigneDeCommande()
    {
        try {
            System.out.println("idddddddddddddddddddddd mta3 commande  : "+AffichageCommande.idC);
            System.out.println("adresseeeeeee mta3 commande  : "+ip.getAdresse());
            System.out.println("http://localhost/SoukLemdina/web/commande/modifierlcJson/" + AffichageCommande.idC + 
                    "?adresse=" + ip.getAdresse()+
                     "&adresse2=" + ip.getAdresse2()+ 
                    "&ville=" + ip.getVille()+ 
                    "&codepostal=" + ip.getCodePostal()+
                    "&numtel=" + ip.getNumTel());
            ConnectionRequest connReq = new ConnectionRequest();
            connReq.setPost(true);
            connReq.setContentType("application/json");
            connReq.setUrl("http://localhost/SoukLemdina/web/commande/modifierlcJson/" + AffichageCommande.idC + 
                    "?adresse=" + ip.getAdresse()+
                     "&adresse2=" + ip.getAdresse2()+ 
                    "&ville=" + ip.getVille()+ 
                    "&codepostal=" + ip.getCodePostal()+
                    "&numtel=" + ip.getNumTel()) ;
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
