/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.LigneWishlistProduit;
import com.souklemdina.entities.Panier;
import com.souklemdina.services.LigneWishlistServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.util.ImageViewerHerit;
import com.souklemdina.util.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author hatem
 */
public class AfficherLigneWishlist {

    private Form f;
    LigneWishlistServices lwps = new LigneWishlistServices();
    TextField txtQte;
    Button btnAjout;
    private Resources theme = UIManager.initFirstTheme("/theme");

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public AfficherLigneWishlist(Integer idu) {
        f = new Form("Ma Wishlist", new BoxLayout(BoxLayout.Y_AXIS));
        LigneWishlistProduit lwp = new LigneWishlistProduit();
        ArrayList<LigneWishlistProduit> lstlwp = lwps.getLignesWishlistProduit(idu);
        Image placeholder = Image.createImage(f.getWidth() / 3 - 4, f.getWidth() / 3 - 4, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        for (LigneWishlistProduit l : lstlwp) {
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            c2.setPreferredW(100);
            btnAjout = new Button("Ajouter au panier");
            Button btnSupp = new Button("Supprimer");
            Button btn1 = new Button();

            Button btn2 = new Button();
            Label lLibelle = new Label("Produit : " + l.getLibelle());
            Label lDesc = new Label();
            Label lQte = new Label("Quantité en stock : " + l.getQuantiteEnStock().toString());
            txtQte = new TextField();
            System.out.println("jawek behy" + txtQte);
            txtQte.setHint("Quantité");
            ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + l.getImage(),
                    "http://localhost/SoukLemdina5/web/uploads/images/" + l.getImage()));
            c1.add(lLibelle);
            c1.add(lQte);
            c1.add(btnAjout);
            c1.add(btnSupp);
            c2.add(img1);
            c2.add(btn1);
            c2.add(txtQte);
            c.add(c2);
            c.add(btn2);
            c.add(c1);
            f.add(c);
            btnAjout.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    PanierServices ps = new PanierServices();
                    Panier p = new Panier();
                    System.out.println("idp " + l.getIdProduit());
                    p.setId((int) (Double.parseDouble(l.getIdProduit())));
                    p.setIdu(idu);
                    p.setQte(txtQte.getText());
                    System.out.println(txtQte.getText());
                    p.setProdImg(l.getImage());
                    p.setLibelle(l.getLibelle());
                    p.setPrix(Double.parseDouble(l.getPrix()));
                    p.setPrixTot(Double.parseDouble(l.getPrix()) * Integer.parseInt(txtQte.getText()));
                    System.out.println("p " + p.toString());
                    ps.addPanier(p, SessionUser.getUser().getId());
                    AfficherPanier afp = new AfficherPanier();
                }
            });
            btnSupp.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    lwps.deleteLigneWishlist(l.getIdLigneWishlist());
                    AfficherLigneWishlist.this.getF();
                    // f.removeComponent(c);
                    //  Button ok = new Button("vous etes sur?");
                    // c1.replaceAndWait(btnAjout, ok, null);
                    AfficherLigneWishlist ijamennadhou9lbenna = new AfficherLigneWishlist(idu);
                    ijamennadhou9lbenna.getF().showBack();
                }
            });
        }
//        Toolbar tb = f.getToolbar();
//        //  AfficherLigneWishlist affw = new AfficherLigneWishlist(fu.getId());
//        //  AfficherPanier affp = new AfficherPanier().getF();
//        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_ADD_A_PHOTO, e -> {
//            new AfficherPanier().getF().show();
//        });
//        tb.addMaterialCommandToSideMenu("Commandes", FontImage.MATERIAL_AC_UNIT, e -> {
//            new AffichageCommande().getF().show();
//        });
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
