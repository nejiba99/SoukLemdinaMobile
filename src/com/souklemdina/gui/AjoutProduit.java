/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
//import com.codename1.uikit.materialscreens.ProfileForm;
//import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Post;
import com.souklemdina.entities.Produit;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class AjoutProduit  {

    private String newfilePath = "";
    ProduitServices ps = new ProduitServices();

    public AjoutProduit(Resources res) {
//
//        super(BoxLayout.y());
//        SessionUser.setUser(new FosUser(3));
//        Toolbar tb = getToolbar();
//        tb.setTitleCentered(false);
//
//        Label profilePicLabel = new Label(" Ajouter un produit");
//        FontImage.setMaterialIcon(profilePicLabel, FontImage.MATERIAL_ADD);
//        profilePicLabel.getAllStyles().setFgColor(0xFFFFFF);
//
//        Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//
//        Container titleCmp = BoxLayout.encloseY(
//                FlowLayout.encloseIn(menuButton),
//                BorderLayout.centerAbsolute(
//                        BoxLayout.encloseY(
//                                new Label("Ajouter un produit", "Title")
//                        )
//                ).add(BorderLayout.CENTER, profilePicLabel),
//                GridLayout.encloseIn(2)
//        );
//        tb.setTitleComponent(titleCmp);
//
//        TextField libelle = new TextField("", "Libelle", 40, TextField.ANY);
//        libelle.getAllStyles().setFgColor(0x000000);
//        libelle.getAllStyles().setBgColor(0x000000);
//        add(libelle);
//        TextField description = new TextField("", "Description", 40, TextField.ANY);
//        description.getAllStyles().setFgColor(0x000000);
//        description.getAllStyles().setBgColor(0x000000);
//        add(description);
//        TextField prix = new TextField("", "Prix", 40, TextField.ANY);
//        prix.getAllStyles().setFgColor(0x000000);
//        prix.getAllStyles().setBgColor(0x000000);
//        add(prix);
//        TextField quantite = new TextField("", "Quantité", 40, TextField.ANY);
//        quantite.getAllStyles().setFgColor(0x000000);
//        quantite.getAllStyles().setBgColor(0x000000);
//        add(quantite);
//        TextField promotion = new TextField("", "Promotion", 40, TextField.ANY);
//        promotion.getAllStyles().setFgColor(0x000000);
//        promotion.getAllStyles().setBgColor(0x000000);
//        add(promotion);
//
//        ImageViewer i = new ImageViewer();
//
//        Button btnOpen = new Button("Choisir Image");
//        btnOpen.addActionListener((evt1) -> {
//            ActionListener callback = e -> {
//                if (e != null && e.getSource() != null) {
//                    this.newfilePath = (String) e.getSource();
//                    try {
//                        i.setImage(Image.createImage(this.newfilePath));
//                    } catch (IOException ex) {
//                        System.out.println(ex.getMessage());
//                    }
//                    //Here goes the file upload logic
//                    System.out.println(this.newfilePath);
//                    try {
//                        this.newfilePath = UploadFile.uploadImage(newfilePath, null);
//                    } catch (Exception ex) {
//                        System.out.println(ex.getMessage());
//                    }
//                }
//            };
//            if (FileChooser.isAvailable()) {
//                FileChooser.showOpenDialog(".jpg,image/jpg,.jpeg", callback);
//            } else {
//                Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
//            }
//        });
//        btnOpen.setUIID("LoginButton");
//
//        add(btnOpen);
//
//        Button aj = new Button("Ajouter");
//        aj.addActionListener((evt1) -> {
//            if (!this.newfilePath.equals("") && this.newfilePath != null) {
//                Produit p = new Produit();
//              
//                p.setLibelle(libelle.getText());
//                p.setDescription(description.getText());
//                p.setPrix(Double.valueOf(prix.getText()));
//                p.setQuqntite(Integer.valueOf(quantite.getText()));
//                p.setPromotion(Integer.valueOf(promotion.getText()));
//                p.setImage(this.newfilePath);
//                this.ps.ajout(p, SessionUser.getUser().getId());
//
//        LocalNotification n = new LocalNotification();
//        n.setId("demo-notification");
//        n.setAlertBody("Votre produit a été ajouté");
//        n.setAlertTitle("Ajout");
//        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
//
//        Display.getInstance().scheduleLocalNotification(
//                n,
//                System.currentTimeMillis(), // fire date/time
//                LocalNotification.REPEAT_MINUTE // Whether to repeat and what frequency
//        );
//
//                
//                
//                new ProfileForm(res).showBack();
//            }
//
//        });
//
//        add(aj);
//
//        setupSideMenu(res);
//
//    }
//
//    @Override
//    protected void showOtherForm(Resources res) {
    }

}
