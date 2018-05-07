/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.gui;

import com.codename1.components.Accordion;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.souklemdina.entities.LigneDeCommandeProduit;
import com.souklemdina.services.LigneDeCommandeServices;
import java.util.ArrayList;

/**
 *
 * @author hatem
 */
public class AfficherLigneCommandes {

    LigneDeCommandeServices lcs = new LigneDeCommandeServices();
    private Form f;
    Button btnModif;
    public static Integer X =0;
    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }

    public AfficherLigneCommandes(Integer id) {
        f = new Form("Lignes de commande", new BorderLayout());
        Accordion accr = new Accordion();
        ArrayList<LigneDeCommandeProduit> lstlcp = lcs.getLignesCommandeProduit(id);
        System.out.println(id);
        System.out.println(lstlcp);
        for (LigneDeCommandeProduit lcp : lstlcp) {
                    btnModif = new Button("Modifier");

            Label l1 = new Label(lcp.getLibelle() + " " + lcp.getDescription());
            accr.addContent(l1, new SpanLabel(lcp.getAdresse() + " " + lcp.getAdresse2()));
            System.out.println("add" + lcp.getAdresse());
btnModif.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            X = 1;
                       AjoutCommandeForm acf = new AjoutCommandeForm();
                       
                        }
                    });
        }

        f.add(BorderLayout.CENTER, accr);
        f.show();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
