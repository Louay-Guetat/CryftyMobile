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
package com.codename1.uikit.pheonixui;

import com.codename1.Services.servicesblogs;
import com.codename1.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entites.BlogArticle;

import java.io.IOException;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class TrendingForm extends BaseForm {
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    Form current;
    public TrendingForm(Form current) {
        this(Resources.getGlobalResources());
    }

    public TrendingForm(Resources resourceObjectInstance) {
        current=this;

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});
        Toolbar.setGlobalToolbar(true);
        Style s = UIManager.getInstance().getComponentStyle("Title");

        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Button gui_Button_12 = new Button();
        gui_Button_12.setText("search");
        gui_Button_12.setUIID("Label");
        gui_Button_12.setName("Button_12");
        FontImage.setMaterialIcon(gui_Button_12, FontImage.MATERIAL_CIRCLE);
        TextField searchField = new TextField("", "Toolbar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        hi.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        ArrayList<BlogArticle> list1;
        list1 = servicesblogs.getInstance().listarticle();
        //hi.add(gui_Button_12);
        searchField.addDataChangeListener((i1, i2) -> { // <2>
            String t = searchField.getText();

            if(t.length() < 1) {
                for(Component cmp : hi.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
            } else {
                t = t.toLowerCase();
                for(Component cmp : hi.getContentPane()) {
                    String val = null;
                    //hi.add(gui_Button_12);
                    if(cmp instanceof Label) {
                        val = ((Label)cmp).getText();
                    } else {
                        if(cmp instanceof TextArea) {
                            val = ((TextArea)cmp).getText();
                        } else {
                            val = (String)cmp.getPropertyValue("text");
                        }
                    }
                    boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                    cmp.setHidden(!show); // <3>
                    cmp.setVisible(show);
                    //hi.add(gui_Button_12);
                }
            }
            hi.getContentPane().animateLayout(250);
            //  hi.add(gui_Button_12);
        });
        hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
            searchField.startEditingAsync(); // <4>
            //    hi.add(gui_Button_12);
        });

        for(BlogArticle rec : list1){
            Label b = new Label(rec.getTitle());
            hi.add(b);
            b.addPointerPressedListener(e -> {

                if (rec.getTitle() == b.getText())
                {
                    new  ArticleForm(resourceObjectInstance,rec).show();
                }
            });
        }
//hi.add(BorderLayout.EAST,gui_Button_12);




        ArrayList<BlogArticle> list2;
        list2 = servicesblogs.getInstance().listarticle();

//        hi.show();


        Button gui_Button_0 = new Button();
        gui_Button_0.setText("search");
        gui_Button_0.setUIID("Label");
        gui_Button_0.setName("Button_0");
        FontImage.setMaterialIcon(gui_Button_0, FontImage.MATERIAL_SEARCH);
        addComponent(gui_Button_0);
        gui_Button_0.addActionListener(e -> { hi.show();});
        ArrayList<BlogArticle> list;
        list = servicesblogs.getInstance().listarticle();
        for (BlogArticle rec : list) {
            Container gui_Container_1 = new Container(new BorderLayout());
            MultiButton gui_Multi_Button_1 = new MultiButton();
            MultiButton gui_LA = new MultiButton();
            Container gui_imageContainer1 = new Container(new BorderLayout());
            Container gui_Container_2 = new Container(new BorderLayout());
            TextArea gui_Text_Area_1 = new TextArea();
            Button gui_Button_1 = new Button();
            Button gui_Button_5 = new Button();
            Button gui_Button_6 = new Button();
            Label gui_separator1 = new Label();
            MultiButton gui_newYork = new MultiButton();
            Container gui_imageContainer2 = new Container(new BorderLayout());
            Container gui_Container_3 = new Container(new BorderLayout());
            TextArea gui_Text_Area_2 = new TextArea();
            Button gui_Button_2 = new Button();
            Label gui_Label_1_1_1 = new Label();

            gui_separator1.setShowEvenIfBlank(true);
            gui_Label_1_1_1.setShowEvenIfBlank(true);
            try{
                imgv = new ImageViewer(Image.createImage("/load.png"));
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            try{
                enc = EncodedImage.create("/load.png");
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            String url = Statics.URL_REP_IMAGES + rec.getImage();
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setImage(imgs);

            gui_imageContainer1.add(BorderLayout.CENTER, imgv);

            gui_Button_6.setText("");
            gui_Button_6.setUIID("Label");
            gui_Button_6.setName("Button_6");
            FontImage.setMaterialIcon(gui_Button_6, FontImage.MATERIAL_DIRECTIONS);
            gui_Text_Area_2.setRows(2);
            gui_Text_Area_2.setColumns(100);
            gui_Text_Area_2.setGrowByContent(false);
            gui_Text_Area_2.setEditable(false);
            gui_Text_Area_1.setRows(2);
            gui_Text_Area_1.setColumns(100);
            gui_Text_Area_1.setGrowByContent(false);
            gui_Text_Area_1.setEditable(false);
            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(BorderLayout.CENTER, gui_Multi_Button_1);
            gui_Container_1.addComponent(BorderLayout.EAST, gui_LA);
            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");
            gui_Multi_Button_1.setPropertyValue("line1", "" + rec.getTitle());
            gui_Multi_Button_1.setPropertyValue("line2", "" + rec.getCategory());
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
            gui_LA.setUIID("Label");
            gui_LA.setName("xxx");
            gui_LA.setPropertyValue("line1", "" + rec.getDate().substring(0,10));
            gui_LA.setPropertyValue("line2", "" + rec.getAuthor());
            gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA.setPropertyValue("uiid2", "RedLabelRight");
            addComponent(gui_imageContainer1);
            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(BorderLayout.SOUTH, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(BorderLayout.CENTER, gui_Text_Area_1);
            //   gui_Container_2.addComponent(BorderLayout.EAST, gui_Button_1);
            // gui_Container_2.addComponent(BorderLayout.NORTH, gui_Button_5);
            gui_Container_2.addComponent(BorderLayout.WEST, gui_Button_6);
            gui_Text_Area_1.setText("" + rec.getContents());
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");
            gui_Button_1.setText("");
            gui_Button_1.setUIID("Label");
            gui_Button_1.setName("Button_1");
            FontImage.setMaterialIcon(gui_Button_1, FontImage.MATERIAL_DELETE);
            gui_Button_5.setText("");
            gui_Button_5.setUIID("Label");
            gui_Button_5.setName("Button_5");
            FontImage.setMaterialIcon(gui_Button_5, FontImage.MATERIAL_ADD_MODERATOR);

            gui_Button_5.addPointerPressedListener(l -> {
                // System.out.println("hello update");
                new  UpdateBAForm(resourceObjectInstance,rec).show();


            } );
            gui_Button_6.addPointerPressedListener(l -> {
                // System.out.println("hello update");
                new  ArticleForm(resourceObjectInstance,rec).show();


            } );
            gui_Button_1.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("Delete");
                if (dig.show("Delete","you want to delete this article","Cancel","Delete")){
                    dig.dispose();
                }
                else {
                    dig.dispose();

                    if (servicesblogs.getInstance().deleteArticle(rec.getId())) {
                        new TrendingForm(current).show();


                    }
                }
                //ServiceRestaurant.getInstance().affichageReclamations(rec.getId());
                //ArrayList<Reclamation>  list = ServiceRestaurant.getInstance().affichageReclamations(2);
                //[mb3d commentairet] new ListRForm(rec.getId(), resourceObjectInstance).show();

            });

        }
   /*     FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> { new AddBAForm(current).show();

        });*/
    }

    ////-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    protected com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    protected com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    protected com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    protected com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    protected com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    protected com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    protected com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    protected com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    protected com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    protected com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
    protected com.codename1.ui.Toolbar search =new Toolbar();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Trending");
        setName("TrendingForm");
        gui_Container_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_1.setName("Container_1");
        gui_imageContainer1.setInlineStylesTheme(resourceObjectInstance);
        gui_imageContainer1.setName("imageContainer1");
        gui_separator1.setUIID("Separator");
        gui_separator1.setInlineStylesTheme(resourceObjectInstance);
        gui_separator1.setName("separator1");
        gui_null_1_1.setInlineStylesTheme(resourceObjectInstance);
        gui_null_1_1.setName("null_1_1");
        gui_imageContainer2.setInlineStylesTheme(resourceObjectInstance);
        gui_imageContainer2.setName("imageContainer2");
        gui_Label_1_1_1.setUIID("Separator");
        gui_Label_1_1_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1_1_1.setName("Label_1_1_1");
        addComponent(gui_Container_1);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Multi_Button_1.setName("Multi_Button_1");
        gui_Multi_Button_1.setIcon(resourceObjectInstance.getImage("contact-c.png"));
        gui_Multi_Button_1.setPropertyValue("line1", "Ami Koehler");
        gui_Multi_Button_1.setPropertyValue("line2", "@dropperidiot");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setInlineStylesTheme(resourceObjectInstance);
        gui_LA.setName("LA");
        gui_LA.setPropertyValue("line1", "3 minutes ago");
        gui_LA.setPropertyValue("line2", "in Los Angeles");
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        addComponent(gui_imageContainer1);
        gui_Container_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_2.setName("Container_2");
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Text_Area_1.setText("The park is a favorite among skaters in California and it definitely deserves it. The park is complete with plenty of smooth banks to gain a ton of speed in the flow bowl.");
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Area_1.setName("Text_Area_1");
        gui_Button_1.setText("");
        gui_Button_1.setUIID("Label");
        gui_Button_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_1.setName("Button_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1,"\ue5c8".charAt(0));
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        addComponent(gui_separator1);
        addComponent(gui_null_1_1);
        gui_null_1_1_1.setUIID("Label");
        gui_null_1_1_1.setInlineStylesTheme(resourceObjectInstance);
        gui_null_1_1_1.setName("null_1_1_1");
        gui_null_1_1_1.setIcon(resourceObjectInstance.getImage("contact-b.png"));
        gui_null_1_1_1.setPropertyValue("line1", "Detra Mcmunn");
        gui_null_1_1_1.setPropertyValue("line2", "@dropperidiot");
        gui_null_1_1_1.setPropertyValue("uiid1", "Label");
        gui_null_1_1_1.setPropertyValue("uiid2", "RedLabel");
        gui_newYork.setUIID("Label");
        gui_newYork.setInlineStylesTheme(resourceObjectInstance);
        gui_newYork.setName("newYork");
        gui_newYork.setPropertyValue("line1", "15 minutes ago");
        gui_newYork.setPropertyValue("line2", "in New York");
        gui_newYork.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_newYork.setPropertyValue("uiid2", "RedLabelRight");
        gui_null_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_null_1_1_1);
        gui_null_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_newYork);
        addComponent(gui_imageContainer2);
        gui_Container_3.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_3.setName("Container_3");
        gui_imageContainer2.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_3);
        gui_Text_Area_2.setText("Griffith Observatory is a facility in Los Angeles, California sitting on the south-facing slope of Mount Hollywood in Los Angeles' Griffith Park.");
        gui_Text_Area_2.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Area_2.setName("Text_Area_2");
        gui_Button_2.setText("");
        gui_Button_2.setUIID("Label");
        gui_Button_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_2.setName("Button_2");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_2,"\ue5c8".charAt(0));
        gui_Container_3.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_2);
        gui_Container_3.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_2);
        addComponent(gui_Label_1_1_1);
    }// </editor-fold>

    //-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentTrending() {
        return true;
    }

}

