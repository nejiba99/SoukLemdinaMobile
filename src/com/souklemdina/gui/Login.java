/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Panier;
import com.souklemdina.entities.Produit;
import com.souklemdina.entities.Profile;
import com.souklemdina.services.FosUserServices;
import com.souklemdina.services.LigneWishlistServices;
import com.souklemdina.services.PanierServices;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.services.ProfileServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class Login extends Form {

    private Form f;
    Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);

    public Login() {

        try {
            f = new Form("Login", BoxLayout.y());
            Resources theme = UIManager.initFirstTheme("/theme");

            ImageViewer logo = new ImageViewer(Image.createImage("/app.png"));
            logo.getImage().scale(300, 300);

            TextField login = new TextField("", "Tapez votre nom d'utilisateur");
            TextField password = new TextField("", "Tapez votre mot de passe", 20, TextField.PASSWORD);
            
            Style loginStyle = login.getAllStyles();
            Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
            loginStyle.setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xffffff).
                    strokeColor(0x7EC0EE).
                    strokeOpacity(80).
                    stroke(borderStroke));
            loginStyle.setMarginUnit(Style.UNIT_TYPE_DIPS);
            loginStyle.setMargin(Component.BOTTOM, 5);
            loginStyle.setMargin(Component.TOP, 5);

            Style passwordStyle = password.getAllStyles();
            passwordStyle.setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xffffff).
                    strokeColor(0x7EC0EE).
                    strokeOpacity(80).
                    stroke(borderStroke));

            passwordStyle.setMargin(Component.BOTTOM, 5);

            Button loginButton = new Button("Login");

            loginButton.addActionListener(new ActionListener() {
                private String newfilePath = "";
                ProduitServices ps = new ProduitServices();

                @Override
                public void actionPerformed(ActionEvent evt) {

                    FosUserServices us = new FosUserServices();
                    if ((login.getText().equals("")) || ((password.getText().equals("")))) {
                        Dialog.show("Vérifiez Vos Champs", "Vérifiez Vos Champs", null, "ok");
                    }
                    System.out.println(us.login(login.getText(), password.getText()));
                    if (us.login(login.getText(), password.getText())) {
                        try {
                            FosUser u = us.getuser(login.getText(), password.getText());
                            SessionUser.setUser(u);
                            ProfileServices psss = new ProfileServices();
                            SessionUser.setProfile(psss.findProfileByIdUser(u.getId()));

                            Container cn = new Container(new FlowLayout(Component.CENTER));

                            SpanLabel l = new SpanLabel(SessionUser.getUser().getFirstname() + " " + SessionUser.getUser().getLastname());
                            l.getAllStyles().setFgColor(0xd03958);
                            cn.add(l);

                            Form home = new Form("Home page", BoxLayout.y());

                            ImageViewer logo = new ImageViewer(Image.createImage("/app.png"));
                            logo.getImage().scale(300, 300);
                            logo.getAllStyles().setMarginBottom(20);
                            logo.getAllStyles().setMarginTop(20);
                            home.add(logo);
                            Container vc2 = new Container(BoxLayout.y());
//                            Button b1 = new Button("Les locaux");
//                            Button b2 = new Button("Les évènements");
//                            Button b3 = new Button("Les workshops");
//                            Button b4 = new Button("Mes commandes");
//                            Button b5 = new Button("Hdith ElSouk");
//                            vc2.add(b1).add(b2).add(b3).add(b4).add(b5);
//                            b1.addActionListener((evt1) -> {
//                                GUILocal gl = new GUILocal();
//                                gl.getF().getAllStyles().setBgColor(0x696969);
//                                gl.getF().show();
//                                Image icon = theme.getImage("back-command.png");
//                                gl.getF().getToolbar().addCommandToLeftBar("", icon, e -> {
//                                    home.showBack();
//                                });
//                            });
//                            b2.addActionListener((evt1) -> {
//                                ListEvents ac = new ListEvents();
//                                Image icon = theme.getImage("back-command.png");
//                                ac.fe.getToolbar().addCommandToLeftBar("", icon, e -> {
//                                    home.showBack();
//                                });
//                            });
//                            b3.addActionListener((evt1) -> {
//                                ListeWorks ac = new ListeWorks();
//                                ac.getf().show();
//                                Image icon = theme.getImage("back-command.png");
//                                ac.getf().getToolbar().addCommandToLeftBar("", icon, e -> {
//                                    home.showBack();
//                                });
//                            });
//                            b4.addActionListener((evt1) -> {
//                                AffichageCommande ac = new AffichageCommande();
//                                ac.getF().show();
//                                Image icon = theme.getImage("back-command.png");
//                                ac.getF().getToolbar().addCommandToLeftBar("", icon, e -> {
//                                    home.showBack();
//                                });
//                            });
//                            b5.addActionListener((evt1) -> {
//                                AccueilSocial ac = new AccueilSocial();
//                                ac.getF().show();
//                                Image icon = theme.getImage("back-command.png");
//                                ac.getF().getToolbar().addCommandToLeftBar("", icon, e -> {
//                                    home.showBack();
//                                });
//                            });
                            home.add(vc2);

                            Form about = new Form("About", new FlowLayout(CENTER, CENTER));

                            Form souk = new Form("Le souk", BoxLayout.y());

                            ProduitServices ps = new ProduitServices();

                            ArrayList<Produit> listProduits = ps.getList2();

                            Toolbar tb1 = home.getToolbar();
                            about.getToolbar().addCommandToOverflowMenu("Logout", theme.getImage("back-command.png"), b -> f.showBack());

                            tb1.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
                                home.show();
                            });
                            tb1.addMaterialCommandToSideMenu("Hdith Elsouk", FontImage.MATERIAL_CHAT, e -> {
                                AccueilSocial ac = new AccueilSocial(home);
                                ac.getF().show();
//                                Image icon = theme.getImage("back-command.png");
//                                ac.getF().getToolbar().addCommandToLeftBar("", icon, e1 -> {
//                                    home.showBack();
//                                });
                            });
                            tb1.addMaterialCommandToSideMenu("Les Workshops", FontImage.MATERIAL_WORK, e -> {
                                ListeWorks ac = new ListeWorks();
                                ac.getf().show();
                                Image icon = theme.getImage("back-command.png");
                                ac.getf().getToolbar().addCommandToLeftBar("", icon, e1 -> {
                                    home.showBack();
                                });
                            });
                            tb1.addMaterialCommandToSideMenu("Les évènements", FontImage.MATERIAL_EVENT, e -> {
                                ListEvents ac = new ListEvents();
                                ac.fe.show();
                                Image icon = theme.getImage("back-command.png");
                                ac.fe.getToolbar().addCommandToLeftBar("", icon, e1 -> {
                                    home.showBack();
                                });
                            });
                            tb1.addMaterialCommandToSideMenu("Les locaux", FontImage.MATERIAL_ADD_LOCATION, e -> {
                                GUILocal gl = new GUILocal();
                                gl.getF().getAllStyles().setBgColor(0x696969);
                                gl.getF().show();
                                Image icon = theme.getImage("back-command.png");
                                gl.getF().getToolbar().addCommandToLeftBar("", icon, e1 -> {
                                    home.showBack();
                                });
                            });
                            tb1.addMaterialCommandToSideMenu("Le souk", FontImage.MATERIAL_SHOP, e -> {
                                souk.show();
                            });
                            tb1.addMaterialCommandToSideMenu("Mon panier", FontImage.MATERIAL_SHOPPING_BASKET, e -> {
                                AfficherPanier ap = new AfficherPanier();
                                
                                Image icon = theme.getImage("back-command.png");
                                ap.getF().getToolbar().addCommandToLeftBar("", icon, e1 -> {
                                    home.showBack();
                            });
                                });
                            tb1.addMaterialCommandToSideMenu("Ma wishlist", FontImage.MATERIAL_LIST, e -> {
                                AfficherLigneWishlist alw = new AfficherLigneWishlist(SessionUser.getUser().getId());
                                
                                Image icon = theme.getImage("back-command.png");
                                alw.getF().getToolbar().addCommandToLeftBar("", icon, e1 -> {
                                    home.showBack();
                            });
                                });
                            tb1.addMaterialCommandToSideMenu("Mes commandes", FontImage.MATERIAL_SHOPPING_CART, e -> {
                                AffichageCommande ac = new AffichageCommande();
                                ac.getF().show();
                                Image icon = theme.getImage("back-command.png");
                                ac.getF().getToolbar().addCommandToLeftBar("", icon, e1 -> {
                                    home.showBack();
                                });
                            });
                            tb1.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_PERSON, e -> {
                                about.show();
                            });
                            home.show();

                            for (Produit t : listProduits) {
                                Toolbar tb = getToolbar();
                                Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
                                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + t.getImage()));

// ImageViewer img = new ImageViewer(res.getImage(t.getImage()));
                                Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                c1.setScrollableY(false);
                                c1.getAllStyles().setBorder(RoundBorder.create().rectangle(true).
                                        color(0xffffff).
                                        strokeColor(0x7EC0EE).
                                        strokeOpacity(80).
                                        stroke(borderStroke));
                                c1.getAllStyles().setBackgroundType(Byte.MIN_VALUE);
//                            c1.getStyle().setBgColor(0xe6e5f4);
                                c1.getStyle().setBgTransparency(255);
                                c1.getAllStyles().setMarginBottom(15);
                                c1.getAllStyles().setMarginLeft(15);
                                c1.getAllStyles().setMarginRight(15);
                                c1.getAllStyles().setMarginTop(15);

                                Label name = new Label(t.getLibelle());
                                name.setUIID("Label");

                                name.getAllStyles().setFgColor(0x3958d0);
                                name.getAllStyles().setFont(font);
                                name.setAlignment(CENTER);

                                name.addPointerPressedListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        System.out.println(".actionPerformed()");
                                        Form product = new Form(t.getLibelle(), BoxLayout.y());

                                        Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
                                        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                        ImageViewer pImg = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + t.getImage()));
                                        pImg.getAllStyles().setMarginTop(15);
                                        //Container cImg = new Container(BoxLayout.x());
                                        Container cName = new Container(BoxLayout.x());
                                        Container cDescription = new Container(BoxLayout.x());
                                        Container cPrix = new Container(BoxLayout.x());
                                        Container cQuantite = new Container(BoxLayout.x());
                                        Container cPromotion = new Container(BoxLayout.x());
                                        Container cType = new Container(BoxLayout.x());
                                        Container cCategorie = new Container(BoxLayout.x());
                                        Container cbtn = new Container(BoxLayout.x());

                                        Button bnt1 = new Button("Ajouter au panier");
                                        bnt1.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                PanierServices ps = new PanierServices();
                                                Panier p = new Panier();
                                                p.setId(t.getId());
                                                p.setIdu(SessionUser.getUser().getId());
                                                p.setLibelle(t.getLibelle());
                                                p.setPrix(t.getPrix());
                                                p.setQte("1");
                                                p.setProdImg(t.getImage());
                                                ps.addPanier(p, SessionUser.getUser().getId());
                                            }
                                        });
                                        Button bnt2 = new Button("Wishlist");
                                        bnt2.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
                                                LigneWishlistServices lws= new LigneWishlistServices();
                                                lws.addLigneWishlist(SessionUser.getUser().getId(), t.getId());
                                            }
                                        });

                                        cbtn.add(bnt1);
                                        cbtn.add(bnt2);

                                        cName.getAllStyles().setMarginTop(15);

                                        Label lbPname = new Label("Libelle : ");
                                        Label lbPdescription = new Label("Description : ");
                                        Label lbPprice = new Label("Prix : ");
                                        Label lbPquantite = new Label("Quantité disponible : ");
                                        Label lbPpromotion = new Label("Promotion : ");
                                        Label lbPtype = new Label("Type : ");
                                        Label lbPcategorie = new Label("Catégorie : ");

                                        lbPname.setUIID("Label");
                                        lbPname.getAllStyles().setFgColor(0x3958d0);
                                        lbPname.getAllStyles().setFont(font);

                                        lbPdescription.setUIID("Label");
                                        lbPdescription.getAllStyles().setFgColor(0x3958d0);
                                        lbPdescription.getAllStyles().setFont(font);

                                        lbPprice.setUIID("Label");
                                        lbPprice.getAllStyles().setFgColor(0x3958d0);
                                        lbPprice.getAllStyles().setFont(font);

                                        lbPpromotion.setUIID("Label");
                                        lbPpromotion.getAllStyles().setFgColor(0x3958d0);
                                        lbPpromotion.getAllStyles().setFont(font);

                                        lbPtype.setUIID("Label");
                                        lbPtype.getAllStyles().setFgColor(0x3958d0);
                                        lbPtype.getAllStyles().setFont(font);

                                        lbPcategorie.setUIID("Label");
                                        lbPcategorie.getAllStyles().setFgColor(0x3958d0);
                                        lbPcategorie.getAllStyles().setFont(font);

                                        lbPquantite.setUIID("Label");
                                        lbPquantite.getAllStyles().setFgColor(0x3958d0);
                                        lbPquantite.getAllStyles().setFont(font);

                                        Label Pname = new Label(t.getLibelle());
                                        Label Pdescription = new Label(t.getDescription());
                                        Label Pprice = new Label(Double.toString(t.getPrix()) + "DT");
                                        Label Pquantite = new Label(Integer.toString(t.getQuqntite()));
                                        Label Ppromotion = new Label(Integer.toString(t.getPromotion()) + "%");
                                        Label Ptype = new Label(t.getType());
                                        Label Pcategorie = new Label(t.getCategorie());

                                        cName.add(lbPname);
                                        cName.add(Pname);

                                        cDescription.add(lbPdescription);
                                        cDescription.add(Pdescription);

                                        cPrix.add(lbPprice);
                                        cPrix.add(Pprice);

                                        cQuantite.add(lbPquantite);
                                        cQuantite.add(Pquantite);

                                        cPromotion.add(lbPpromotion);
                                        cPromotion.add(Ppromotion);

                                        cType.add(lbPtype);
                                        cType.add(Ptype);

                                        cCategorie.add(lbPcategorie);
                                        cCategorie.add(Pcategorie);

                                        //cImg.add(pImg);
                                        product.add(pImg);
                                        product.add(cName);
                                        product.add(cDescription);
                                        product.add(cPrix);
                                        if (t.getPromotion() != 0) {
                                            product.add(cPromotion);
                                        }
                                        product.add(cType);
                                        product.add(cCategorie);
                                        product.add(cbtn);

                                        product.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), b -> souk.showBack());

                                        product.show();

                                    }
                                });

                                c1.add(name);
                                c1.add(img);

                                Label price = new Label(String.valueOf(t.getPrix()) + " DT");

                                price.setUIID("label");
                                price.getAllStyles().setFgColor(0xa1a0aa);
                                price.setAlignment(CENTER);
                                c1.add(price);
                                souk.add(c1);

                                int id = t.getId();
                            }

                            Form mesProduits = new Form("Mon Souk", BoxLayout.y());

                            for (Produit t : listProduits) {
                                if (t.getIdUser() == (SessionUser.getUser().getId())) {
                                    Toolbar tb = getToolbar();
                                    Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
                                    EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                    ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + t.getImage()));

// ImageViewer img = new ImageViewer(res.getImage(t.getImage()));
                                    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                                    c1.setScrollableY(false);
                                    c1.getAllStyles().setBorder(RoundBorder.create().rectangle(true).
                                            color(0xffffff).
                                            strokeColor(0x7EC0EE).
                                            strokeOpacity(80).
                                            stroke(borderStroke));
                                    c1.getAllStyles().setBackgroundType(Byte.MIN_VALUE);
//                            c1.getStyle().setBgColor(0xe6e5f4);
                                    c1.getStyle().setBgTransparency(255);
                                    c1.getAllStyles().setMarginBottom(15);
                                    c1.getAllStyles().setMarginLeft(15);
                                    c1.getAllStyles().setMarginRight(15);
                                    c1.getAllStyles().setMarginTop(15);

                                    Label name = new Label(t.getLibelle());
                                    name.setUIID("Label");

                                    name.getAllStyles().setFgColor(0x3958d0);
                                    name.getAllStyles().setFont(font);
                                    name.setAlignment(CENTER);

                                    name.addPointerPressedListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            
                                            Form product = new Form(t.getLibelle(), BoxLayout.y());

                                            Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
                                            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                                            ImageViewer pImg = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + t.getImage()));
                                            pImg.getAllStyles().setMarginTop(15);
                                            //Container cImg = new Container(BoxLayout.x());
                                            Container cName = new Container(BoxLayout.x());
                                            Container cDescription = new Container(BoxLayout.x());
                                            Container cPrix = new Container(BoxLayout.x());
                                            Container cQuantite = new Container(BoxLayout.x());
                                            Container cPromotion = new Container(BoxLayout.x());
                                            Container cType = new Container(BoxLayout.x());
                                            Container cCategorie = new Container(BoxLayout.x());

                                            cName.getAllStyles().setMarginTop(15);

                                            Label lbPname = new Label("Libelle : ");
                                            Label lbPdescription = new Label("Description : ");
                                            Label lbPprice = new Label("Prix : ");
                                            Label lbPquantite = new Label("Quantité disponible : ");
                                            Label lbPpromotion = new Label("Promotion : ");
                                            Label lbPtype = new Label("Type : ");
                                            Label lbPcategorie = new Label("Catégorie : ");

                                            lbPname.setUIID("Label");
                                            lbPname.getAllStyles().setFgColor(0x3958d0);
                                            lbPname.getAllStyles().setFont(font);

                                            lbPdescription.setUIID("Label");
                                            lbPdescription.getAllStyles().setFgColor(0x3958d0);
                                            lbPdescription.getAllStyles().setFont(font);

                                            lbPprice.setUIID("Label");
                                            lbPprice.getAllStyles().setFgColor(0x3958d0);
                                            lbPprice.getAllStyles().setFont(font);

                                            lbPpromotion.setUIID("Label");
                                            lbPpromotion.getAllStyles().setFgColor(0x3958d0);
                                            lbPpromotion.getAllStyles().setFont(font);

                                            lbPtype.setUIID("Label");
                                            lbPtype.getAllStyles().setFgColor(0x3958d0);
                                            lbPtype.getAllStyles().setFont(font);

                                            lbPcategorie.setUIID("Label");
                                            lbPcategorie.getAllStyles().setFgColor(0x3958d0);
                                            lbPcategorie.getAllStyles().setFont(font);

                                            lbPquantite.setUIID("Label");
                                            lbPquantite.getAllStyles().setFgColor(0x3958d0);
                                            lbPquantite.getAllStyles().setFont(font);

                                            Label Pname = new Label(t.getLibelle());
                                            Label Pdescription = new Label(t.getDescription());
                                            Label Pprice = new Label(Double.toString(t.getPrix()) + "DT");
                                            Label Pquantite = new Label(Integer.toString(t.getQuqntite()));
                                            Label Ppromotion = new Label(Integer.toString(t.getPromotion()) + "%");
                                            Label Ptype = new Label(t.getType());
                                            Label Pcategorie = new Label(t.getCategorie());

                                            cName.add(lbPname);
                                            cName.add(Pname);

                                            cDescription.add(lbPdescription);
                                            cDescription.add(Pdescription);

                                            cPrix.add(lbPprice);
                                            cPrix.add(Pprice);

                                            cQuantite.add(lbPquantite);
                                            cQuantite.add(Pquantite);

                                            cPromotion.add(lbPpromotion);
                                            cPromotion.add(Ppromotion);

                                            cType.add(lbPtype);
                                            cType.add(Ptype);

                                            cCategorie.add(lbPcategorie);
                                            cCategorie.add(Pcategorie);

                                            //cImg.add(pImg);
                                            product.add(pImg);
                                            product.add(cName);
                                            product.add(cDescription);
                                            product.add(cPrix);
                                            if (t.getPromotion() != 0) {
                                                product.add(cPromotion);
                                            }
                                            product.add(cType);
                                            product.add(cCategorie);

                                            product.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), b -> mesProduits.showBack());

                                            product.show();

                                        }
                                    });

                                    c1.add(name);
                                    c1.add(img);

                                    Label price = new Label(String.valueOf(t.getPrix()) + " DT");

                                    price.setUIID("label");
                                    price.getAllStyles().setFgColor(0xa1a0aa);
                                    price.setAlignment(CENTER);
                                    c1.add(price);

                                    System.out.println(u.getId());
                                    System.out.println(u.getId().equals(t.getIdUser()));
                                    System.out.println(t.getIdUser());
                                    if (u.getId().equals(t.getIdUser())) {
                                        mesProduits.add(c1);
                                    }

                                    int id = t.getId();
                                }

                            }

                            Form ajoutProduit = new Form("Ajouter un produit", BoxLayout.y());

                            TextField libelle = new TextField("", "Libelle");
                            ajoutProduit.add(libelle);

                            TextField description = new TextField("", "Description");
                            ajoutProduit.add(description);

                            TextField prix = new TextField("", "Prix");
                            ajoutProduit.add(prix);

                            TextField quantite = new TextField("", "Quantité");
                            ajoutProduit.add(quantite);

                            TextField promotion = new TextField("", "Promotion");
                            ajoutProduit.add(promotion);

                            ImageViewer i = new ImageViewer();

                            Button btnOpen = new Button("Choisir Image");

                            btnOpen.addActionListener((evt1) -> {
                                ActionListener callback = e -> {

                                    if (e != null && e.getSource() != null) {
                                        this.newfilePath = (String) e.getSource();
                                        try {
                                            i.setImage(Image.createImage(this.newfilePath));
                                        } catch (IOException ex) {
                                            System.out.println("Error" + ex.getMessage());
                                        }
                                        //Here goes the file upload logic
                                        System.out.println(this.newfilePath);
                                        try {
                                            this.newfilePath = UploadFile.uploadImage(newfilePath, null);
                                        } catch (Exception ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                    }
                                };
                                if (FileChooser.isAvailable()) {
                                    FileChooser.showOpenDialog(".jpg,image/jpg,.jpeg", callback);
                                } else {
                                    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
                                }
                            });

                            ajoutProduit.add(btnOpen);

                            Button aj = new Button("Ajouter");
                            aj.addActionListener((evt1) -> {
                                if (!this.newfilePath.equals("") && this.newfilePath != null) {
                                    Produit p = new Produit();

                                    p.setLibelle(libelle.getText());
                                    p.setDescription(description.getText());
                                    p.setPrix(Double.valueOf(prix.getText()));
                                    p.setQuqntite(Integer.valueOf(quantite.getText()));
                                    p.setPromotion(Integer.valueOf(promotion.getText()));
                                    p.setImage(this.newfilePath);
//                                ProduitServices ps = new ProduitServices();
                                    this.ps.ajout(p, SessionUser.getUser().getId());

                                    LocalNotification n = new LocalNotification();
                                    n.setId("demo-notification");
                                    n.setAlertBody("Votre produit a été ajouté");
                                    n.setAlertTitle("Ajout");
                                    n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound

                                    Display.getInstance().scheduleLocalNotification(
                                            n,
                                            System.currentTimeMillis(), // fire date/time
                                            LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
                                    );

                                    mesProduits.showBack();
                                }

                            });

                            ajoutProduit.add(aj);

                            ajoutProduit.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), b -> mesProduits.showBack());
                            mesProduits.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), b -> souk.showBack());
                            mesProduits.getToolbar().addCommandToRightBar("Ajouter", theme.getImage("back-command.png"), b -> ajoutProduit.show());

                            souk.getToolbar().addCommandToOverflowMenu("Mon Souk", theme.getImage("back-command.png"), b -> mesProduits.show());
                            souk.getToolbar().addCommandToOverflowMenu("Logout", theme.getImage("back-command.png"), b -> f.showBack());
                            souk.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), b -> home.showBack());

                            //cn.add(logo);
                            about.add(cn);

                            //about.add(l);
                            about.getToolbar().addCommandToLeftBar("Back", theme.getImage("back-command.png"), b -> home.showBack());

                        } catch (IOException ex) {
                            System.out.println("Error" + ex.getMessage());
                        }

                    }

                }

            }
            );

            f.add(logo);
            f.add(login);
            f.add(password);
            f.add(loginButton);

        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());
        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
