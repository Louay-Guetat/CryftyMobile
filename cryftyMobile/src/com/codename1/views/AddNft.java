package com.codename1.views;

import com.codename1.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.entities.Category;
import com.codename1.entities.Nft;
import com.codename1.Services.Connection;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.entities.SubCategory;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.pheonixui.BaseForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AddNft extends BaseForm {
        String image;
        EncodedImage enc;
        Image imgs;
        ImageViewer imgv;
    public AddNft(Form previous) {
        setLayout(BoxLayout.y());
        setTitle("Add Nft");
        SpanLabel sp = new SpanLabel();
        add(sp);
        Container imageContainer = new Container();
        imageContainer.setLayout(BoxLayout.xCenter());
        Button btnUpload = new Button("Upload");
        try{
            imgv = new ImageViewer(Image.createImage("/load.png"));
        }catch(IOException ex){
            Dialog.show("Error",ex.getMessage(),"ok",null);
        }

        Label lblImage = new Label();
        //lblImage.setIcon(icon);
        imageContainer.add(lblImage);
        add(imgv);
        add(btnUpload);

        Nft nft = new Nft();

        TextField title = new TextField();
        Label lblTitle = new Label("Titre");
        add(lblTitle);
        add(title);

        TextArea description = new TextArea();
        description.setHeight(200);
        Label lblDescription = new Label("Description");
        add(lblDescription);
        add(description);

        TextField price = new TextField();
        Label lblPrice = new Label("Price: ");
        add(lblPrice);
        add(price);

        Label lblCurrency = new Label("Currency");
        ComboBox currency = new ComboBox();
        currency.addItem("Bit");
        add(lblCurrency);
        add(currency);

        ComboBox category = new ComboBox();
        ArrayList<Category> categories = Connection.getInstance().getAllCategories();
        for(Category cat : categories){
            category.addItem(cat.getName());
        }

        Label lblCategory = new Label("Category");
        add(lblCategory);
        add(category);

        ComboBox subCategory = new ComboBox();
        ArrayList<SubCategory> subCategories = Connection.getInstance().getAllSubCategories();
        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                for(SubCategory subCat : subCategories){
                    if(subCat.getCategory().equals("{id="+(category.getSelectedIndex()+1)+".0}")){
                        subCategory.addItem(subCat.getName());
                    }
                }
            }
        });

        Label lblSubCategory = new Label("SubCategory");
        add(lblSubCategory);
        add(subCategory);

        Button submit = new Button("Submit");
        add(submit);

        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                nft.setTitle(title.getText());
                nft.setImage(image);
                nft.setDescription(description.getText());
                nft.setPrice(Float.parseFloat(price.getText()));
                nft.setCurrency((currency.getSelectedIndex()+1)+"");
                nft.setCategory((category.getSelectedIndex()+1)+"");
                nft.setSubCategory((subCategory.getSelectedIndex()+1)+"");
                nft.setLikes(0);
                //nft.setOwner();

                if (Connection.getInstance().addNft(nft)){
                    Dialog.show("success", "nft ajouté", "ok",null);
                }
                else{
                    Dialog.show("error", "Request erroné", "ok",null);
                }
            }
        });

        btnUpload.addActionListener((evt) -> {
            MultipartRequest cr = new MultipartRequest();
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

            cr.setUrl(Statics.URL_UPLOAD);
            cr.setPost(true);
            String mime = "image/*";
            try {
                cr.addData("file", filePath, mime);
            } catch (IOException ex) {
                Dialog.show("Error", ex.getMessage(), "OK", null);
            }
            String uniqueID = UUID.randomUUID().toString();
            String extension = "";

            int i = filePath.lastIndexOf('.');
            if (i > 0) {
                extension = filePath.substring(i+1);
            }
            cr.setFilename("file", uniqueID+"."+extension);//any unique name you want
            System.out.println(uniqueID+"."+extension);
            image = uniqueID+"."+extension;
            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            cr.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(cr);
            Dialog.show("Success", "Image uploaded", "OK", null);
            try{
                enc = EncodedImage.create("/load.png");
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            String url = Statics.URL_REP_IMAGES + image;
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setImage(imgs);
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
