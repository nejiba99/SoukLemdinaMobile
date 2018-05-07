/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.db.Cursor;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
//import com.codename1.uikit.materialscreens.ProfileForm;
//import com.codename1.uikit.materialscreens.WalkthruForm;
import com.souklemdina.entities.FosUser;
import com.souklemdina.services.FosUserServices;
import com.souklemdina.util.SessionUser;
import java.io.IOException;

/**
 *
 * @author asus
 */
public class LoginForm extends Form {

    public LoginForm(Resources theme) {
//        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
//        setUIID("LoginForm");
//        Container welcome = FlowLayout.encloseCenter(
//                new Label("LOGIN", "WelcomeWhite")
//        );
//
//        getTitleArea().setUIID("Container");
//
//        Image profilePic = theme.getImage("app.png");
//        profilePic.scale(150, 150);
//
//        Label profilePicLabel = new Label(profilePic, "ProfilePic");
//
//        TextField login = new TextField("", "Saisir votre pseudo", 40, TextField.EMAILADDR);
//        login.getAllStyles().setFgColor(0xFFFFFF);
//        login.getAllStyles().setBgColor(0x000000);
//
//        TextField password = new TextField("", "Saisir votre mot de passe", 40, TextField.PASSWORD);
//        login.getAllStyles().setMargin(LEFT, 0);
//        password.getAllStyles().setMargin(LEFT, 0);
//        Label loginIcon = new Label("", "TextField");
//        Label passwordIcon = new Label("", "TextField");
//        loginIcon.getAllStyles().setMargin(RIGHT, 0);
//        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
//        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 5);
//        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 5);
//
//        Button loginButton = new Button("LOGIN");
//        loginButton.setUIID("LoginButton");
//
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//
//                FosUserServices us = new FosUserServices();
//                if ((login.getText().equals("")) || ((password.getText().equals("")))) {
//                    Dialog.show("Vérifiez Vos Champs", "Vérifiez Vos Champs", null, "ok");
//                }
//                System.out.println(us.login(login.getText(), password.getText()));
//                if (us.login(login.getText(), password.getText())) {
//                    FosUser u = us.getuser(login.getText(), password.getText());
//                    SessionUser.setUser(u);
//                    
//                    Toolbar.setGlobalToolbar(false);
//                    new WalkthruForm(theme).show();
//                    Toolbar.setGlobalToolbar(true);
//
//                }
//            }
//        }
//        );
//
// 
//
//        // We remove the extra space for low resolution devices so things fit better
//        Label spaceLabel;
//        if (!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
//            spaceLabel = new Label();
//        } else {
//            spaceLabel = new Label(" ");
//        }
//
//        Container by = BoxLayout.encloseY(
//                welcome,
//                profilePicLabel,
//                spaceLabel,
//                BorderLayout.center(login).
//                        add(BorderLayout.WEST, loginIcon),
//                BorderLayout.center(password).
//                        add(BorderLayout.WEST, passwordIcon),
//                loginButton
//        );
//        by.getAllStyles().setBgColor(0xFFFFFF);
//        add(BorderLayout.CENTER, by);
//
//        // for low res and landscape devices
//        by.setScrollableY(true);
//        by.setScrollVisible(false);
    }

}
