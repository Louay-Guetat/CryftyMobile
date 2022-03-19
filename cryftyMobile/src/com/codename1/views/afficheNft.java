/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.views;

import com.codename1.Statics;
import com.codename1.entities.Nft;
import com.codename1.entities.NftComment;
import com.codename1.Services.Connection;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LOUAY
 */
public class afficheNft extends Form {
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;

    public afficheNft(Form previous, int id) {
        ArrayList<Nft> nfts = Connection.getInstance().afficheOneNft(id);
        setTitle("List tasks");
        for (Nft nft : nfts) {
            Container c = new Container();
            c.setLayout(BoxLayout.y());

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
            String url = Statics.URL_REP_IMAGES + nft.getImage();
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setImage(imgs);

            Label lblTitle = new Label(nft.getTitle());
            Label lblDescription = new Label(nft.getDescription());
            Label lblPrice = new Label(nft.getPrice() + "");
            Label lblCurrency = new Label(nft.getCurrency());
            Label lblDate = new Label(nft.getCreationDate());
            Label lblLikes = new Label(nft.getLikes() + "");
            Label lblCategory = new Label(nft.getCategory());
            Label lblSubCategory = new Label(nft.getSubCategory());
            Label lblOwner = new Label(nft.getOwner());
            Label separ = new Label("______________");
            c.addAll(imgv, lblTitle, lblDescription, lblPrice, lblCurrency, lblDate, lblLikes, lblCategory, lblSubCategory, lblOwner,separ);
            add(c);
        }
        ArrayList<NftComment> comments = Connection.getInstance().getComments(id);
        for (NftComment comment : comments) {
            Container c = new Container();
            c.setLayout(BoxLayout.y());
            Label lblUser = new Label(comment.getUser());
            Label lblComment = new Label(comment.getComment());
            Label lblPostDate = new Label(comment.getPostDate());
            Label lblLikes = new Label(comment.getLikes()+"");
            Label lblDislikes = new Label(comment.getDislikes()+ "");
            Label separ = new Label("______________");
            c.addAll(lblUser,lblComment, lblPostDate,lblLikes,lblDislikes,separ);
            add(c);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
