/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.Accordion;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.souklemdina.entities.Commande;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.LigneDeCommandeProduit;
import static com.souklemdina.gui.AfficherLigneCommandes.X;
import com.souklemdina.services.CommandeServices;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.util.SessionUser;
import java.util.ArrayList;

/**
 *
 * @author hatem
 */
public class AffichageCommande {
                    FosUser fu = new FosUser(3);

  /*  public static String adrm;
    public static String adr2m;
    public static String vilm;
    public static String codem;
    public static String numm;*/

    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    private Form f;
    private CommandeServices cs = new CommandeServices();
    public static Integer idC;
    Button btnModif;
        private Resources theme = UIManager.initFirstTheme("/theme");


    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public AffichageCommande() {
        f = new Form("Mes Commandes", new BoxLayout(BoxLayout.Y_AXIS));
       // f.setScrollableX(true);

        //SpanLabel lb = new SpanLabel("");
        Font smallBoldSystemFont = Font.createTrueTypeFont("native:ItalicBold", "native:ItalicBold").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        Font smallLightSystemFont = Font.createTrueTypeFont("native:ItalicLight", "native:ItalicLight").derive(Display.getInstance().convertToPixels(3), Font.STYLE_PLAIN);
        TableLayout layout = new TableLayout(1, 3);
        TableLayout.Constraint constraint = layout.createConstraint();
        constraint.setWidthPercentage(200);
        Container c0 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lh1 = new Label("Id    ");
        Label lh2 = new Label("Date Commande");
        Label lh3 = new Label("Date Max");
        c0.add(lh1);
        c0.add(lh2);
//c0.add(lh3);
        f.add(c0);
              Toolbar tb = f.getToolbar();
            //  AfficherLigneWishlist affw = new AfficherLigneWishlist(fu.getId());
            //  AfficherPanier affp = new AfficherPanier().getF();
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_ADD_A_PHOTO, e->{new AfficherPanier().getF().show();});
        tb.addMaterialCommandToSideMenu("Wishlist", FontImage.MATERIAL_AC_UNIT, e->{new AfficherLigneWishlist(fu.getId()).getF().show();});
        ArrayList<Commande> lstC = cs.getCommandes(SessionUser.getUser().getId());

        for (Commande c : lstC) {

            Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Label l1 = new Label(c.getId().toString());
            l1.setAutoSizeMode(true);

            Label l2 = new Label(c.getDateCommande());
            Label l3 = new Label(c.getDateMax());
            l2.setAutoSizeMode(true);
            l3.setAutoSizeMode(true);
            c1.add(createForFont(smallLightSystemFont, l1.getText()));
            c1.add(createForFont(smallLightSystemFont, l2.getText()));
            //      c1.add(createForFont(smallLightSystemFont,l3.getText()));

            Button b1 = new Button("Details");

            c1.add(b1);
//                    c1.add(btnModif);
/*                    btnModif.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
             //System.out.println("hédha id wlh : "+c.getId());
             X = 1;
             AjoutCommandeForm acf = new AjoutCommandeForm();
             }
             });*/
            f.add(c1);
            b1.addActionListener(new ActionListener() {
                ArrayList<LigneDeCommandeProduit> lstlcp = lcs.getLignesCommandeProduit(c.getId());

                @Override
                public void actionPerformed(ActionEvent evt) {
                    /*jdid*/

                    Form f1 = new Form("Lignes de commande", new BorderLayout());
                    Accordion accr = new Accordion();
                    //             System.out.println("id commande"+c.getId());
                    System.out.println("liste lc : " + lstlcp);
                    //        Log.p("aaaasa"+lcs.getLignesCommandeProduit(c.getId()));
                    System.out.println("size : " + lstlcp.size());
                    for (LigneDeCommandeProduit lcp : lstlcp) {
                        btnModif = new Button("Modifier");
                        Label l1 = new Label("Produit : " + lcp.getLibelle());
                        createForFont(smallBoldSystemFont, l1.getText());
                        //System.out.println(l1);
                        accr.addContent(l1, BoxLayout.encloseY(
                                new Label("   Prix Total : " + lcp.getPrixTotal()),
                                new Label("   Quantité achetée : " + lcp.getQuantiteCommander()),
                                new Label("   Adresse : " + lcp.getAdresse()),
                                new Label("   Adresse 2 : " + lcp.getAdresse2()),
                                new Label("   Ville: " + lcp.getVille()),
                                new Label("   Code Postal : " + lcp.getCodePostalforAff()),
                                new Label("   Numero Tel  : " + lcp.getNumTel()),
                                btnModif)
                        );
                        btnModif.setBlockLead(true);
                        System.out.println("addzzzzzz" + lcp.getAdresse());
                        btnModif.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                X = 1;
                                idC = lcp.getIdLigneDeCommande();
                                System.out.println("id ligne :p " + lcp.getIdLigneDeCommande());
                            /*    adrm = lcp.getAdresse();
                                adr2m = lcp.getAdresse2();
                                vilm = lcp.getVille();
                                codem = lcp.getCodePostal();
                                numm = lcp.getNumTel();*/
                                AjoutCommandeForm acf = new AjoutCommandeForm();
                                

                            }
                        });

                    }
//                    AffichageCommande ac = new AffichageCommande();
//           ac.getF().show();
                    f1.getToolbar().addCommandToRightBar("", theme.getImage("back-command.png"), (b)->f.showBack());
              Toolbar tb = f1.getToolbar();
            //  AfficherLigneWishlist affw = new AfficherLigneWishlist(fu.getId());
            //  AfficherPanier affp = new AfficherPanier().getF();
        tb.addMaterialCommandToSideMenu("Panier", FontImage.MATERIAL_ADD_A_PHOTO, e->{new AfficherPanier().getF().show();});
        tb.addMaterialCommandToSideMenu("Wishlist", FontImage.MATERIAL_AC_UNIT, e->{new AfficherLigneWishlist(fu.getId()).getF().show();});
                    f1.add(BorderLayout.CENTER, accr);

                    f1.show();
                }
            });

            /*  l1.addPointerPressedListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent evt) {
             //  Form f1 = new Form("",new BoxLayout(BoxLayout.X_AXIS));
             Form f1 = new Form("Lignes de commande", new BorderLayout());
             Accordion accr = new Accordion();
             LigneDeCommandeServices lcs= new LigneDeCommandeServices();
             ArrayList<LigneDeCommandeProduit> lstlcp= new ArrayList<>();
             lstlcp=lcs.getLignesCommandeProduit();
             for(LigneDeCommandeProduit lcp : lstlcp)
             {
             Label l1 = new Label(lcp.getLibelle()+" "+lcp.getDescription());
             accr.addContent(l1,new SpanLabel(lcp.getAdresse()+" "+lcp.getAdresse2()));
             }
             f1.add(BorderLayout.CENTER, accr);        

             f1.show();
             }
             });*/
        }
        //  lb.setText(cs.getList2().toString());
        //  f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
        f.show();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
