/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.InfoPersonnel;
import com.souklemdina.entities.Panier;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.util.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author hatem
 */
public class AjouterLigneDeCommande {
    private Form f;

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    public AjouterLigneDeCommande()
    {            
        System.out.println("X = "+AfficherLigneCommandes.X);

                   // System.out.println("hédhi l'adresse: "+acf.);
                if (AfficherLigneCommandes.X==1)
                {
                    FosUser fu = new FosUser(3);
               ArrayList<Panier> lstp= new ArrayList<>();
        PanierServices ps = new PanierServices();

                     LigneDeCommandeServices lcs = new LigneDeCommandeServices();
     //  lcs.addCommandeAndLigneDeCommande(fu.getId());
  ps.createDB();
       // InfoPersonnel ip = new InfoPersonnel();
     //  lstp=ps.returnPanier(fu.getId());
       
                   lcs.updateLigneDeCommande();
       
       AffichageCommande ac = new AffichageCommande();
                }
                
                else{
                    
             
     FosUser fu = new FosUser(3);
               ArrayList<Panier> lstp= new ArrayList<>();
        PanierServices ps = new PanierServices();

                     LigneDeCommandeServices lcs = new LigneDeCommandeServices();
       lcs.addCommandeAndLigneDeCommande(SessionUser.getUser().getId());
  ps.createDB();
       // InfoPersonnel ip = new InfoPersonnel();
       lstp=ps.returnPanier(SessionUser.getUser().getId());
       for(Panier p : lstp)
       {
        //   System.out.println("idprod "+p.getId());
          // System.out.println("iduuuuu "+p.getIdu());
                   lcs.addLigneDeCommande(SessionUser.getUser().getId(), p);
ps.DeleteFromPanier(SessionUser.getUser().getId(), p.getId());      
       }
       AffichageCommande ac = new AffichageCommande();
          }
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
