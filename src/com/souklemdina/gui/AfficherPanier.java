/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.InfoPersonnel;
import com.souklemdina.entities.Panier;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.util.ImageViewerHerit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class AfficherPanier {

    private Form f;
    Button btnValider;
    Button btnSupp;
    TextField t3;
    PanierServices ps = new PanierServices();
            FosUser fu =new FosUser(3);
        private Resources theme = UIManager.initFirstTheme("/theme");


    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public AfficherPanier() {
        f = new Form("Panier", new BoxLayout(BoxLayout.Y_AXIS));
        ArrayList<Panier> lstp = new ArrayList();
        //ps.getPanier();
        /* lstp=ps.getPanier();
         for(Panier p : lstp)
         {
         System.out.println(p.getLibelle());
         }*/

//Panier p = new Panier(4,4,"a.jpg","karhba","55",99.);
        ps.createDB();
        // ps.addPanier(p,p.getIdu());
        lstp = ps.returnPanier(3);
        Image placeholder = Image.createImage(f.getWidth() / 3 - 4, f.getWidth() / 3 - 4, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        btnValider = new Button("Valider");

        for (Panier pan : lstp) {
            // LigneDeCommandeServices lcs = new LigneDeCommandeServices();
            // InfoPersonnel ip = new InfoPersonnel("gamra","b3id barcha","coin fi lgamra",2047,"25262728");
            // lcs.addLigneDeCommande(3,pan);

            /* System.out.println("http://localhost/SoukLemdina5/web/commande/ajoutLigneCommandeJson/" + 3 +"/" + pan.getId()+ 
             "?prixtot=" + pan.getPrixTot()+ 
             "&qte=" + pan.getQte()+ 
             "&adresse=" + ip.getAdresse()
             + "&adresse2=" + ip.getAdresse2()+ 
             "&ville=" + ip.getVille()+ 
             "&codepostal=" + ip.getCodePostal()+
             "&numtel=" + ip.getNumTel());*/
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            //Label l1 = new Label(pan.getProdImg().toString());

            ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + pan.getProdImg(),
                    "http://localhost/SoukLemdina5/web/uploads/images/" + pan.getProdImg()));
                    //l1.setAutoSizeMode(true);

            Label l2 = new Label("Produit : " + pan.getLibelle());
            Label l3 = new Label("Quantité : ");
            t3 = new TextField(pan.getQte());
            Label l4 = new Label("Prix Total : " + pan.getPrixTot().toString());
            Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            /*Button btn3 = new Button();

             c.add(btn3);*/
            c.add(img1);

            /* Button btn1 = new Button();
             c.add(btn1);*/
            c1.add(l2);
            c2.add(l3);
            c2.add(t3);
            c1.add(c2);
            c1.add(l4);
            c.add(c1);

            t3.addDataChangedListener(new DataChangedListener() {

                @Override
                public void dataChanged(int type, int index) {
                    PanierServices ps = new PanierServices();
                    ps.updateQtePanier(pan, pan.getIdu(), t3.getText());
                }
            });

            Button btn = new Button();
            btnSupp = new Button("Supprimer");
            f.add(c);
            f.add(btnSupp);
            btnSupp.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    PanierServices ps = new PanierServices();
                    System.out.println("idu " + pan.getIdu());
                    System.out.println("id " + pan.getId());
                    ps.DeleteFromPanier(pan.getIdu(), pan.getId());
                    AfficherPanier ijamennadhou9lbenna = new AfficherPanier();
                    ijamennadhou9lbenna.getF().showBack();
                }
            });
            f.add(btn);
        }

        f.add(btnValider);
        btnValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               
                AjoutCommandeForm acf = new AjoutCommandeForm();
            }
        });
        Toolbar tb = f.getToolbar();
          
        tb.addMaterialCommandToSideMenu("WishList", FontImage.MATERIAL_ADD_A_PHOTO, e->{new AfficherLigneWishlist(fu.getId()).getF().show();});
        tb.addMaterialCommandToSideMenu("Commandes", FontImage.MATERIAL_AC_UNIT, e->{new AffichageCommande().getF().show();});
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
