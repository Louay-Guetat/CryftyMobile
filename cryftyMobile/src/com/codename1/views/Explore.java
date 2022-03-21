package com.codename1.views;

import com.codename1.Services.Connection;
import com.codename1.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.entities.Nft;
import com.codename1.ui.*;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.pheonixui.BaseForm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Explore extends BaseForm {
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    Form current;

    public Explore(Form previous){
        current=this;
        ArrayList<Nft> nfts = Connection.getInstance().getAllNfts();
        for(Nft nft : nfts){
            try{
                imgv = new ImageViewer(Image.createImage("/load.png"));
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }

            Container c = new Container();
            c.setLayout(BoxLayout.y());

            Button btnTitle = new Button(nft.getTitle());
            btnTitle.addActionListener((e)-> new afficheNft(current,nft.getId()).show());

            Label lblDescription = new Label(nft.getDescription());

            Label lblPrice = new Label(nft.getPrice()+"");

            Label lblCurrency = new Label(nft.getCurrency());
            Date creationDate = new Date();
            try{
                creationDate=new SimpleDateFormat("dd/MM/yyyy").parse(nft.getCreationDate());
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
            Label lblDate = new Label(creationDate.toString());

            Label lblLikes = new Label(nft.getLikes()+"");

            Label lblCategory = new Label(nft.getCategory()+"");

            Label lblSubCategory = new Label(nft.getSubCategory());

            Label lblOwner = new Label(nft.getOwner());
            Label separ = new Label("__________________");

            try{
                enc = EncodedImage.create("/load.png");
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            String url = Statics.URL_REP_IMAGES + nft.getImage();
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setImage(imgs);

            c.addAll(imgv,btnTitle,lblDescription,lblPrice,lblCurrency,lblDate,lblLikes,lblCategory,lblSubCategory,lblOwner,separ);
            add(c);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}

