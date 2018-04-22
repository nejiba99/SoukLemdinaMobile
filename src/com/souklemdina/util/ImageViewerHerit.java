/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.util;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Form;
import com.codename1.ui.URLImage;

/**
 *
 * @author rkader
 */
public class ImageViewerHerit extends ImageViewer{
    private Form f;

    public ImageViewerHerit(URLImage createToStorage) {
        super(createToStorage);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    @Override
    public void pointerPressed(int x, int y) {
        f.show();
    }
    
    
}
