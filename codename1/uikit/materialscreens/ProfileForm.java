/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.p;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.souklemdina.entities.Produit;
import com.souklemdina.gui.AjoutProduit;
import com.souklemdina.services.ProduitServices;
import com.souklemdina.util.ImageViewerHerit;
import com.souklemdina.util.SessionUser;
import java.util.ArrayList;

/**
 * Represents a user profile in the app, the first form we open after the
 * walkthru
 *
 * @author Shai Almog
 */
public class ProfileForm extends SideMenuBaseForm {

    public ProfileForm(Resources res) {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Label profilePicLabel = new Label(" La boutique");
        FontImage.setMaterialIcon(profilePicLabel, FontImage.MATERIAL_SHOPPING_BASKET);
        profilePicLabel.getAllStyles().setFgColor(0xFFFFFF);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("La boutique", "Title")
                        )
                ).add(BorderLayout.CENTER, profilePicLabel),
                GridLayout.encloseIn(2)
        );
        tb.setTitleComponent(titleCmp);
        
        Button aj = new Button("Ajouter un produit");
        aj.setUIID("LoginButton");
        
        
        aj.addActionListener((evt1) -> {new AjoutProduit(res).show();});
        add(aj);
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        Label l =new Label("Nos Produits", "WelcomeBlue");
        l.setAlignment(CENTER);
        add(l);

        add(new Label(""));
        add(new Label(""));
        
        
                


        ProduitServices ps = new ProduitServices();

        ArrayList<Produit> listProduits = ps.getList2();

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_ADD, "Label", 0);
        
        for (Produit t : listProduits) {

            Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + t.getImage()));

// ImageViewer img = new ImageViewer(res.getImage(t.getImage()));
            Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label libelle = new Label(t.getLibelle());
            libelle.setAlignment(CENTER);
            c1.add(libelle);
            c1.add(img);

            Label prix = new Label(String.valueOf(t.getPrix()) + " DT");
            prix.setAlignment(CENTER);
            c1.add(prix);
            add(c1);
            addButtonBottom(arrowDown, t.getCategorie(), 0xd997f1, true);

            int id = t.getId();

            libelle.addPointerPressedListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Form f1 = new Form(new BoxLayout(BoxLayout.Y_AXIS));

                    Image placeholder = Image.createImage(tb.getWidth() / 3 - 4, tb.getWidth() / 3 - 4, 0xbfc9d2);
                    EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
                    ImageViewer img = new ImageViewer(URLImage.createToStorage(encImage, "file" + t.getImage(), "http://localhost/SoukLemdina/web/uploads/images/" + t.getImage()));
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Label l2 = new Label(t.getLibelle());
                    SpanLabel l3 = new SpanLabel(t.getDescription());
                    l3.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    l3.setTextBlockAlign(CENTER);
                                        SpanLabel l4 = new SpanLabel(String.valueOf(t.getPrix()) + " DT");
                                        l4.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
                    l4.setTextBlockAlign(CENTER);

                    l2.setAlignment(CENTER);
                    
                    f1.add(c3);
                    f1.add(img);
                    f1.add(l2);
                    f1.add(l3);
                    f1.add(l4);

                    f1.show();
                    f1.getToolbar().addCommandToLeftBar("Back", res.getImage("back-command.png"), b -> new ProfileForm(res).show());

                }
            });
        }

        setupSideMenu(res);
    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);

        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private void addButtonBottom2(String text) {
        MultiButton finishLandingPage = new MultiButton(text);

        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseCenter(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }

}
