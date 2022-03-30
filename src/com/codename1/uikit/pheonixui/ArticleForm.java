package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.services.servicesblogs;
import com.codename1.share.EmailShare;
import com.codename1.share.FacebookShare;
import com.codename1.share.SMSShare;
import com.codename1.share.ShareService;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entites.BlogArticle;
import com.mycomany.utils.Statics;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ArticleForm extends BaseForm {
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    Form current;


    public ArticleForm(Resources resourceObjectInstance, BlogArticle rec) {
        current=this;

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});




        ArrayList<BlogArticle> list;
        list = servicesblogs.getInstance().listarticle();
   //     for (BlogArticle rec : list) {
            Container gui_Container_1 = new Container(new BorderLayout());
            MultiButton gui_Multi_Button_1 = new MultiButton();
            MultiButton gui_LA = new MultiButton();
            Container gui_imageContainer1 = new Container(new BorderLayout());
            Container gui_Container_2 = new Container(new BorderLayout());
            TextArea gui_Text_Area_1 = new TextArea();
          Button gui_Button_9 = new Button();
        Button gui_Button_10 = new Button();

        Label gui_separator1 = new Label();
            //  private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            // private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
            MultiButton gui_newYork = new MultiButton();
            Container gui_imageContainer2 = new Container(new BorderLayout());
            Container gui_Container_3 = new Container(new BorderLayout());
            TextArea gui_Text_Area_2 = new TextArea();

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

            // ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
            //sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            gui_imageContainer1.add(BorderLayout.CENTER, imgv);
            // sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
            // sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            //gui_imageContainer2.add(BorderLayout.CENTER, imgv);

          /*  FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
            gui_LA.setIconPosition(BorderLayout.EAST);

            FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
            gui_newYork.setIconPosition(BorderLayout.EAST);
*/
        gui_Button_9.setText("");
        gui_Button_9.setUIID("Label");
        gui_Button_9.setName("Button_9");
        FontImage.setMaterialIcon(gui_Button_9, FontImage.MATERIAL_ASSISTANT_NAVIGATION);
        gui_Button_10.setText("");
        gui_Button_10.setUIID("Label");
        gui_Button_10.setName("Button_10");
        FontImage.setMaterialIcon(gui_Button_10, FontImage.MATERIAL_PUBLIC);
            gui_Text_Area_2.setRows(2);
            gui_Text_Area_2.setColumns(100);
            gui_Text_Area_2.setGrowByContent(true);
            gui_Text_Area_2.setEditable(false);
            gui_Text_Area_1.setRows(2);
            gui_Text_Area_1.setColumns(100);
          //  gui_Text_Area_1.setGrowByContent(false);
            gui_Text_Area_1.setEditable(false);
            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(BorderLayout.CENTER, gui_Multi_Button_1);
            gui_Container_1.addComponent(BorderLayout.EAST, gui_LA);
        gui_Container_2.addComponent(BorderLayout.EAST, gui_Button_9);
        gui_Container_2.addComponent(BorderLayout.WEST, gui_Button_10);
            gui_Multi_Button_1.setUIID("Label");
            gui_Multi_Button_1.setName("Multi_Button_1");
            gui_Multi_Button_1.setPropertyValue("line1", "" + rec.getTitle());
            gui_Multi_Button_1.setPropertyValue("line2", "" + rec.getCategory());
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
            gui_LA.setUIID("Label");
            gui_LA.setName("xxx");
            gui_LA.setPropertyValue("line1", "" + rec.getDate());
            gui_LA.setPropertyValue("line2", "" + rec.getAuthor());
            gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA.setPropertyValue("uiid2", "RedLabelRight");
            addComponent(gui_imageContainer1);
            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(BorderLayout.SOUTH, gui_Container_2);
            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(BorderLayout.CENTER, gui_Text_Area_1);

            gui_Text_Area_1.setText("" + rec.getContents());
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");


      // gui_Button_9.addActionListener();
        Form hi = new Form("ShareButton");
        ShareButton sb = new ShareButton();
        sb.setText("Share Screenshot");
        hi.add(sb);


        Image screenshot = Image.createImage(hi.getWidth(), hi.getHeight());
        hi.revalidate();
        hi.setVisible(true);
        hi.paintComponent(screenshot.getGraphics(), true);

        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile);) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch(IOException err) {
            Log.e(err);
        }
        sb.setImageToShare(imageFile, "image/png");

        gui_Button_9.addActionListener(e -> {  new EmailShare().share(rec.getContents());});
        gui_Button_10.addActionListener(e -> {  new FacebookShare().share(rec.getContents());});
        //gui_Button_9.addActionListener(e -> { hi.show();});






        }




    //-- DON'T EDIT BELOW THIS LINE!!!
// <editor-fold defaultstate="collapsed" desc="Generated Code">
    // </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
    @Override
    protected boolean isCurrentTrending() {
        return true;
    }
}
