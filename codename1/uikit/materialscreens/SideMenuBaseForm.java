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

import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.souklemdina.gui.AccueilSocial;
import com.souklemdina.gui.LoginForm;
import java.io.IOException;


/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        Image profilePic = res.getImage("app.png");
        profilePic.scale(150, 150);

        Label profilePicLabel = new Label(profilePic, "SideMenuTitle");
 

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Mon compte", FontImage.MATERIAL_PERSON,  e ->showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Boutique", FontImage.MATERIAL_LOCAL_GROCERY_STORE,  e -> new ProfileForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Réseau Social", FontImage.MATERIAL_PAGES,  e -> {
        AccueilSocial as=new AccueilSocial();
        as.getF().show();
        });
        getToolbar().addMaterialCommandToSideMenu("  Evénements", FontImage.MATERIAL_EXPLORE,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Workshops", FontImage.MATERIAL_SCHOOL,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Panier", FontImage.MATERIAL_ADD_SHOPPING_CART,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Locaux", FontImage.MATERIAL_LOCATION_CITY,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> {

                new LoginForm(res).show();
                
  
           
            
        });
    }
    
    protected abstract void showOtherForm(Resources res);
}
