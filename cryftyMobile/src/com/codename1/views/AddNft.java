package com.codename1.views;

import com.codename1.Statics;
import com.codename1.components.*;
import com.codename1.entities.Category;
import com.codename1.entities.Nft;
import com.codename1.Services.Connection;
import com.codename1.capture.Capture;
import com.codename1.entities.Node;
import com.codename1.entities.SubCategory;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.uikit.pheonixui.InboxForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AddNft extends BaseForm {
        String image="";
        EncodedImage enc;
        Image imgs;
        ImageViewer imgv;

        //addForm
    public AddNft(Form previous) {
        setLayout(BoxLayout.y());
        setTitle("Add Nft");

        SpanLabel sp = new SpanLabel();
        Button btnUpload = new Button("Upload");
        Label lblImage = new Label();
        Label lblTitle = new Label("Titre");
        Label lblPrice = new Label("Price: ");
        Label lblCurrency = new Label("Currency");
        Label lblDescription = new Label("Description");
        Label lblCategory = new Label("Category");
        Label lblSubCategory = new Label("SubCategory");
        TextField title = new TextField("","Title",0,TextArea.ANY);
        TextArea description = new TextArea();
        TextField price = new TextField("","Price",0,TextArea.ANY);
        ComboBox currency = new ComboBox();
        ComboBox category = new ComboBox();
        Picker subCategory = new Picker();
        Button submit = new Button("Submit");

        Container imageContainer = new Container(BoxLayout.y());
        Container titleContainer = new Container(BoxLayout.y());
        Container descriptionContainer = new Container(BoxLayout.y());
        Container priceContainer = new Container(BoxLayout.y());
        Container currencyContainer = new Container(BoxLayout.y());
        Container categoryContainer = new Container(BoxLayout.y());
        Container subCategoryContainer = new Container(BoxLayout.y());
        Container buttonContainer = new Container(BoxLayout.y());

        try{
            imgv = new ImageViewer(Image.createImage("/load.png"));
        }catch(IOException ex){
            Dialog.show("Error",ex.getMessage(),"ok",null);
        }

        addComponent(imageContainer);
        imageContainer.addComponent(imgv);
        imageContainer.addComponent(btnUpload);


        addComponent(titleContainer);
        titleContainer.addComponent(lblTitle);
        titleContainer.addComponent(title);


        addComponent(descriptionContainer);
        descriptionContainer.addComponent(lblDescription);
        descriptionContainer.addComponent(description);
        description.setRows(3);

        addComponent(priceContainer);
        priceContainer.addComponent(lblPrice);
        priceContainer.addComponent(price);


        addComponent(currencyContainer);
        currencyContainer.addComponent(lblCurrency);
        currencyContainer.addComponent(currency);

        addComponent(categoryContainer);
        categoryContainer.addComponent(lblCategory);
        categoryContainer.addComponent(category);

        addComponent(subCategoryContainer);
        subCategoryContainer.addComponent(lblSubCategory);
        subCategoryContainer.addComponent(subCategory);

        addComponent(buttonContainer);
        buttonContainer.addComponent(submit);


        for(Node curr : allCurrencies){
            currency.addItem(curr.getCoidCode());
        }

        for(Category cat : allCategories){
            category.addItem(cat.getName());
        }

        ArrayList subs = new ArrayList();
        for(SubCategory subCat : allSubCategories){
            if(subCat.getCategory().equals("{id="+(category.getSelectedIndex()+1)+".0}")){
                subs.add(subCat.getName());
            }
        }
        if(subs.size()!=0){
            subCategory.setEnabled(true);
            String tab[] = new String[subs.size()];
            for(int i=0;i<subs.size();i++){
                tab[i]= (String) subs.get(i);
            }
            subCategory.setStrings(tab);
            subCategory.setSelectedString(tab[0]);
        }
        else{
            subCategory.setText("No subCategories to show");
            subCategory.setEnabled(false);
        }

        //ActionListeners
        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList subs = new ArrayList();
                int i=0;
                for(SubCategory subCat : allSubCategories){
                    if(subCat.getCategory().getName().equals(category.getSelectedItem())){
                        subs.add(subCat.getName());
                    }
                }
                if(subs.size()!=0){
                    subCategory.setEnabled(true);
                    String tab[] = new String[subs.size()];
                    for(i=0;i<subs.size();i++){
                        tab[i]= (String) subs.get(i);
                    }
                    subCategory.setStrings(tab);
                    subCategory.setSelectedString(tab[0]);
                }
                else{
                    subCategory.setText("No subCategories to show");
                    subCategory.setEnabled(false);
                }
            }
        });

        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                if(image.isEmpty()){
                    Dialog.show("Error", "You need to upload an Image", "OK", null);
                }
                else if(title.getText().isEmpty()){
                    Dialog.show("Error", "You need to specify a title", "OK", null);
                }
                else if(price.getText().isEmpty()){
                    Dialog.show("Error", "You need to specify the price", "OK", null);
                }
                else if(subCategory.getSelectedStringIndex()<0){
                    Dialog.show("Error", "You need to specify a subCategory", "OK", null);
                }
                else{
                    Nft nft = new Nft();

                    nft.setTitle(title.getText());
                    nft.setImage(image);
                    nft.setDescription(description.getText());
                    nft.setPrice(Float.parseFloat(price.getText()));
                    for (Category cat : allCategories) {
                        if (cat.getName().equals(category.getSelectedItem()))
                            nft.setCategory(cat);
                    }
                    for (SubCategory subCat : allSubCategories) {
                        if (subCat.getName().equals(subCategory.getSelectedString()))
                            nft.setSubCategory(subCat);
                    }

                    for (Node curr : allCurrencies) {
                        if (curr.getCoidCode().equals(currency.getSelectedItem()))
                            nft.setCurrency(curr);
                    }
                    nft.setLikes(0);
                    nft.setOwner(client.getId()+"");

                    if (Connection.getInstance().addNft(nft)){
                        MultipartRequest cr = new MultipartRequest();
                        InfiniteProgress prog = new InfiniteProgress();
                        Dialog.show("success", "nft ajouté", "ok",null);
                        Dialog dlg = prog.showInifiniteBlocking();
                        new Explore(new InboxForm()).show();
                        cr.setDisposeOnCompletion(dlg);
                    }
                    else{
                        Dialog.show("error", "Request erroné", "ok",null);
                    }
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



        // updateForm
    public AddNft(Form previous,Nft nft) {

        setLayout(BoxLayout.y());
        setTitle("Update "+nft.getTitle());


        Label lblTitle = new Label("Titre");
        Label lblPrice = new Label("Price: ");
        Label lblCurrency = new Label("Currency");
        Label lblDescription = new Label("Description");
        Label lblCategory = new Label("Category");
        Label lblSubCategory = new Label("SubCategory");
        TextField title = new TextField(nft.getTitle(),"Title",0,TextArea.ANY);
        TextArea description = new TextArea(nft.getDescription());
        TextField price = new TextField(nft.getPrice()+"","Price",0,TextArea.ANY);
        ComboBox currency = new ComboBox();
        ComboBox category = new ComboBox();
        Picker subCategory = new Picker();
        Button Update = new Button("Update");

        Container titleContainer = new Container(BoxLayout.y());
        Container descriptionContainer = new Container(BoxLayout.y());
        Container priceContainer = new Container(BoxLayout.y());
        Container currencyContainer = new Container(BoxLayout.y());
        Container categoryContainer = new Container(BoxLayout.y());
        Container subCategoryContainer = new Container(BoxLayout.y());
        Container buttonContainer = new Container(BoxLayout.y());

        addComponent(titleContainer);
        titleContainer.addComponent(lblTitle);
        titleContainer.addComponent(title);


        addComponent(descriptionContainer);
        descriptionContainer.addComponent(lblDescription);
        descriptionContainer.addComponent(description);
        description.setRows(3);

        addComponent(priceContainer);
        priceContainer.addComponent(lblPrice);
        priceContainer.addComponent(price);


        addComponent(currencyContainer);
        currencyContainer.addComponent(lblCurrency);
        currencyContainer.addComponent(currency);

        addComponent(categoryContainer);
        categoryContainer.addComponent(lblCategory);
        categoryContainer.addComponent(category);

        addComponent(subCategoryContainer);
        subCategoryContainer.addComponent(lblSubCategory);
        subCategoryContainer.addComponent(subCategory);

        addComponent(buttonContainer);
        buttonContainer.addComponent(Update);

        for(Node curr : allCurrencies){
            currency.addItem(curr.getCoidCode());
        }
        currency.setSelectedItem(nft.getCurrency().getCoidCode());


        for(Category cat : allCategories){
            category.addItem(cat.getName());
        }
        category.setSelectedItem(nft.getCategory().getName());



        ArrayList subs = new ArrayList();
        for(SubCategory subCat : allSubCategories){
            if(subCat.getCategory().getName().equals(nft.getCategory().getName()))
                subs.add(subCat.getName());
        }
        if(subs.size()!=0){
            subCategory.setEnabled(true);
            String tab[] = new String[subs.size()];
            for(int i=0;i<subs.size();i++){
                tab[i]= (String) subs.get(i);
            }
            subCategory.setStrings(tab);
        }
        else{
            subCategory.setText("No subCategories to show");
            subCategory.setEnabled(false);
        }
        subCategory.setSelectedString(nft.getSubCategory().getName());


        //ActionListeners
        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList subs = new ArrayList();
                int i=0;
                for(SubCategory subCat : allSubCategories){
                    if(subCat.getCategory().equals("{id="+(category.getSelectedIndex()+1)+".0}")){
                        subs.add(subCat.getName());
                    }
                }
                if(subs.size()!=0){
                    subCategory.setEnabled(true);
                    String tab[] = new String[subs.size()];
                    for(i=0;i<subs.size();i++){
                        tab[i]= (String) subs.get(i);
                    }
                    subCategory.setStrings(tab);
                    subCategory.setSelectedString(tab[0]);
                }
                else{
                    subCategory.setText("No subCategories to show");
                    subCategory.setEnabled(false);
                }
            }
        });

        Update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){

                if(title.getText().isEmpty()){
                    Dialog.show("Error", "You need to specify a title", "OK", null);
                }
                else if(price.getText().isEmpty()){
                    Dialog.show("Error", "You need to specify the price", "OK", null);
                }
                else if(subCategory.getSelectedStringIndex()<0){
                    Dialog.show("Error", "You need to specify a subCategory", "OK", null);
                }
                else{
                    nft.setTitle(title.getText());
                    nft.setImage(image);
                    nft.setDescription(description.getText());
                    nft.setPrice(Float.parseFloat(price.getText()));
                    for (Category cat : allCategories) {
                        if (cat.getName().equals(category.getSelectedItem()))
                            nft.setCategory(cat);
                    }
                    for (SubCategory subCat : allSubCategories) {
                        if (subCat.getName().equals(subCategory.getSelectedString()))
                            nft.setSubCategory(subCat);
                    }

                    for (Node curr : allCurrencies) {
                        if (curr.getCoidCode().equals(currency.getSelectedItem()))
                            nft.setCurrency(curr);
                    }
                    nft.setLikes(0);

                    if (Connection.getInstance().updateNft(nft)){
                        MultipartRequest cr = new MultipartRequest();
                        InfiniteProgress prog = new InfiniteProgress();
                        Dialog.show("Success", "NFT Modifié", "ok",null);
                        Dialog dlg = prog.showInifiniteBlocking();
                        new afficheNft(new Explore(new InboxForm()),nft.getId()).showBack();
                        cr.setDisposeOnCompletion(dlg);
                    }
                    else{
                        Dialog.show("error", "Request erroné", "ok",null);
                    }
                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            new afficheNft(new Explore(new InboxForm()),nft.getId()).showBack();
        });
    }

}

