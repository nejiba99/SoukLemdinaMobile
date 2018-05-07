/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import static com.codename1.io.rest.Rest.options;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;

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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.regex.RE;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.ParticipantWork;
import com.souklemdina.entities.Workshop;
import com.souklemdina.services.ParticipantWorkService;
import com.souklemdina.services.WorkshopServices;
//import com.souklemdina.util.ImageViewerHerit;
import com.souklemdina.util.SessionUser;
import com.souklemdina.util.UploadFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ListeWorks {

    boolean testPrix = true;
    boolean testnb = true;
    private static final String apiKey = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    final DefaultListModel<String> options = new DefaultListModel<>();
    WorkshopServices ws = new WorkshopServices();
    FosUser fu = new FosUser(5);
    ParticipantWork p = new ParticipantWork();
    public ParticipantWorkService pw = new ParticipantWorkService();
    private Form f;
    private Container c1, c2;
    private SpanLabel sl;
    private String newfilePath = "";
    Workshop w = new Workshop();
    Workshop ww = new Workshop();
    Workshop www = new Workshop();

    //int nbp=w.getNbPlace();
    int nbp = 0;
    int nbPart = 0;


    public ListeWorks() {
        Resources theme = UIManager.initFirstTheme("/theme");

        f = new Form("Liste des Workshops", new BoxLayout(BoxLayout.Y_AXIS));

        SessionUser.setUser(new FosUser(3));
        Picker txTyp = new Picker();

        Button btnAj = new Button("Ajouter Votre Workshop");

        btnAj.addActionListener((evt) -> {
            Form fa = new Form("Ajouter Votre Workshop", BoxLayout.y());
            Image icon = theme.getImage("back-command.png");
            icon = icon.scaled(70, 90);
            fa.getToolbar().addCommandToLeftBar("", icon, e -> {
                ListeWorks acObj = new ListeWorks();
                acObj.getf().showBack();
            });

            TextField nom = new TextField("", "Nom workshop");
            fa.add(nom);
//           // TextField adres = new TextField("", "Adresse");
//            adres.setMaxSize(255);
//            fa.add(adres);
            Label labD = new Label("Adresse ");
            AutoCompleteTextField ac = new AutoCompleteTextField(options) {
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
            ac.setMinimumElementsShownInPopup(5);
            ImageViewer iAdd = new ImageViewer();
            fa.add(labD);
            fa.add(ac);
            Picker datedeb = new Picker();
            datedeb.setType(Display.PICKER_TYPE_DATE);
            datedeb.setDate(new Date());
     
            fa.add(datedeb);
            Picker datefin = new Picker();
            datefin.setType(Display.PICKER_TYPE_DATE);
            datefin.setDate(new Date());
            
               
            fa.add(datefin);
            //Picker txtTyp = new Picker();
            Container cTyp = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label labTyp = new Label("Type: ");

            txTyp.setStrings(new String[]{"patisserie Traditionnelle", "Haute couture", "autres"});

            cTyp.add(labTyp);
            cTyp.add(txTyp);
            TextField desc = new TextField("", "Description");
            fa.add(desc);
            TextField nb = new TextField("", "nbre de place", 100, TextField.NUMERIC);
            nb.addDataChangedListener((i1, i2) -> {
                RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                if (!re.match(nb.getText())) {
                    Dialog.show("Alerte", "Le champ nbre de place doit etre de type nombre", "Ok", null);
                    testnb = false;
                } else {
                    testPrix = true;
                }
            });
            datefin.addPointerReleasedListener((l)->{
                
            });
            
            fa.add(nb);

            TextField prix = new TextField("", "Prix", 100, TextField.NUMERIC);
            prix.addDataChangedListener((i1, i2) -> {
                RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                if (!re.match(prix.getText())) {
                    Dialog.show("Alerte", "Le champ prix doit etre de type nombre", "Ok", null);
                    testPrix = false;
                } else {
                    testPrix = true;
                }
            });
            fa.add(prix);
            Container hcc = new Container(BoxLayout.y());
            Button btnOpen = new Button("Choisir Image");
            btnOpen.addActionListener((evt1) -> {
                ActionListener callback = e -> {
                    if (e != null && e.getSource() != null) {
                        try {
                            this.newfilePath = (String) e.getSource();
                            iAdd.setImage(Image.createImage(this.newfilePath));
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
            // ImageViewer ill = new ImageViewer();
            //fa.add(ill);

            Button btnConf = new Button("Ajouter");
            btnConf.addActionListener((evt1) -> {
                
                      String dd =datedeb.getText();
           String  year = dd.substring(dd.length()-2,dd.length());
           int dy = Integer.parseInt(year);
           String month = (dd.substring(dd.indexOf("/")+1)).substring(0, dd.indexOf("/"));
           int dm = Integer.parseInt(month);
           String day = dd.substring(0,dd.indexOf("/"));
           int dday = Integer.parseInt(day); 
                
           String df =datefin.getText();
           String  yearf= df.substring(df.length()-2,df.length());
           int dfy = Integer.parseInt(yearf);
           String monthf = (df.substring(df.indexOf("/")+1)).substring(0, df.indexOf("/"));
           int dfm = Integer.parseInt(monthf);
           String dayf = df.substring(0,df.indexOf("/"));
           int ddayf = Integer.parseInt(dayf);
            System.out.println("year"+dfy);
            
                if (dy>dfy || (dy==dfy && dday>ddayf) ||(dy==dfy && dday==ddayf && dm>dfm)||
                        (nom.getText().equals(""))  ||
                        (ac.getText().equals("")) ||
                        (desc.getText().equals("")) || (txTyp.getSelectedStringIndex() < 0)|| 
                        (prix.getText().equals("")) || 
                        (nb.getText().equals("")) ||
                         (desc.getText().equals(""))) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs ou vérifier les données saisies", "Ok", null);
                } else {

                    if (!this.newfilePath.equals("") && this.newfilePath != null) {
                        Workshop work = new Workshop();
                        work.setNomWorkshop(nom.getText());
                        // work.setAdresse(adres.getText());
                        //work.setType(showForm(combo.getSelectedItem().toString()));
                        work.setType(txTyp.getSelectedString());
                        work.setDateDebut(datedeb.getDate());
                        work.setDateFin(datefin.getDate());
                        work.setPrix(Double.parseDouble(prix.getText()));
                        work.setNbPlace(Integer.parseInt(nb.getText()));
                        work.setImage(this.newfilePath);
                        this.ws.ajoutWork(work, SessionUser.getUser().getId());
                        nom.clear();
                        // adres.clear();
                        ListeWorks acObj = new ListeWorks();
                        acObj.getf().showBack();
                    }
                }
            });
            fa.add(cTyp);

            fa.add(btnOpen);
            fa.add(btnConf);
            fa.show();
        });
        f.add(btnAj);

        ArrayList<Workshop> LstWorks = ws.getListWorks();

        for (Workshop w : LstWorks) {

            System.out.println("wwwwwwwwww" + w.getId());
            // System.out.println(w.getNomWorkshop());
            c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container ctitre = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container cn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Image placeholder = Image.createImage(f.getWidth() / 3 - 4, f.getWidth() / 3 - 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img1 = new ImageViewer(URLImage.createToStorage(encImage, "file" + w.getImage(),
                    "http://localhost/SoukLemdina/web/uploads/images/" + w.getImage()));
            img1.setPreferredH(300);
            img1.setPreferredW(450);
            Container btSup = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           
           Button supp =new Button("Supprimer");
 if (w.getIdUser() != fu.getId()) {
                            supp.setVisible(false);
                        }
 //cn2.add(btSup);
  btSup.add(supp);
supp.addActionListener(new ActionListener() {
     
              @Override
              public void actionPerformed(ActionEvent evt) {
                  if(Dialog.show("Confirmation","Etes vous sûr de vouloir supprimer ce local?","Oui","Annuler")){
                  ws.Delete(w, SessionUser.getUser().getId(), w.getId());
                  ListeWorks lis = new ListeWorks();
                        lis.getf().showBack();
                   lis.getf().showBack();
                  }
             
              }
          });
            
           
            Label l = new Label(w.getNomWorkshop());
            Label pr = new Label(("Places: " + String.valueOf(w.getNbPlace())));
            Label des = new Label("Prix: " + String.valueOf(w.getPrix()));
            // Dimension d = new Dimension() ;
            // pr.setSize(d);
            pr.getAllStyles().getMarginTop();
            c2.add(img1);
            ctitre.add(l);

            c2.getAllStyles().setBorder(Border.createLineBorder(3, 0xffa83b));

            c2.getAllStyles().setBgTransparency(200);
            cn2.add(ctitre);
            cn2.add(pr);
            cn2.add(des);

            c2.add(cn2);

            l.addPointerPressedListener(new ActionListener() {
                WorkshopServices ws = new WorkshopServices();
                private String newfilePath;

                @Override
                public void actionPerformed(ActionEvent evt) {
                    //     System.out.println("idw ZEINEBBBBB "+p.idWorkshop);
//www.setNbPlace(w.getNbPlace());
//          www.setId(w.getId());
                    nbp = w.getNbPlace();
                    nbPart = ws.nbPar(w.getId());
                    System.out.println("www.getNbPlace()" + w.getNbPlace());
                    System.out.println("www.id()" + w.getId());
                    System.out.println(ws.nbPar(27));
                    System.out.println("ws.nbPar(www.getId())" + ws.nbPar(w.getId()));
                    // System.out.println("nbplaaaaaaaaaaaaaaaaaaaaaaaaaaaace "+w.getNbPlace());
                    // System.out.println("nbpméchlaaaaaaaaaaaaaaaaaaaaaaaaaaaace "+w.getId());
                    Form f1 = new Form(w.getNomWorkshop(), new BoxLayout(BoxLayout.Y_AXIS));
                    Container pie = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container cadre = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container cdes = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label espace = new Label();
                    SpanLabel sl = new SpanLabel("Description  : " + w.getDescription());
                    SpanLabel sT = new SpanLabel("Type  : " + w.getType());
                    cdes.getAllStyles().setBorder(Border.createLineBorder(5, 0xFF0000));
                    cdes.getAllStyles().setBgTransparency(200);
                    sT.getAllStyles().setBorder(Border.createLineBorder(5, 0xFF0000));
                    sT.getAllStyles().setBgTransparency(200);
                    cdes.add(sl);
                    cdes.add(sT);
                    Container cD = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    cD.getAllStyles().setBorder(Border.createLineBorder(5, 0xFF0000));
                    cD.getAllStyles().setBgTransparency(200);

                    Label D = new Label("Début : " + w.getDateDeb());
                    Label F = new Label("Fin : " + w.getDateF());
                    cD.addAll(D, F);
                    cadre.addAll(cD,espace ,cdes);
                    ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + w.getImage(),
                            "http://localhost/SoukLemdina/web/uploads/images/" + w.getImage()));

                    Button modif = new Button("Modifier");

                    modif.getAllStyles().getBackgroundGradientEndColor();
                    modif.addActionListener((evt1) -> {
                        if (w.getIdUser() != +SessionUser.getUser().getId()) {
                            modif.setVisible(false);
                        }
                        Form fa = new Form("Modifier un Workshop", BoxLayout.y());
                        Image icon2 = theme.getImage("back-command.png");
                        icon2 = icon2.scaled(70, 90);
                        fa.getToolbar().addCommandToLeftBar("", icon2, ezz -> {
                            ListeWorks lll = new ListeWorks();
                            lll.getf().showBack();
                        });

                        TextField nom = new TextField(w.getNomWorkshop());
                        fa.add(nom);
                        Label lad = new Label("Adresse ");
                        AutoCompleteTextField ac = new AutoCompleteTextField(options) {
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
                        ac.setMinimumElementsShownInPopup(5);
//            TextField adres = new TextField(w.getAdresse());
//            adres.setMaxSize(255);
                        fa.add(lad);
                        fa.add(ac);
                        ImageViewer iAdd = new ImageViewer();
                        Picker datedeb = new Picker();
                        datedeb.setType(Display.PICKER_TYPE_DATE);
                        fa.add(datedeb);
                        Picker datefin = new Picker();
                        datefin.setType(Display.PICKER_TYPE_DATE);
                        fa.add(datefin);
                        // fa.add(combo);

                        TextField desc = new TextField(w.getDescription());
                        fa.add(desc);
                        TextField nb = new TextField("", "nbre de place", 100, TextField.NUMERIC);
                        nb.addDataChangedListener((i1, i2) -> {
                            RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                            if (!re.match(nb.getText())) {
                                Dialog.show("Alerte", "Le champ nbre de place doit etre de type nombre", "Ok", null);
                                testnb = false;
                            } else {
                                testPrix = true;
                            }
                        });

                        TextField prix = new TextField("", "Prix", 100, TextField.NUMERIC);
                        prix.addDataChangedListener((i1, i2) -> {
                            RE re = new RE("([0-9]+(\\.[0-9]+)?)+");
                            if (!re.match(prix.getText())) {
                                Dialog.show("Alerte", "Le champ prix doit etre de type nombre", "Ok", null);
                                testPrix = false;
                            } else {
                                testPrix = true;
                            }
                        });
                        fa.add(prix);
                        fa.add(nb);
                        
                        Container cTyp = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Label labTyp = new Label("Type: ");

                        txTyp.setStrings(new String[]{"patisserie Traditionnelle", "Haute couture", "autres"});
                        cTyp.add(labTyp);
                        cTyp.add(txTyp);

                        Container hcc = new Container(BoxLayout.y());
                        Button btnOpen1 = new Button("Modifier Image");
                        btnOpen1.addActionListener((ett) -> {
                                        String dd =datedeb.getText();
           String  year = dd.substring(dd.length()-2,dd.length());
           int dy = Integer.parseInt(year);
           String month = (dd.substring(dd.indexOf("/")+1)).substring(0, dd.indexOf("/"));
           int dm = Integer.parseInt(month);
           String day = dd.substring(0,dd.indexOf("/"));
           int dday = Integer.parseInt(day); 
                
           String df =datefin.getText();
           String  yearf= df.substring(df.length()-2,df.length());
           int dfy = Integer.parseInt(yearf);
           String monthf = (df.substring(df.indexOf("/")+1)).substring(0, df.indexOf("/"));
           int dfm = Integer.parseInt(monthf);
           String dayf = df.substring(0,df.indexOf("/"));
           int ddayf = Integer.parseInt(dayf);
            System.out.println("year"+dfy);
            
                if (dy>dfy || (dy==dfy && dday>ddayf) ||(dy==dfy && dday==ddayf && dm>dfm)) {
                    Dialog.show("Alerte", "Date debut doit etre inf", "Ok", null);
                } 
                            ActionListener callback = e -> {
                                if (e != null && e.getSource() != null) {
                                    try {
                                        this.newfilePath = (String) e.getSource();
                                        iAdd.setImage(Image.createImage(this.newfilePath));
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

                        Button btnConf = new Button("Effectuer modifications");
                        btnConf.addActionListener((evt2) -> {
                            if ((nom.getText().equals(""))
                                    || (txTyp.getSelectedStringIndex() < 0)
                                    || (desc.getText().equals(""))
                                    || (prix.getText().equals(""))
                                    || (nb.getText().equals(""))
                                    || (ac.getText().equals(""))
                                    || (desc.getText().equals(""))) {
                                Dialog.show("Alerte", "Veuillez remplir tous les champs ou vérifier les données saisies", "Ok", null);
                            } else {
                                if (!this.newfilePath.equals("") && this.newfilePath != null) {
                                    ww = new Workshop(w.getId());
                                    ww.setNomWorkshop(nom.getText());
                                    ww.setAdresse(ac.getText().toString());

                                    ww.setType(txTyp.getSelectedString());
                                    ww.setDateDebut(datedeb.getDate());
                                    ww.setDateFin(datefin.getDate());
                                    ww.setNbPlace(Integer.parseInt(nb.getText().toString()));
                                    ww.setPrix(Double.parseDouble(prix.getText()));
                                    ww.setDescription(desc.getText());
                                    ww.setImage(this.newfilePath);
                                    ws.modWork(ww);
                                    nom.clear();
                                    ac.clear();
                                    ListeWorks acObj = new ListeWorks();
                                    acObj.getf().showBack();
                                }
                            }
                        });

                        fa.add(cTyp);
                        fa.add(btnOpen1);
                        fa.add(btnConf);
                        fa.show();
                    });
                    if (w.getIdUser() != fu.getId()) {
                        Button part = new Button("Participer");
                        f1.add(part);
                        part.setVisible(true);
//                        System.out.println("idw "+ws.nbPar(w.getId()));
//                        System.out.println("getnbplace "+w.getNbPlace());
//                        System.out.println("nbpar "+ ws.nbPar(p.idWorkshop));
//                        
//                        if (w.getNbPlace() <= ws.nbPar(w.getId())) {
//            part.setVisible(false);
//      }

                        if (pw.SwitchBTn(w.getId(), fu.getId()).equals("{zeineb=true}")) {
                            part.setText("Ne Plus Participer");
                        }

                        System.out.println("part: " + pw.SwitchBTn(p.getIdWorkshop(), p.getIdUser()));
                        part.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                pw.Switch(w.getId(), fu.getId());
                                if (pw.SwitchBTn(w.getId(), fu.getId()).equals("{zeineb=true}")) {
                                    part.setText("Ne Plus Participer");
                                } else {
                                    part.setText(" Participer");
                                }
                            }
                        });

                    }

                    f1.add(modif);

                    f1.add(img);

                    // f1.addComponent(cD);
                    //f1.addComponent(cdes);
                    f1.addComponent(cadre);
                    f1.add(createPieChartForm(w.getId()));

                    f1.show();
                    f1.getToolbar().addCommandToLeftBar("Retour à la liste", theme.getImage("back-command.png"), v -> {

                        f.showBack();
                    });

                }
            });
// f.add(cn2);
            f.addComponent(c2);
         f.add(btSup);

           //f.add(img1);

        }

        f.show();

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
            Log.e(err);
        }
        return null;
    }

    public boolean isNumber(String s) {
        RE ra = new RE("(20|21|22|70|71|50|51)[0-9][0-9][0-9][0-9][0-9][0-9]$");
        boolean matcher = ra.match(s);
        return matcher;
    }

    /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add("Project " + ++k, value);
        }
        return series;
    }

    public Form createPieChartForm(int id) {
        w.setId(id);
        System.out.println("www.getNbPlace()" + www.getNbPlace());
        System.out.println("ws.nbPar(www.getId())" + ws.nbPar(w.getId()));

        double[] values = new double[]{nbp, nbPart};
        // double[] values = new double[]{12, 14, 11, 10, 19};
String[] titles = new String[]{"Nbre de place", "Nbre de participants"};
        int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setGradientEnabled(true);
        r.setGradientStart(0, ColorUtil.BLUE);
        r.setGradientStop(0, ColorUtil.GREEN);
         //renderer.setChartTitleTextFont(largeFont);
        r.setHighlighted(true);

        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Nbre participants % nbre de places", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

        // Create a form and show it.
        Form f = new Form("Nbre participants % nbre de places");
        f.setLayout(new BorderLayout());
        f.addComponent(BorderLayout.CENTER, c);
        return f;
    }

    public Form getf() {
        return f;
    }

    public void setf(Form f) {
        this.f = f;
    }

}
