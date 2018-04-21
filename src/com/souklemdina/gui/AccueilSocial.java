/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Profile;
import com.souklemdina.entities.PostHome;
import com.souklemdina.services.PostServices;
import com.souklemdina.services.ProfileServices;
import com.souklemdina.util.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author rkader
 */
public class AccueilSocial {

    private Form f;
    private PostServices ps = new PostServices();
    private ProfileServices pr = new ProfileServices();

    public AccueilSocial() {
        f = new Form("Hdith ElSouk", BoxLayout.y());
        //Here goes the connection Logic
        SessionUser.setUser(new FosUser(3));
        SessionUser.setProfile(new Profile(5));
        ArrayList<PostHome> arp = ps.getAccueil(3);
        for (PostHome p : arp) {
            Container hc = new Container(BoxLayout.x());
            hc.add(new Label(p.getFirstname() + " " + p.getLastname()));
            if (p.getPr().getIdUser() != SessionUser.getUser().getId()) {
                Button btf = new Button(p.getMfollowi() ? "Unfollow" : "Follow");
                btf.addActionListener((evt) -> {
                    System.out.println(SessionUser.getProfile().getId());
                    System.out.println(SessionUser.getUser().getId());
                    pr.follow(SessionUser.getProfile().getId(), p.getPr().getId());
                    btf.setText(btf.getText().equals("Follow") ? "Unfollow" : "Follow");
                });
                hc.add(btf);
            }
            f.add(hc);
            f.add(new SpanLabel(p.getPos().getTitre()));
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
