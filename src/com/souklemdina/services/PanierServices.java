/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.souklemdina.entities.Commande;
import com.souklemdina.entities.Panier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author hatem
 */
public class PanierServices {

    Database db;
    boolean created;

    public ArrayList<Panier> getPanier() {
        createDB();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/SoukLemdina5/web/commande/panierJson";
        con.setUrl(Url);
        ArrayList<Panier> mapPanier = new ArrayList<>();
        con.addResponseListener((e) -> {
            PanierServices ps = new PanierServices();
            String jsonRes = new String(con.getResponseData());
            try {
                JSONParser j = new JSONParser();

                Map<String, Object> paniers = j.parseJSON(new CharArrayReader(jsonRes.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) paniers.get("root");

                for (Map<String, Object> obj : list) {
                    /*  Panier p = new Panier();
                     System.out.println("img"+obj.get("prodImg"));
                     p.setProdImg(obj.get("prodImg").toString());
                     p.setLibelle(obj.get("libelle").toString());*/
                   // p.setQte(obj.get("qte").toString());
                    //  p.setPrixTot((Double)obj.get("prixTot"));

                  //  mapPanier.add(p);
                }

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueue(con);

        return mapPanier;
    }

    public void createDB() {
        created = Database.exists("panier");
        try {
            db = Database.openOrCreate("panier");
            if (created == false) {
                db.execute("create table panier( id integer,idu integer,  prodImg varchar,  libelle varchar, qte varchar, prix number, prixTot number)");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("créer");

    }

    public void addPanier(Panier p, Integer idu) {
      //  createDB();

      //  System.out.println("slt");
               if(!findPanier(idu, p.getId()))
               {
        try {
            db.execute("insert into `panier` (id,idu,prodImg,libelle,qte,prix,prixTot) values('" + p.getId() + "','"+idu+"','"+  p.getProdImg() + "','" + p.getLibelle() + "','" + p.getQte() +"','" + p.getPrix()+ "','" +Double.parseDouble(p.getQte())*p.getPrix()+ "')");
       db.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("Ramy mch Mgamel barcha");
               }
    }
    public Boolean findPanier(Integer idu, Integer idp) {
       createDB();
        Boolean b = false;

        try {
            System.out.println("aaaaaaaaaaaaaaaaaaa");
            Cursor cs = db.executeQuery("select * from panier where idU="+idu+" and id="+idp);
if (cs.next())
{
    b=true;
}

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
                System.out.println("b "+b);

        return b;
    }

    public ArrayList<Panier> returnPanier(Integer idu) {
        ArrayList<Panier> lstp = new ArrayList();

        try {
            System.out.println("aaaaaaaaaaaaaaaaaaa");
            Cursor cs = db.executeQuery("select * from panier where idU="+idu);

            while (cs.next()) {
                Row r = cs.getRow();

                Panier p = new Panier(r.getInteger(0), r.getInteger(1), r.getString(2), r.getString(3), r.getString(4),r.getDouble(5),r.getDouble(6));
                lstp.add(p);

            }
            db.close();
            System.out.println("panier"+lstp);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lstp;
    }
    
    public void DeleteFromPanier(Integer idu, Integer idp)
    {        createDB();

        try{
            db.execute("delete from panier where id="+idp+" and idu="+idu);
        }catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    
    public void updateQtePanier(Panier p, Integer idu, String qte) {
        
        createDB();
 try {
            db.execute("update panier set qte="+qte+" where id="+p.getId()+" and idu="+idu);
                    
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("modifié");
               
    }
}
