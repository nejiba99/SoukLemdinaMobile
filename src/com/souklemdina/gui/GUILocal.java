/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.messaging.Message;
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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;

import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;

import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.regex.RE;

import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.Local;
import com.souklemdina.entities.Location;
import com.souklemdina.services.LocalServices;
import com.souklemdina.services.LocationServices;
import com.souklemdina.util.ImageViewerHerit;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class GUILocal {

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    private void updateColors(Style s) {
        s.setFgColor(ColorUtil.rgb(160, 160, 160));
    }

    private void updateColors1(Style s) {
        s.setFgColor(ColorUtil.rgb(64, 64, 64));
    }
    //rafraichir les pages + click pr plus de détail + revoir modif et ajout et louer +image

    Form f;
    LocalServices ls = new LocalServices();
    LocationServices lst = new LocationServices();
    private String newfilePath = "";
    boolean testPrix = true;
    boolean testSup = true;
    boolean testTel = true;
    ArrayList<Local> listLU;
    ArrayList<Local> listLocal;
    String dateFinaleD, dateFinaleF;

    public boolean isNumber(String s) {
        RE r = new RE("(20|21|22|70|71|50|51)([0-9][0-9][0-9][0-9][0-9][0-9])+");
        boolean matcher = r.match(s);
        return matcher;
    }

    public GUILocal() {

        Font italicLight = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);

        Resources theme = UIManager.initFirstTheme("/theme");
        Image flech = theme.getImage("cal_left_arrow.png");

        Form ad = new Form("Nouveau local", new BoxLayout(BoxLayout.Y_AXIS));
        ad.getAllStyles().setBgColor(0x696969);

        Form me = new Form("Mes locaux", new BoxLayout(BoxLayout.Y_AXIS));
        me.getToolbar().addCommandToLeftBar("Retour", flech, e -> {
            f.getContentPane().addPullToRefresh(() -> {
                System.out.println("refreshed");
            });
            GUILocal gl = new GUILocal();
            gl.getF().getAllStyles().setBgColor(0x696969);
            gl.getF().showBack();

        });
        me.getToolbar().addCommandToRightBar("Nouveau", null, e -> {
            ad.show();
        });

        ad.getToolbar().addCommandToLeftBar("Retour", flech, e -> {
            listLU = ls.afficherUser(3);
            me.getContentPane().addPullToRefresh(() -> {
                System.out.println("refreshed");
            });
            me.getComponentForm().showBack();
//            me.showBack();
        });
        me.getAllStyles().setBgColor(0x696969);

        //Ajout Local
        Container cTyp = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label labTyp = new Label("Type: ");
        Picker txtTyp = new Picker();
        txtTyp.setStrings(new String[]{"Restaurant", "Boutique", "Café", "Espace vide", "Autres"});

        cTyp.add(labTyp);
        cTyp.add(txtTyp);

        Container cDesc = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label labDesc = new Label("Description: ");
        TextField txtDesc = new TextField("", "Description", 500, TextField.ANY);
        cDesc.add(labDesc);
        cDesc.add(txtDesc);

        Container cPri = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label labPri = new Label("Prix: ");
        TextField txtPri = new TextField("", "Prix", 100, TextField.NUMERIC);
        txtPri.addActionListener((i1) -> {
            RE re = new RE("[0-9]+");
            if (!re.match(txtPri.getText())) {
                Dialog.show("Alerte", "Le champ prix doit etre de type nombre", "Ok", null);
                testPrix = false;
            } else {
                testPrix = true;
            }
        });

        cPri.add(labPri);
        cPri.add(txtPri);

        Container cSup = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label labSup = new Label("Superficie: ");
        TextField txtSup = new TextField("", "Superficie", 100, TextField.NUMERIC);
        txtSup.addActionListener((i2) -> {
            RE re = new RE("[0-9]+");
            if (!re.match(txtSup.getText())) {
                Dialog.show("Alerte", "Le champ superficie doit etre de type nombre", "Ok", null);
                testSup = false;
            } else {
                testSup = true;
            }
        });
        cSup.add(labSup);
        cSup.add(txtSup);

        Container cAdr = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label labAdr = new Label("Adresse: ");
        TextField txtAdr = new TextField("", "Adresse", 100, TextField.ANY);
        cAdr.add(labAdr);
        cAdr.add(txtAdr);

        Container cTel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label labTel = new Label("Téléphone: ");
        TextField txtTel = new TextField("", "Téléphone", 8, TextField.NUMERIC);
        txtTel.addActionListener((i) -> {
            if (!isNumber(txtTel.getText()) || txtTel.getText().length() != 8) {
                Dialog.show("Alerte", "Le champ Téléphone doit etre de type nombre et comporte 8 chiffres", "Ok", null);
                testTel = false;
            } else {
                testTel = true;
            }
        });
        cTel.add(labTel);
        cTel.add(txtTel);

        Container cImg = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cImg.getAllStyles().setPaddingBottom(20);
        Label labImg = new Label("Image: ");
        ImageViewer adIv = new ImageViewer();
        adIv.setPreferredH(100);
        adIv.setPreferredW(100);
        Button btnImg = new Button("Parcourrir");

        updateColors1(btnImg.getUnselectedStyle());
        updateColors1(btnImg.getSelectedStyle());
        Button btnAdd = new Button("Ajouter");

        updateColors1(btnAdd.getUnselectedStyle());
        updateColors1(btnAdd.getSelectedStyle());
        Container cx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cx.add(labImg);
        cx.add(btnImg);
        cImg.add(cx);
        cImg.add(adIv);

        ad.add(cTyp);
        ad.add(cDesc);
        ad.add(cPri);
        ad.add(cSup);
        ad.add(cAdr);
        ad.add(cTel);
        ad.add(cImg);
        ad.add(btnAdd);

        btnImg.addActionListener((evt) -> {
            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    try {
                        this.newfilePath = (String) e.getSource();
                        adIv.setImage(Image.createImage(this.newfilePath));
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

        btnAdd.addActionListener((evt1) -> {

            if ((testPrix == false) || (testSup == false) || (testTel == false) || (txtTyp.getSelectedStringIndex() < 0) || (txtDesc.getText().equals("")) || (txtPri.getText().equals("")) || (txtSup.getText().equals("")) || (txtAdr.getText().equals("")) || (txtTel.getText().equals(""))) {
                Dialog.show("Alerte", "Veuillez remplir tous les champs ou vérifier les données saisies", "Ok", null);
            } else {

                if (!this.newfilePath.equals("") && this.newfilePath != null) {
                    Local nLocal = new Local();
                    nLocal.setType(txtTyp.getSelectedString());
                    nLocal.setDescription(txtDesc.getText());
                    nLocal.setPrix(Integer.parseInt(txtPri.getText()));
                    nLocal.setSuperficie(Integer.parseInt(txtSup.getText()));
                    nLocal.setAdresse(txtAdr.getText());
                    nLocal.setTelephone(txtTel.getText());
                    nLocal.setImage(this.newfilePath);
                    //recuperer idUser
                    ls.ajouterLocal(nLocal, 3);
                    listLU.add(nLocal);
                    listLocal.add(nLocal);
                    Dialog.show("Succes", "Local ajoute", "OK", null);
                }
            }
        });

        listLU = ls.afficherUser(SessionUser.getUser().getId());
        for (Local l : listLU) {
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            c.getAllStyles().setPaddingTop(25);
            c.getAllStyles().setPaddingBottom(25);

            c.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.rgb(32, 32, 32)));
            Image placeholder = Image.createImage(me.getWidth() / 3 - 4, me.getWidth() / 3 - 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + l.getImage(),
                    "http://localhost/SoukLemdina/web/uploads/images/" + l.getImage()));

            Container cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container contx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label lab = new Label(l.getType());
            lab.getStyle().setPadding(Component.LEFT, 60);
            lab.getStyle().setPadding(Component.TOP, 80);

            Button supp = new Button("Supprimer");
            supp.getAllStyles().setBorder(Border.createEmpty());
            supp.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            updateColors(supp.getUnselectedStyle());
            updateColors(supp.getSelectedStyle());

            Button mod = new Button("Modifier");
            mod.getAllStyles().setBorder(Border.createEmpty());
            mod.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
            updateColors(mod.getUnselectedStyle());
            updateColors(mod.getSelectedStyle());

            contx.add(supp);
            contx.add(mod);
            cont.add(lab);
            cont.add(contx);
            c.add(img);
            c.add(cont);
            me.add(c);

            supp.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (Dialog.show("Confirmation", "Etes vous sûr de vouloir supprimer ce local?", "Oui", "Annuler")) {
                        ls.supprimerLocal(l.getId());
//                        listLU.remove(l);
//                        listLU = ls.afficherUser(3);
                        Dialog.show("Succes", "Local supprime", "OK", null);
                        me.getComponentForm().showBack();
                    }

                }
            });

            mod.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form up = new Form("Mes Locaux", new BoxLayout(BoxLayout.Y_AXIS));
                    up.getToolbar().addCommandToLeftBar("Retour", flech, e -> {

                        me.showBack();
                    });
                    up.getAllStyles().setBgColor(0x696969);
                    Container a = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label adr = new Label("Adresse:");
                    TextField adrT = new TextField();
                    adrT.setText(l.getAdresse());
                    a.add(adr);
                    a.add(adrT);
                    Container b = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label tel = new Label("Téléphone:");
                    TextField telT = new TextField();
                    telT.setText(l.getTelephone());
                    telT.addActionListener((e) -> {
                        if (!isNumber(txtTel.getText()) || txtTel.getText().length() != 8) {
                            Dialog.show("Alerte", "Le champ Téléphone doit etre de type nombre et comporte 8 chiffres", "Ok", null);
                            testTel = false;
                        } else {
                            testTel = true;
                        }
                    });
                    b.add(tel);
                    b.add(telT);
                    Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label typ = new Label("Type:");
                    Picker typT = new Picker();
                    typT.setStrings(new String[]{"Restaurant", "Boutique", "Café", "Espace vide", "Autres"});
                    typT.setText(l.getType());
                    c.add(typ);
                    c.add(typT);
                    Container d = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label pri = new Label("Prix:");
                    TextField priT = new TextField("", "Prix", 100, TextField.NUMERIC);
                    priT.setText(String.valueOf(l.getPrix()));
                    priT.addActionListener((e) -> {
                        RE re = new RE("[0-9]+");
                        if (!re.match(txtPri.getText())) {
                            Dialog.show("Alerte", "Le champ prix doit etre de type nombre", "Ok", null);
                            testPrix = false;
                        } else {
                            testPrix = true;
                        }
                    });
                    d.add(pri);
                    d.add(priT);
                    Container e = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label sup = new Label("Superficie:");

                    TextField supT = new TextField("", "Superficie", 100, TextField.NUMERIC);
                    supT.setText(String.valueOf(l.getSuperficie()));
                    supT.addActionListener((e1) -> {
                        RE re = new RE("[0-9]+");
                        if (!re.match(txtPri.getText())) {
                            Dialog.show("Alerte", "Le champ superficie doit etre de type nombre", "Ok", null);
                            testPrix = false;
                        } else {
                            testPrix = true;
                        }
                    });
                    e.add(sup);
                    e.add(supT);
                    Container f = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label desc = new Label("Description:");
                    TextField descT = new TextField("", "Description", 500, TextField.ANY);
                    descT.setText(l.getDescription());
                    descT.getAllStyles().setPaddingBottom(20);
                    f.add(desc);
                    f.add(descT);
                    Button bt = new Button("Valider");

                    updateColors1(bt.getUnselectedStyle());
                    updateColors1(bt.getSelectedStyle());
                    up.add(a);
                    up.add(b);
                    up.add(c);
                    up.add(d);
                    up.add(e);
                    up.add(f);
                    up.add(bt);

                    bt.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            // (typT.getSelectedStringIndex()<0)||
                            if ((testPrix == false) || (testSup == false) || (testTel == false) || (descT.getText().equals("")) || (priT.getText().equals("")) || (supT.getText().equals("")) || (adrT.getText().equals("")) || (telT.getText().equals(""))) {
                                Dialog.show("Alerte", "Veuillez remplir tous les champs ou vérifier les données saisies", "Ok", null);
                            } else {
                                if (Dialog.show("Confirmation", "Voulez vous enregistrer les modifications apportés?", "Oui", "Annuler")) {

                                    l.setAdresse(adrT.getText());
                                    l.setTelephone(telT.getText());
                                    l.setType(typT.getText());
                                    l.setPrix(Integer.parseInt(priT.getText()));
                                    l.setSuperficie(Integer.parseInt(supT.getText()));
                                    l.setDescription(descT.getText());
                                    Dialog.show("Succes", "Local mis a jour", "OK", null);
                                }
                            }
                        }
                    });
                    up.show();
                }

            });
        }

        f = new Form("Locaux ElSouk", new BoxLayout(BoxLayout.Y_AXIS));
//        f.getToolbar().addCommandToLeftBar("Retour", flech, e -> {
//        });//chemin back 
        f.getToolbar().addCommandToRightBar("Mes locaux", null, e -> {
            me.show();
        });//icon + chemin mes locaux

        //Afficher locaux elSouk
        listLocal = ls.afficheer();
        for (Local l : listLocal) {

            System.out.println(l.getId());

            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));

            c.getAllStyles().setPaddingTop(25);
            c.getAllStyles().setPaddingBottom(25);
            c.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.rgb(32, 32, 32)));

            Image placeholder = Image.createImage(f.getWidth() / 3 - 4, f.getWidth() / 3 - 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file"+l.getImage(),
                    "http://localhost/SoukLemdina/web/uploads/images/" + l.getImage()));

            System.out.println(l.getImage());
            Label lab = new Label(l.getType());
            lab.getStyle().setPadding(Component.LEFT, 60);
            lab.getStyle().setPadding(Component.TOP, 25);

            c.add(img);
            c.add(lab);
            String Desc = l.getDescription();
            int pr = l.getPrix();
            int su = l.getSuperficie();
            String adre = l.getAdresse();
            String te = l.getTelephone();

            lab.addPointerPressedListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form det = new Form(l.getType(), new BoxLayout(BoxLayout.Y_AXIS));
                    det.getAllStyles().setBgColor(0x696969);
                    Image placeholderDet = Image.createImage(f.getWidth(), f.getWidth(), 0xbfc9d2);
                    EncodedImage encImageDet = EncodedImage.createFromImage(placeholderDet, false);
                    ImageViewer imgDet = new ImageViewer(URLImage.createToStorage(encImageDet, "file" + l.getImage(),
                            "http://localhost/SoukLemdina/web/uploads/images/" + l.getImage()));
                    Container ctx = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel lab = new SpanLabel("Description: ");
                    lab.getAllStyles().setPaddingRight(20);
                    SpanLabel labb = new SpanLabel(Desc);
                    ctx.add(lab);
                    ctx.add(createForFont(italicLight, labb.getText()));

                    Container ctx1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel lab1 = new SpanLabel("Prix: ");
                    lab1.getAllStyles().setPaddingRight(20);
                    SpanLabel labb1 = new SpanLabel(String.valueOf(pr));
                    ctx1.add(lab1);
                    ctx1.add(createForFont(italicLight, labb1.getText()));

                    Container ctx2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel lab2 = new SpanLabel("Superficie: ");
                    lab2.getAllStyles().setPaddingRight(20);
                    SpanLabel labb2 = new SpanLabel(String.valueOf(su));
                    ctx2.add(lab2);
                    ctx2.add(createForFont(italicLight, labb2.getText()));

                    Container ctx3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel lab3 = new SpanLabel("Adresse: ");
                    lab3.getAllStyles().setPaddingRight(20);
                    SpanLabel labb3 = new SpanLabel(adre);
                    ctx3.add(lab3);
                    ctx3.add(createForFont(italicLight, labb3.getText()));

                    Container ctx4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    SpanLabel lab4 = new SpanLabel("Téléphone: ");
                    lab4.getAllStyles().setPaddingRight(20);
                    SpanLabel labb4 = new SpanLabel(te);
                    labb4.getAllStyles().setPaddingBottom(20);
                    ctx4.add(lab4);
                    ctx4.add(createForFont(italicLight, labb4.getText()));

                    Button btnLouer = new Button("Louer");

                    updateColors1(btnLouer.getUnselectedStyle());
                    updateColors1(btnLouer.getSelectedStyle());

                    btnLouer.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Form rent = new Form("Location", new BoxLayout(BoxLayout.Y_AXIS));
                            rent.getAllStyles().setBgColor(0x696969);

                            rent.getToolbar().addCommandToLeftBar("Retour", flech, e -> {
                                det.show();
                            });
                            Label labFrom = new Label("Date Début:");
                            Label labTo = new Label("Date Fin:");
                            Picker from = new Picker();
                            from.setType(Display.PICKER_TYPE_DATE);
                            from.setDate(new Date());
                            Picker to = new Picker();
                            to.setType(Display.PICKER_TYPE_DATE);
                            to.setDate(new Date());

                            Button btnVL = new Button("Valider");
                            updateColors1(btnVL.getUnselectedStyle());
                            updateColors1(btnVL.getSelectedStyle());

                            btnVL.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    String dt = from.getText();
                                    System.out.println(dt);
                                    String ft = to.getText();
                                    String dyear = dt.substring(dt.length() - 2, dt.length());
                                    int ddY = Integer.parseInt(dyear);
                                    String dmonth = (dt.substring(dt.indexOf('/') + 1)).substring(0, dt.indexOf('/'));

                                    int ddM = Integer.parseInt(dmonth);
                                    String dday = dt.substring(0, dt.indexOf('/'));
                                    int ddD = Integer.parseInt(dday);

                                    String fyear = ft.substring(ft.length() - 2, ft.length());
                                    int dfY = Integer.parseInt(fyear);
                                    String fmonth = (ft.substring(ft.indexOf('/') + 1)).substring(0, ft.indexOf('/'));
                                    int dfM = Integer.parseInt(fmonth);
                                    String fday = ft.substring(0, ft.indexOf('/'));
                                    int dfD = Integer.parseInt(fday);
                                    String dateNow = String.valueOf(new Date());
                                    String yearN = dateNow.substring(26, 28);
                                    String monthN = dateNow.substring(4, 7);
                                    String dayN = dateNow.substring(8, 10);
                                    int yn = Integer.parseInt(yearN);
                                    int mn = 0;
                                    if (monthN.equals("Jan")) {
                                        mn = 1;
                                    }
                                    if (monthN.equals("Feb")) {
                                        mn = 2;
                                    }
                                    if (monthN.equals("Mar")) {
                                        mn = 3;
                                    }
                                    if (monthN.equals("Apr")) {
                                        mn = 4;
                                    }
                                    if (monthN.equals("May")) {
                                        mn = 5;
                                    }
                                    if (monthN.equals("Jun")) {
                                        mn = 6;
                                    }
                                    if (monthN.equals("Jul")) {
                                        mn = 7;
                                    }
                                    if (monthN.equals("Aug")) {
                                        mn = 8;
                                    }
                                    if (monthN.equals("Sep")) {
                                        mn = 9;
                                    }
                                    if (monthN.equals("Oct")) {
                                        mn = 10;
                                    }
                                    if (monthN.equals("Nov")) {
                                        mn = 11;
                                    }
                                    if (monthN.equals("Dec")) {
                                        mn = 12;
                                    }
                                    int dn = Integer.parseInt(dayN);

//                                System.out.println(ddY+" "+dfY);
//                                System.out.println(ddM +" "+dfM);
//                                System.out.println(ddD +" "+dfD);
                                    int i = 0;
                                    ArrayList<Location> listLocat = lst.afficherLocations(l.getId());
                                    for (Location item : listLocat) {
                                        String ddt = String.valueOf(item.getDateDebutLocation());
                                        String year = ddt.substring(26, 28);
                                        System.out.println("year" + item.getDateDebutLocation());
                                        int ddlY = Integer.parseInt(year);
                                        String month = ddt.substring(4, 7);
                                        int ddlM = 0;
                                        System.out.println("month " + month);
                                        if (month.equals("Jan")) {
                                            ddlM = 1;
                                        }
                                        if (month.equals("Feb")) {
                                            ddlM = 2;
                                        }
                                        if (month.equals("Mar")) {
                                            ddlM = 3;
                                        }
                                        if (month.equals("Apr")) {
                                            ddlM = 4;
                                        }
                                        if (month.equals("May")) {
                                            ddlM = 5;
                                        }
                                        if (month.equals("Jun")) {
                                            ddlM = 6;
                                        }
                                        if (month.equals("Jul")) {
                                            ddlM = 7;
                                        }
                                        if (month.equals("Aug")) {
                                            ddlM = 8;
                                        }
                                        if (month.equals("Sep")) {
                                            ddlM = 9;
                                        }
                                        if (month.equals("Oct")) {
                                            ddlM = 10;
                                        }
                                        if (month.equals("Nov")) {
                                            ddlM = 11;
                                        }
                                        if (month.equals("Dec")) {
                                            ddlM = 12;
                                        }
                                        String day = ddt.substring(8, 10);
                                        int ddlD = Integer.parseInt(day);

                                        String dft = String.valueOf(item.getDateFinLocation());
                                        String yearf = dft.substring(26, 28);
                                        int dflY = Integer.parseInt(yearf);
                                        String monthf = dft.substring(4, 7);
                                        int dflM = 0;
                                        if (monthf.equals("Jan")) {
                                            dflM = 1;
                                        }
                                        if (monthf.equals("Feb")) {
                                            dflM = 2;
                                        }
                                        if (monthf.equals("Mar")) {
                                            dflM = 3;
                                        }
                                        if (monthf.equals("Apr")) {
                                            dflM = 4;
                                        }
                                        if (monthf.equals("May")) {
                                            dflM = 5;
                                        }
                                        if (monthf.equals("Jun")) {
                                            dflM = 6;
                                        }
                                        if (monthf.equals("Jul")) {
                                            dflM = 7;
                                        }
                                        if (monthf.equals("Aug")) {
                                            dflM = 8;
                                        }
                                        if (monthf.equals("Sep")) {
                                            dflM = 9;
                                        }
                                        if (monthf.equals("Oct")) {
                                            dflM = 10;
                                        }
                                        if (monthf.equals("Nov")) {
                                            dflM = 11;
                                        }
                                        if (monthf.equals("Dec")) {
                                            dflM = 12;
                                        }

                                        String dayf = dft.substring(8, 10);
                                        int dflD = Integer.parseInt(dayf);
                                        String dateDebut = "20" + ddY + "-" + ddD + "-" + ddM;
                                        String dateFin = "20" + dfY + "-" + dfD + "-" + dfM;
                                        String d1 = "20" + ddlY + "-" + ddlD + "-" + ddlM;
                                        String d2 = "20" + dflY + "-" + dflD + "-" + dflM;
                                        dateFinaleD = dateDebut;
                                        dateFinaleF = dateFin;
                                        System.out.println("d1 " + d1);
                                        System.out.println("d2 " + d2);
                                        System.out.println("pc " + dateDebut);
                                        if (dateDebut.equals(d1) || dateFin.equals(d2)) {
                                            i++;
                                        }

                                    }
//                                    
                                    if (ddY > dfY || (ddY == dfY && ddD > dfD) || (ddY == dfY && ddD == dfD && ddM > dfM) || (ddY < yn) || (dfY < yn) || (ddY == yn && ddD < dn) || (ddY == yn && ddD == dn && ddM < mn) || (dfY == yn && dfD < dn) || (dfY == yn && dfD == dn && dfM < mn)) {
                                        Dialog.show("Erreur", "Données eronnés", "OK", null);

                                    } else if (i == 0) {
                                        lst.ajoutLocation(l.getId(), 3, dateFinaleD, dateFinaleF);
                                        Message m = new Message("Une demande de location de votre " + l.getType() + " du " + dateFinaleD + " au " + dateFinaleF);
                                        Display.getInstance().sendMessage(new String[]{"malek.touzri@esprit.tn"}, "Demande de location", m);
//                              Dialog.show ("Succés","Un mail a été envoyé au proprio vous recervrez bientôt une réponse de sa part!","OK",null);
                                    } else {
                                        Dialog.show("Erreur", "Date non disponible", "OK", null);
                                    }
                                }
                            });

                            rent.add(labFrom);
                            rent.add(from);
                            rent.add(labTo);
                            rent.add(to);
                            rent.add(btnVL);
                            rent.show();
                        }
                    });

                    det.add(imgDet);
                    det.add(ctx);
                    det.add(ctx1);
                    det.add(ctx2);
                    det.add(ctx3);
                    det.add(ctx4);
                    det.add(btnLouer);
                    det.show();
                    det.getToolbar().addCommandToLeftBar("Retour", flech, e -> {
                        f.showBack();
                    });

                }
            });
            f.addComponent(c);

        }
        f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
