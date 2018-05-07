/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import static com.codename1.io.rest.Rest.options;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.util.regex.RE;
import com.souklemdina.entities.FosUser;
import com.souklemdina.entities.InfoPersonnel;
import com.souklemdina.entities.Panier;
import com.souklemdina.services.LigneDeCommandeServices;
import com.souklemdina.services.PanierServices;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author hatem
 */
public class AjoutCommandeForm {
    private static final String apiKey = "AIzaSyBWeRU02YUYPdwRuMFyTKIXUbHjq6e35Gw";
    public static String adr;
    public String adr2;
    public String vil;
    public Integer code;
    public String num;
    public static InfoPersonnel ip;
    final DefaultListModel<String> options = new DefaultListModel<>();
    
    private Form f;
    private boolean testTel;
    private boolean testcode;

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    
    public boolean isNumber(String s) {
        RE r = new RE("(20|21|22|70|71|50|51)[0-9][0-9][0-9][0-9][0-9][0-9]$");
        boolean matcher = r.match(s);
     return matcher;
    } 
    public AjoutCommandeForm()
    {
                f = new Form("Informations personnels", new BoxLayout(BoxLayout.Y_AXIS));
                AutoCompleteTextField adresse = new AutoCompleteTextField(options){
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
 adresse.setMinimumElementsShownInPopup(5);
    
 
 
                adresse.setHint("Adresse");
                TextField adresse2 = new TextField();
                adresse2.setHint("Adresse2");
                TextField ville = new TextField();
                ville.setHint("Ville");
                TextField codepostal = new TextField("","Code Postal",5,TextField.NUMERIC);
                codepostal.setHint("Code Postal");
                TextField numtel = new TextField();
                numtel.setHint("Numero telephone");
                Button btnValider = new Button("Valider");
                f.add(adresse);
                f.add(adresse2);
                f.add(ville);
                f.add(codepostal);
                f.add(numtel);
                f.add(btnValider);
                
                codepostal.addDataChangedListener((e,k)->{
                            RE r = new RE("([0-9]+(\\.[0-9]+)?)+");

           if (!r.match(codepostal.getText()))
           {
           Dialog.show("Alerte", "Le champ CODE POSTAL doit etre de type nombre", "Ok",null);
           testcode = false ;
           }else{testcode= true;}
               });
                
                numtel.addDataChangedListener((e,k)->{
                            RE r = new RE("([0-9]+(\\.[0-9]+)?)+");

           if (!r.match(numtel.getText()) )
           {
           Dialog.show("Alerte", "Le champ Téléphone doit etre de type nombre et comporte 8 chiffres", "Ok",null);
           testTel = false ;
           }else{testTel= true;}
               });
                if (AfficherLigneCommandes.X==1)
                {
                   /* adresse.setText(AffichageCommande.adrm);
                    adresse2.setText(AffichageCommande.adr2m);
                    ville.setText(AffichageCommande.vilm);
                    codepostal.setText(AffichageCommande.codem);
                    numtel.setText(AffichageCommande.numm);
                    */
                    btnValider.setText("Modifier");
                    Button btnAnnuler = new Button("Annuler");
                    f.add(btnAnnuler);
                    btnAnnuler.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                              AffichageCommande ac = new AffichageCommande();

                        }
                    });
              btnValider.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                       adr=adresse.getText();
                   adr2=adresse2.getText();
                   vil=ville.getText();
                   code=Integer.parseInt(codepostal.getText());
                   num=numtel.getText();
                                               ip = new InfoPersonnel(adr,adr2,vil,code,num);
                                               System.out.println("ip "+ ip);
                                               AjouterLigneDeCommande acl = new AjouterLigneDeCommande();

                        }
                    });
                     
                }
                else
                {
                btnValider.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                   adr=adresse.getText();
                   adr2=adresse2.getText();
                   vil=ville.getText();
                   code=Integer.parseInt(codepostal.getText());
                   num=numtel.getText();
                                               ip = new InfoPersonnel(adr,adr2,vil,code,num);

                      Paypal p = new Paypal();
                        p.affPaypal();
                         //      AjouterLigneDeCommande acl = new AjouterLigneDeCommande();

                    }
                });
                }
                        f.show();

       
    }
    /*public InfoPersonnel getInfo()
    {
return ip;
    }*/
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
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
}
