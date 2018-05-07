/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.util;

import com.codename1.ui.Form;
import com.codename1.ui.Label;

/**
 *
 * @author rkader
 */
public class Labelherit extends Label{
    private Form f;
    
    public Labelherit(){
        super();
        this.addPointerPressedListener((evt) -> {
            Form fr = new Form();
            fr = this.f;
            fr.show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}
