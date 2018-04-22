/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Profile;
import com.souklemdina.entities.PostHome;
import com.souklemdina.services.PostServices;
import com.souklemdina.services.ProfileServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.ImageViewerHerit;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rkader
 */
public class AccueilSocial {

    private Form f;
    private PostServices ps = new PostServices();
    private ProfileServices pr = new ProfileServices();

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public AccueilSocial() {
//        f = new Form("Hdith ElSouk", BoxLayout.y());
        f = new Form("Hdith ElSouk", new FlowLayout());
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        //Here goes the connection Logic
        SessionUser.setUser(new FosUser(3));
        SessionUser.setProfile(new Profile(5));
        ArrayList<PostHome> arp = ps.getAccueil(3);
        for (PostHome p : arp) {
//            Container hc = new Container(BoxLayout.x());
//            Container vc = new Container(BoxLayout.y());
//            Font smallBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
//            hc.add(createForFont(smallBoldSystemFont, p.getFirstname() + " " + p.getLastname()));
//            if (p.getPr().getIdUser() != SessionUser.getUser().getId()) {
//                Button btf = new Button(p.getMfollowi() ? "Unfollow" : "Follow");
//                btf.addActionListener((evt) -> {
//                    System.out.println(SessionUser.getProfile().getId());
//                    System.out.println(SessionUser.getUser().getId());
//                    pr.follow(SessionUser.getProfile().getId(), p.getPr().getId());
//                    btf.setText(btf.getText().equals("Follow") ? "Unfollow" : "Follow");
//                });
//                hc.add(btf);
//            }
//            if (p.getPos().getImage() != null) {
//                Image placeholder = Image.createImage(150, 150, 0xbfc9d2);
//                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
//                vc.add(new ImageViewer(URLImage.createToStorage(encImage, "file" + p.getPos().getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + p.getPos().getImage())));
//            }
//            f.add(hc);
//            f.add(vc);
//            f.add(new SpanLabel(p.getPos().getTitre()));
            if (p.getPos().getImage() != null) {
                Image placeholder = Image.createImage(f.getWidth() / 3 - 4, f.getWidth() / 3 - 4, 0xbfc9d2);
                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                ImageViewerHerit img = new ImageViewerHerit(URLImage.createToStorage(encImage, "file" + p.getPos().getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + p.getPos().getImage()));
                Form fi = new Form(p.getPos().getTitre(), BoxLayout.y());
                Resources theme = UIManager.initFirstTheme("/theme");
                Image icon = theme.getImage("back-command.png");
                icon = icon.scaled(70, 90);
                fi.getToolbar().addCommandToLeftBar("", icon, e -> {
                    f.showBack();
                });
                Image placeholderDet = Image.createImage(f.getWidth(), f.getWidth(), 0xbfc9d2);
                EncodedImage encImageDet = EncodedImage.createFromImage(placeholderDet, false);
                ImageViewer imgDet = new ImageViewer(URLImage.createToStorage(encImageDet, "fileDet" + p.getPos().getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + p.getPos().getImage()));
                fi.add(imgDet);
                Container hc = new Container(BoxLayout.x());
                hc.add(createForFont(smallLightSystemFont, p.getFirstname() + " " + p.getLastname()));
                if (p.getPr().getIdUser() != SessionUser.getUser().getId()) {
                    OnOffSwitch btff = new OnOffSwitch();
                    btff.setNoTextMode(true);
                    btff.setValue(p.getMfollowi());
                    btff.addActionListener((evt) -> {
                        pr.follow(SessionUser.getProfile().getId(), p.getPr().getId());
                    });
                    hc.add(btff);
                }
                fi.add(hc);
                SpanLabel titre = new SpanLabel(p.getPos().getTitre());
                fi.add(createForFont(smallBoldSystemFont, titre.getText()));
                SpanLabel texte = new SpanLabel(p.getPos().getTexte());
                texte.getTextAllStyles().setFont(smallLightSystemFont);
                fi.add(texte);
                
                img.setF(fi);
                f.add(img);
            }
        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
