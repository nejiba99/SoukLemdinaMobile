/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
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
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.regex.RE;
import com.souklemdina.entities.Evenement;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantsEvents;
import com.souklemdina.services.EventServices;
import com.souklemdina.services.ParticipantsServices;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ListEvents {

    //  FosUser fu = new FosUser(1);
    private static final String apiKey = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    boolean tprix;
    boolean tnb;
    Form fe;
    Form fa;
    Container c1;
    Container c2;
    Container crat;
    Label lb;
    Rating rat1;
    Slider s1;
    final DefaultListModel<String> options = new DefaultListModel<>();
    private String newfilePath = "";
    Picker txType = new Picker();
// FosUser fu= new FosUser();
    ParticipantsServices pes = new ParticipantsServices();
    ParticipantsEvents p = new ParticipantsEvents();

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    EventServices Evs = new EventServices();

    public ListEvents() {
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels((float) 2.2), Font.STYLE_ITALIC);

        UIBuilder.registerCustomComponent("DateTimeSpinner", DateTimeSpinner.class);

        Resources theme = UIManager.initFirstTheme("/theme");
        fe = new Form("Evénements à venir", BoxLayout.y());
        // fe.setTintColor(0x00bfff);

        //    fe.getUnselectedStyle().setFont();
        ArrayList<Evenement> lev = Evs.getListEvents();
        ////////////////////////ADD/////////////////////////////////////////////

        Button btnAdd = new Button("Ajouter un  événement");
        // btnAdd.getStyle().setBgPainter();
        btnAdd.setTextPosition(Component.LEFT);

        btnAdd.addActionListener((evt) -> {
            Form fa = new Form("Ajouter un événement", BoxLayout.y());
            Image icon = theme.getImage("back-command.png");
            icon = icon.scaled(70, 90);
            fa.getToolbar().addCommandToLeftBar("", icon, e -> {
                ListEvents leObj = new ListEvents();
                leObj.getFe().showBack();
            });
            TextField nom = new TextField("", "nomEvenement");
            fa.add(nom);
            TextField datede = new TextField("", "dateDebut");
            //  fa.add(datede);
            TextField datefi = new TextField("", "dateFin");
            //  fa.add(datefi);
            TextField descr = new TextField("", "description");
            descr.setMaxSize(255);
            fa.add(descr);

            Container cTyp = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label labTyp = new Label("Type: ");

            txType.setStrings(new String[]{"musical", "vide dressing", "culturel", "autres"});

            cTyp.add(labTyp);
            cTyp.add(txType);
            AutoCompleteTextField adr = new AutoCompleteTextField(options) {
                @Override
                protected boolean filter(String text) {
                    if (text.length() == 0) {
                        return false;
                    }
                    String[] l = searchLocations(text);
                    if (l == null || l.length == 0) {
                        return false;
                    }

                    options.removeAll();
                    for (String s : l) {
                        options.addItem(s);

                    }
                    return true;
                }

            };
            adr.setMinimumElementsShownInPopup(5);
            TextField he = new TextField("", "heure");
            // fa.add(he);
            TextField pri = new TextField("", "prix");
            fa.add(pri);
            pri.addDataChangedListener((i1, i2) -> {
                RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                if (!re.match(pri.getText())) {
                    Dialog.show("Alerte", "Le champ prix doit etre un nombre", "Ok", null);
                    tnb = false;
                } else {
                    tprix = true;
                }
            });
            TextField nbpl = new TextField("", "nbPlace");
            fa.add(nbpl);
            nbpl.addDataChangedListener((i1, i2) -> {
                RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                if (!re.match(nbpl.getText())) {
                    Dialog.show("Alerte", "Le champ nbre de place doit etre de type nombre", "Ok", null);
                    tnb = false;
                } else {
                    tprix = true;
                }
            });
            Button vajout = new Button("ajouter");
            DateTimeSpinner heure = new DateTimeSpinner();
            Picker datedebut = new Picker();
            datedebut.setType(Display.PICKER_TYPE_DATE);
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            datedebut.setDate(new Date());
            // datedebut.setFormatter((SimpleDateFormat) sdf);
            Picker datefin = new Picker();
            datefin.setType(Display.PICKER_TYPE_DATE);
            datefin.setDate(new Date());
            //  datefin.setFormatter((SimpleDateFormat) sdf);

            datedebut.getAllStyles().setFgColor(com.codename1.charts.util.ColorUtil.GREEN);
            datefin.getAllStyles().setFgColor(com.codename1.charts.util.ColorUtil.GRAY);
            heure.getAllStyles().setFgColor(com.codename1.charts.util.ColorUtil.BLUE);

            datefin.setShowMeridiem(false);
            datedebut.setShowMeridiem(false);
            heure.setShowMeridiem(false);

            ImageViewer i = new ImageViewer();
            Button btnOpen = new Button("Choisir Image");
            //Container cc = new Container(BoxLayout.y());

            btnOpen.addActionListener((evt1) -> {
                ActionListener callback = e -> {
                    if (e != null && e.getSource() != null) {
                        try {
                            this.newfilePath = (String) e.getSource();
                            i.setImage(Image.createImage(this.newfilePath));
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

            vajout.addActionListener((e) -> {
                String dd = datedebut.getText();
                String year = dd.substring(dd.length() - 2, dd.length());
                int dy = Integer.parseInt(year);
                String month = (dd.substring(dd.indexOf("/") + 1)).substring(0, dd.indexOf("/"));
                int dm = Integer.parseInt(month);
                String day = dd.substring(0, dd.indexOf("/"));
                int dday = Integer.parseInt(day);

                String dff = datefin.getText();
                String yearf = dff.substring(dff.length() - 2, dff.length());
                int dfy = Integer.parseInt(yearf);
                String monthf = (dff.substring(dff.indexOf("/") + 1)).substring(0, dff.indexOf("/"));
                int dfm = Integer.parseInt(monthf);
                String dayf = dff.substring(0, dff.indexOf("/"));
                int ddayf = Integer.parseInt(dayf);
                System.out.println("year" + dfy);

//                if ((dy > dfy || (dy == dfy && dday > ddayf) || (dy == dfy && dday == ddayf && dm > dfm) || nom.getText().equals("")) || (txType.getSelectedStringIndex() < 0) || (descr.getText().equals(""))
//                        || (pri.getText().equals("")) || (nbpl.getText().equals(""))
//                        || (adr.getText().equals(""))) {
//                    Dialog.show("Alerte", "Veuillez remplir tous les champs ou vérifier les données saisies", "Ok", null);
//                } else {

                    // EventServices evs=new EventServices();
                    if (!this.newfilePath.equals("") && this.newfilePath != null) {
                        try {

                            if (datedebut.getDate().getTime() > (datefin.getDate().getTime())) {

                                String d = datedebut.getText();
                                Date d1 = sdf.parse(d);
                                Calendar c = Calendar.getInstance();
                                c.setTime(d1);
                                String df = datefin.getText();
                                Date ddf = sdf.parse(d);
                                Calendar cf = Calendar.getInstance();
                                c.setTime(ddf);
                            }

                            int h = heure.getCurrentHour();
                            int m = heure.getCurrentMinute();
                            SimpleDateFormat sdff = new SimpleDateFormat("hhmm");

                            Evenement ev = new Evenement();
                            ev.setNomEvenement(nom.getText());
                            ev.setDateDebut(datedebut.getDate());
                            ev.setDateFin(datefin.getDate());
                            ev.setHeures(String.valueOf(h) + ":" + String.valueOf(m));
                            ev.setAdresse(adr.getText());
                            ev.setPrix(Double.parseDouble(pri.getText()));
                            ev.setDescription(descr.getText());
                            ev.setNbPlace(Integer.parseInt(nbpl.getText()));
                            ev.setType(txType.getSelectedString());
                            ev.setPhoto(this.newfilePath);

                            this.Evs.addEvent(ev, SessionUser.getUser().getId());

                            ListEvents lst = new ListEvents();
                            lst.getFe().showBack();

                        } catch (ParseException ex) {

                        }

//                    }
                }

            });
            fa.add(adr);
            fa.add(datefin);
            fa.add(datedebut);
            fa.add(heure);
            fa.add(cTyp);
            fa.add(btnOpen);
            fa.add(vajout);

            fa.show();

        });
        fe.add(btnAdd);

        //SessionUser.setUser(new FosUser(4));
        //////////////////LISTE/////////////////////////
        for (Evenement ee : lev) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container cy = new Container(BoxLayout.y());

            Label nom = new Label(ee.getNomEvenement());
            Button det = new Button("detail");

            det.setSize(new Dimension(5, 5));
            Image placeholder = Image.createImage(fe.getWidth() / 3 - 4, fe.getWidth() / 3 - 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + ee.getPhoto(),
                    "http://localhost/SoukLemdina/web/uploads/images/" + ee.getPhoto()));
            Label pr = new Label((String.valueOf(ee.getPrix())));
//            cy.setLayout(new BorderLayout());
//            cy.add(BorderLayout.CENTER, new Label((String.valueOf(e.getPrix()))));

            c1.add(img1);
            c1.add(createForFont(smallLightSystemFont, nom.getText()));

            cy.add(createForFont(smallLightSystemFont, pr.getText()));
            c1.add(cy);
            //c1.getAbsoluteY();
            /////////////////////EDIT EVENT///////////////////////////////////////
            Button edit = new Button("Modifier");
            System.out.println("idu " + ee.getIdUser());
            if (ee.getIdUser() != SessionUser.getUser().getId()) {
                edit.setVisible(false);

            }

            // else {
            //System.out.println("*******************************"+e.getIdUser());
            edit.addActionListener((ev) -> {
                Form ed = new Form("Modifier l'événement", BoxLayout.y());
                //   ed.getStyle().setBgColor(0xdcdcdc);

                // System.out.println("  ///////////"+e.getIdUser());
                Image ic = theme.getImage("back-command.png");
                ic = ic.scaled(70, 90);
                ed.getToolbar().addCommandToLeftBar("", ic, evv -> {
                    ListEvents levent = new ListEvents();
                    levent.getFe().showBack();

                });

                TextField nome = new TextField(ee.getNomEvenement());
                ed.add(nome);

                TextField descr = new TextField(ee.getDescription());
                descr.setMaxSize(255);
                ed.add(descr);
                TextField adr = new TextField(ee.getAdresse());
                ed.add(adr);
                TextField ty = new TextField(ee.getType());
                ed.add(ty);
                TextField pri = new TextField(String.valueOf(ee.getPrix()));
                ed.add(pri);
                pri.addDataChangedListener((i1, i2) -> {
                    RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                    if (!re.match(pri.getText())) {
                        Dialog.show("Alerte", "Le champ  prix doit etre de type nombre", "Ok", null);
                        tnb = false;
                    } else {
                        tprix = true;
                    }
                });
                TextField nbpl = new TextField(ee.getNbPlace());
                ed.add(nbpl);
                nbpl.addDataChangedListener((i1, i2) -> {
                    RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                    if (!re.match(nbpl.getText())) {
                        Dialog.show("Alerte", "Le champ nbre de place doit etre de type nombre", "Ok", null);
                        tnb = false;
                    } else {
                        tprix = true;
                    }
                });
                Picker datedebut = new Picker();
                datedebut.setType(Display.PICKER_TYPE_DATE);
                ed.add(datedebut);

                Picker datefin = new Picker();
                datefin.setType(Display.PICKER_TYPE_DATE);
                ed.add(datefin);
                DateTimeSpinner hour = new DateTimeSpinner();
                int h = hour.getCurrentHour();
                int m = hour.getCurrentMinute();
                ed.add(hour);
                Container cTyp = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label labTyp = new Label("Type: ");

                txType.setStrings(new String[]{"musical", "vide dressing", "culturel", "autres"});

                cTyp.add(labTyp);
                cTyp.add(txType);
                SimpleDateFormat sdff = new SimpleDateFormat("hhmm");
                datedebut.getAllStyles().setFgColor(com.codename1.charts.util.ColorUtil.GREEN);
                datefin.getAllStyles().setFgColor(com.codename1.charts.util.ColorUtil.GRAY);
                datefin.setShowMeridiem(false);
                datedebut.setShowMeridiem(false);
                hour.setShowMeridiem(false);
                ImageViewer img = new ImageViewer();
                Button btnG = new Button("Choisir Image");

                btnG.addActionListener((evt1) -> {
                    ActionListener callback = eee -> {
                        if (ee != null && eee.getSource() != null) {
                            try {
                                this.newfilePath = (String) eee.getSource();
                                img.setImage(Image.createImage(this.newfilePath));
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

                Button save = new Button("enregistrer les modifications");

                save.addActionListener((evv1) -> {
                    String dd = datedebut.getText();
                    String year = dd.substring(dd.length() - 2, dd.length());
                    int dy = Integer.parseInt(year);
                    String month = (dd.substring(dd.indexOf("/") + 1)).substring(0, dd.indexOf("/"));
                    int dm = Integer.parseInt(month);
                    String day = dd.substring(0, dd.indexOf("/"));
                    int dday = Integer.parseInt(day);

                    String dff = datefin.getText();
                    String yearf = dff.substring(dff.length() - 2, dff.length());
                    int dfy = Integer.parseInt(yearf);
                    String monthf = (dff.substring(dff.indexOf("/") + 1)).substring(0, dff.indexOf("/"));
                    int dfm = Integer.parseInt(monthf);
                    String dayf = dff.substring(0, dff.indexOf("/"));
                    int ddayf = Integer.parseInt(dayf);
                    System.out.println("year" + dfy);

                    if ((dy > dfy || (dy == dfy && dday > ddayf) || (dy == dfy && dday == ddayf && dm > dfm) || nome.getText().equals("")) || (txType.getSelectedStringIndex() < 0) || (descr.getText().equals("")) || (pri.getText().equals("")) || (nbpl.getText().equals("")) || (adr.getText().equals(""))) {
                        Dialog.show("Alerte", "Veuillez remplir tous les champs ou vérifier les données saisies", "Ok", null);

                    } else {
                        if (!this.newfilePath.equals("") && this.newfilePath != null) {
                            Evenement event = new Evenement(ee.getId());

                            event.setNomEvenement(nome.getText());
                            event.setDateDebut(datedebut.getDate());
                            event.setDateFin(datefin.getDate());
                            event.setHeures(String.valueOf(h) + ":" + String.valueOf(m));
                            event.setAdresse(adr.getText());
                            event.setPrix(Double.parseDouble(pri.getText()));
                            event.setDescription(descr.getText());
                            event.setNbPlace(Integer.parseInt(nbpl.getText()));
                            event.setType(txType.getSelectedString());
                            event.setPhoto(this.newfilePath);
                            this.Evs.edEvent(event, false);
                            ListEvents levent = new ListEvents();
                            levent.getFe().showBack();
                            System.out.println("nome " + event.getNomEvenement());
                            System.out.println("nome2 " + nome.getText());
                            save.getStyle().setPadding(Component.LEFT, 10);
                            save.getStyle().setPadding(Component.RIGHT, 10);
                            System.out.println("///////////" + event.getIdUser());

                        }
                    }
                });

                ed.add(cTyp);
                ed.add(btnG);
                ed.add(save);
                ed.show();

            });
            //////////////////////DELETE//////////////////////////////
            Button supp = new Button("Supprimer");
            if (ee.getIdUser() != SessionUser.getUser().getId()) {
                supp.setVisible(false);

            }

            supp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
             Dialog.show("Alerte", "etes vous surz de vouloir supprimer", "Ok", null);

                    Evs.Delete(ee, SessionUser.getUser().getId(), ee.getId());
                    ListEvents levent = new ListEvents();
                    levent.getFe().showBack();
                }
            });

            ///////////////////////////////////Detail////////////////////////////////          
            det.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form f2 = new Form(ee.getNomEvenement(), new BoxLayout(BoxLayout.Y_AXIS));
                    ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + ee.getPhoto(),
                            "http://localhost/SoukLemdina/web/uploads/images/" + ee.getPhoto()));
                    Label d = new Label("Description:");
                    SpanLabel des = new SpanLabel(ee.getDescription());
                    des.getTextAllStyles().setFont(smallLightSystemFont);

                    s1 = new Slider();

                    rat1 = new Rating(s1);
                    s1 = rat1.createStarRankSlider();
                    Rating rat2 = new Rating(s1);

                    s1.setProgress(ee.getRating());
                    s1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Evenement event = new Evenement();
                            event.setId(ee.getId());
                            event.setRating(s1.getProgress());
                            Evs.edEvent(event, true);

                        }
                    });
                    System.out.println("********" + ee.getRating());
                    SpanLabel datef = new SpanLabel("Fin:" + ee.getDatefins());
                    //   datef.getStyle().setFgColor();
                    SpanLabel dated = new SpanLabel("Debut:   " + ee.getDateDebuts());
                    f2.addComponent(dated);
                    f2.addComponent(datef);
                    f2.addComponent(img1);
                    f2.addComponent(d);
                    f2.addComponent(des);

                    f2.add(rat2.getF());
                    ParticipantsEvents pa = new ParticipantsEvents();
                    Button part = new Button("Participer");
                    if (ee.getIdUser() != SessionUser.getUser().getId()) {
                        part.setVisible(true);
                    }
//               
                    if (ee.getIdUser() != SessionUser.getUser().getId()) {
                        if (pes.SwitchBTn(ee.getId(), SessionUser.getUser().getId()).equals("{saf=true}")) {
                            part.setText("Ne Plus Participer");
                        }

                        System.out.println("part: " + pes.SwitchBTn(p.getIdEvenement(), p.getIdUser()));
                        part.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                pes.Switch(ee.getId(), SessionUser.getUser().getId());
                                if (pes.SwitchBTn(ee.getId(), SessionUser.getUser().getId()).equals("{saf=true}")) {
                                    part.setText("Ne Plus Participer");
                                } else {
                                    part.setText(" Participer");
                                }
                            }
                        });

                        f2.add(part);

                    }

                    f2.show();
                    f2.getToolbar().addCommandToLeftBar("", theme.getImage("back-command.png"), b -> {
                        fe.showBack();
                    });

                }

            });

            c1.getAllStyles().setBorder(Border.createInsetBorder(3, 0x00fa9a));

            fe.getAllStyles().setBgTransparency(200);
            fe.addComponent(c1);
            // fe.addComponent(cy);
            fe.add(det);
            fe.add(edit);
            fe.add(supp);

        }
    }

    public boolean isNumber(String s) {
        RE ra = new RE("(20|21|22|70|71|50|51)[0-9][0-9][0-9][0-9][0-9][0-9]$");
        boolean matcher = ra.match(s);
        return matcher;
    }

    public Form getFe() {
        return fe;
    }

    public void setFe(Form fe) {
        this.fe = fe;
    }

    String[] searchLocations(String text) {
        try {
            if (text.length() > 0) {
                ConnectionRequest r = new ConnectionRequest();
                r.setPost(false);
                r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
                r.addArgument("key", apiKey);
                r.addArgument("input", text);
                NetworkManager.getInstance().addToQueueAndWait(r);
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                String[] res = Result.fromContent(result).getAsStringArray("//description");
                return res;
            }
        } catch (Exception err) {
            // Log.e(err);
        }
        return null;
    }

}
