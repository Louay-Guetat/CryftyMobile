package com.codename1.views;

import com.codename1.Services.Connection;
import com.codename1.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.entities.Category;
import com.codename1.entities.Nft;
import com.codename1.io.JSONParser;
import com.codename1.ui.*;
import com.codename1.ui.Form;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.uikit.pheonixui.InboxForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Explore extends BaseForm {
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    Form current;

    public Explore(Form previous){
        current=this;
        ArrayList<Nft> nfts = Connection.getInstance().getAllNfts();
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        for(Nft nft : nfts){

            try{
                imgv = new ImageViewer(Image.createImage("/load.png"));
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }

            Container gui_Container_1 = new Container(new BorderLayout());
            Container gui_imageContainer1 = new Container(new BorderLayout());
            Container gui_Container_2 = new Container(new BorderLayout());
            Container gui_Container_3 = new Container(new BorderLayout());
            MultiButton gui_Multi_Button_1 = new MultiButton();
            MultiButton gui_LA = new MultiButton();
            Label gui_Text_Area_1 = new Label();
            MultiButton gui_Multi_Button_2 = new MultiButton();
            MultiButton gui_LA2 = new MultiButton();
            Label gui_separator1 = new Label();
            Label gui_Label_1_1_1 = new Label();

            gui_separator1.setShowEvenIfBlank(true);
            gui_Label_1_1_1.setShowEvenIfBlank(true);


            try{
                enc = EncodedImage.create("/load.png");
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            String url = Statics.URL_REP_IMAGES + nft.getImage();
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setImage(imgs);

            gui_imageContainer1.add(BorderLayout.CENTER, imgv);

            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(BorderLayout.CENTER, gui_Multi_Button_1);
            gui_Container_1.addComponent(BorderLayout.EAST, gui_LA);

            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");
            gui_Multi_Button_1.setPropertyValue("line1", "" + nft.getTitle());
            gui_Multi_Button_1.setPropertyValue("line2", "" + nft.getCategory().getName());
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "Label");

            gui_LA.setUIID("Label");
            gui_LA.setName("xxx");
            gui_LA.setPropertyValue("line1", "" + nft.getCreationDate());
            gui_LA.setPropertyValue("line2", "" + nft.getOwner().substring(18,nft.getOwner().length()-1));
            gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA.setPropertyValue("uiid2", "RedLabelRight");

            addComponent(gui_imageContainer1);
            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(BorderLayout.SOUTH, gui_Container_2);

            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(BorderLayout.CENTER, gui_Text_Area_1);

            gui_Text_Area_1.setText("" + nft.getDescription());
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");

            addComponent(gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(BorderLayout.CENTER, gui_Multi_Button_2);
            gui_Container_3.addComponent(BorderLayout.EAST, gui_LA2);

            gui_Multi_Button_2.setUIID("Label");
            gui_Multi_Button_2.setName("Multi_Button_2");
            gui_Multi_Button_2.setPropertyValue("line1", "" + nft.getPrice() +" "+ nft.getCurrency().getCoidCode());
            gui_Multi_Button_2.setPropertyValue("uiid1", "Label");

            gui_LA2.setUIID("Label");
            gui_LA2.setName("likes");
            gui_LA2.setPropertyValue("line1", "" + nft.getLikes());
            gui_LA2.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA2.setPropertyValue("uiid2", "RedLabelRight");

            //actionListeners
            gui_Multi_Button_1.addActionListener((e)-> new afficheNft(current,nft.getId()).show());
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());

        fab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddNft(current).show();
            }
        });

    }
}

