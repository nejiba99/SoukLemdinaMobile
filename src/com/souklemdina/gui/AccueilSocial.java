/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.capture.Capture;
import com.codename1.util.regex.RE;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Post;
import com.souklemdina.entities.Profile;
import com.souklemdina.entities.PostHome;
import com.souklemdina.services.PostServices;
import com.souklemdina.services.ProfileServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.ImageViewerHerit;
import com.souklemdina.util.Labelherit;
import com.souklemdina.util.UploadFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rkader
 */
public class AccueilSocial {

    private Form f;
    private PostServices ps = new PostServices();
    private ProfileServices pr = new ProfileServices();
    private String newfilePath = "";

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    private Labelherit createForFonthh(Font fnt, String s) {
        Labelherit l = new Labelherit();
        l.setText(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public boolean isNotGood(String s) {
        return s.startsWith("0")
                || s.startsWith("1")
                || s.startsWith("2")
                || s.startsWith("3")
                || s.startsWith("4")
                || s.startsWith("5")
                || s.startsWith("6")
                || s.startsWith("7")
                || s.startsWith("8")
                || s.startsWith("9");
    }

    public AccueilSocial() {
//        f = new Form("Hdith ElSouk", BoxLayout.y());
        f = new Form("Hdith ElSouk", new FlowLayout());
        Resources theme = UIManager.initFirstTheme("/theme");
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        //Here goes the connection Logic
        SessionUser.setUser(new FosUser(3));
        SessionUser.setProfile(new Profile(5));
        ArrayList<PostHome> arp = ps.getAccueil(3);
        Button btnAdd = new Button("Ajouter une publication");
        btnAdd.addActionListener((evt) -> {
            Form fa = new Form("Ajouter une publication", BoxLayout.y());
            Image icon = theme.getImage("back-command.png");
            icon = icon.scaled(70, 90);
            fa.getToolbar().addCommandToLeftBar("", icon, e -> {
                AccueilSocial acObj = new AccueilSocial();
                acObj.getF().showBack();
            });
            TextField tftitre = new TextField("", "Titre");
            fa.add(tftitre);
            TextField tftexte = new TextField("", "Texte");
            tftexte.setMaxSize(255);
            fa.add(tftexte);
            ImageViewer i = new ImageViewer();
            Container hcc = new Container(BoxLayout.y());
            Button btnOpen = new Button("Choisir Image");
            btnOpen.addActionListener((evt1) -> {
                ActionListener callback = e -> {
                    if (e != null && e.getSource() != null) {
                        try {
                            this.newfilePath = (String) e.getSource();
                            i.setImage(Image.createImage(this.newfilePath));
                            //Here goes the file upload logic
                            System.out.println(this.newfilePath);
                            try {
                                this.newfilePath = UploadFile.uploadImage(newfilePath, null);
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        } catch (IOException ex) {
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
            Button btnConf = new Button("Publier");
            btnConf.addActionListener((evt1) -> {
                if (!this.newfilePath.equals("") && this.newfilePath != null && !isNotGood(tftexte.getText()) && !isNotGood(tftitre.getText())) {
                    Post poss = new Post();
                    poss.setTexte(tftexte.getText());
                    poss.setTitre(tftitre.getText());
                    poss.setImage(this.newfilePath);
                    this.ps.addPost(poss, SessionUser.getUser().getId());
                    tftexte.clear();
                    tftitre.clear();
                    AccueilSocial acObj = new AccueilSocial();
                    acObj.getF().showBack();
                } else {
                    Dialog.show("Erreur!", "Données érronées", "Ok", "");
                }
            });
            Button btnCapt = new Button("Prendre photo");
            btnCapt.addActionListener((evt1) -> {
                this.newfilePath = Capture.capturePhoto();
                try {
                    i.setImage(Image.createImage(this.newfilePath));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(this.newfilePath);
                try {
                    this.newfilePath = UploadFile.uploadImage(newfilePath, null);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println(this.newfilePath);
            });
            btnOpen.getStyle().setPadding(Component.LEFT, 10);
            btnOpen.getStyle().setPadding(Component.RIGHT, 10);
            btnCapt.getStyle().setPadding(Component.LEFT, 10);
            btnCapt.getStyle().setPadding(Component.RIGHT, 10);
            btnConf.getStyle().setPadding(Component.RIGHT, 10);
            btnConf.getStyle().setPadding(Component.LEFT, 10);
            hcc.add(btnOpen);
            hcc.add(btnCapt);
            hcc.add(btnConf);
            fa.add(hcc);
            fa.add(i);
            fa.show();
        });
        Container vc = new Container(BoxLayout.y());
        btnAdd.getStyle().setPadding(Component.LEFT, 10);
        btnAdd.getStyle().setPadding(Component.RIGHT, 10);
        vc.add(btnAdd);
        f.add(vc);
        for (PostHome p : arp) {
            if (p.getPos().getImage() != null) {
                Image placeholder = Image.createImage(f.getWidth() / 3 - 6, f.getWidth() / 3 - 6, 0xbfc9d2);
                EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                ImageViewerHerit img = new ImageViewerHerit(URLImage.createToStorage(encImage, "file" + p.getPos().getImage(),
                        "http://localhost/SoukLemdina/web/uploads/images/" + p.getPos().getImage()));
                Form fi = new Form(p.getPos().getTitre(), BoxLayout.y());
                Image icon = theme.getImage("back-command.png");
                icon = icon.scaled(70, 90);
                fi.getToolbar().addCommandToLeftBar("", icon, e -> {
                    AccueilSocial acObj = new AccueilSocial();
                    acObj.getF().showBack();
                });
                SimpleDateFormat dfdte = new SimpleDateFormat("dd/MM/yyyy");
//                FosUser fus = this.pr.findUserById(p.getPr().getIdUser());
                FosUser fus = new FosUser(5, p.getFirstname(), p.getLastname(), new Date(333333333));
                String nn = p.getFirstname() + " " + p.getLastname();
                Form ff = new Form(nn, BoxLayout.y());
                Image placeholderDett = Image.createImage(f.getWidth(), f.getWidth(), 0xbfc9d2);
                EncodedImage encImageDett = EncodedImage.createFromImage(placeholderDett, false);
                ImageViewer imgDett = new ImageViewer(URLImage.createToStorage(encImageDett, "fileDet" + p.getPr().getImage(),
                        "http://localhost/SoukLemdina/web/uploads/images/" + p.getPr().getImage()));
                ff.add(imgDett);
                ff.add(new Label(nn));
                ff.add(new Label(dfdte.format(fus.getDatenaiss())));
                ff.add(new Label());
                ff.add(new SpanLabel(p.getPr().getTagline()));
                ff.add(new Label());
                ff.add(new SpanLabel(p.getPr().getAboutMe()));
                ff.getToolbar().addCommandToLeftBar("", icon, e -> {
                    AccueilSocial acObj = new AccueilSocial();
                    acObj.getF().showBack();
                });

                Image placeholderDet = Image.createImage(f.getWidth(), f.getWidth(), 0xbfc9d2);
                EncodedImage encImageDet = EncodedImage.createFromImage(placeholderDet, false);
                ImageViewer imgDet = new ImageViewer(URLImage.createToStorage(encImageDet, "fileDet" + p.getPos().getImage(),
                        "http://localhost/SoukLemdina/web/uploads/images/" + p.getPos().getImage()));
                fi.add(imgDet);
                Container hc = new Container(BoxLayout.x());

                Labelherit lnn = new Labelherit();
                lnn = createForFonthh(smallLightSystemFont, p.getFirstname() + " " + p.getLastname());
                lnn.setF(ff);

                hc.add(lnn);
                if (p.getPr().getIdUser() != SessionUser.getUser().getId()) {
                    OnOffSwitch btff = new OnOffSwitch();
                    btff.setNoTextMode(true);
                    btff.setValue(p.getMfollowi());
                    btff.addActionListener((evt) -> {
                        pr.follow(SessionUser.getProfile().getId(), p.getPr().getId());
                    });
                    hc.add(btff);
                } else {
                    Button btff = new Button("Effacer");
                    btff.addActionListener((evt) -> {
                        if (Dialog.show("Delete", "Êtes vous sûr de supprimer cette publication??", "Oui", "Non")) {
                            ps.delPost(p.getPos().getId());
                            AccueilSocial acObj = new AccueilSocial();
                            acObj.getF().showBack();
                        }
                    });
                    hc.add(btff);
                    Button btup = new Button("Modifier");
                    btup.addActionListener((evt) -> {
                        Form fa = new Form("Modifier une publication", BoxLayout.y());
                        Image icon2 = theme.getImage("back-command.png");
                        icon2 = icon2.scaled(70, 90);
                        fa.getToolbar().addCommandToLeftBar("", icon2, e -> {
                            AccueilSocial acObj = new AccueilSocial();
                            acObj.getF().showBack();
                        });
                        TextField tftitre = new TextField(p.getPos().getTitre());
                        fa.add(tftitre);
                        TextField tftexte = new TextField(p.getPos().getTexte());
                        tftexte.setMaxSize(255);
                        fa.add(tftexte);
                        Button btnSave = new Button("Enregistrer");
                        btnSave.addActionListener((evt1) -> {
                            Post poss = new Post(p.getPos().getId());
                            poss.setTexte(tftexte.getText());
                            poss.setTitre(tftitre.getText());
                            this.ps.modPost(poss);
                            tftexte.clear();
                            tftitre.clear();
                            AccueilSocial acObj = new AccueilSocial();
                            acObj.getF().showBack();
                        });
                        btnSave.getStyle().setPadding(Component.LEFT, 10);
                        btnSave.getStyle().setPadding(Component.RIGHT, 10);
                        fa.add(btnSave);
                        fa.show();
                    });
                    hc.add(btup);
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
        Container vc2 = new Container(BoxLayout.y());
        Button b1 = new Button("Les locaux");
        Button b2 = new Button("Les évènements");
        Button b3 = new Button("Les workshops");
        Button b4 = new Button("Mes commandes");
        vc2.add(b1).add(b2).add(b3).add(b4);
        b1.addActionListener((evt) -> {
            GUILocal gl = new GUILocal();
            gl.getF().getAllStyles().setBgColor(0x696969);
            gl.getF().show();
            Image icon = theme.getImage("back-command.png");
            gl.getF().getToolbar().addCommandToLeftBar("", icon, e -> {
                AccueilSocial acObj = new AccueilSocial();
                acObj.getF().showBack();
            });
        });
        b3.addActionListener((evt) -> {
            ListeWorks ac = new ListeWorks();
            ac.getf().show();
            Image icon = theme.getImage("back-command.png");
            ac.getf().getToolbar().addCommandToLeftBar("", icon, e -> {
                AccueilSocial acObj = new AccueilSocial();
                acObj.getF().showBack();
            });
        });
        vc.add(vc2);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
