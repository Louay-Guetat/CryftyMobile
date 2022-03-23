/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.views;

import com.codename1.Statics;
import com.codename1.components.MultiButton;
import com.codename1.entities.Nft;
import com.codename1.entities.NftComment;
import com.codename1.Services.Connection;
import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.uikit.pheonixui.InboxForm;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LOUAY
 */
public class afficheNft extends BaseForm {
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;

    public afficheNft(Form previous, int id) {
        ArrayList<Nft> nfts = Connection.getInstance().afficheOneNft(id);
        Nft nft = nfts.get(0);
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

            setTitle(nft.getTitle());

            try{
                imgv = new ImageViewer(Image.createImage("/load.png"));
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }

            Container gui_Container_1 = new Container(new BorderLayout());
            Container gui_imageContainer1 = new Container(new BorderLayout());
            Container gui_Container_2 = new Container(new BorderLayout());
            Container gui_Container_3 = new Container(new BorderLayout());
            Container buttonsContainer = new Container(new BorderLayout());

            MultiButton gui_Multi_Button_1 = new MultiButton();
            MultiButton gui_LA = new MultiButton();
            Label gui_Text_Area_1 = new Label();
            MultiButton gui_Multi_Button_2 = new MultiButton();
            MultiButton gui_LA2 = new MultiButton();
            Label gui_separator1 = new Label();
            Label gui_Label_1_1_1 = new Label();
            Button btnUpdateNft = new Button("Update");
            Button btnDeleteNft = new Button("Delete");

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

            buttonsContainer.setName("ButtonsContainer");
            buttonsContainer.addComponent(BorderLayout.EAST,btnUpdateNft);
            buttonsContainer.addComponent(BorderLayout.WEST,btnDeleteNft);

            if(nft.getOwner().substring(4,5).equals(client.getId()+"")){
                add(buttonsContainer);
            }

            //ActionListeners
            btnUpdateNft.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new AddNft(previous,nft.getId(),nft.getTitle(),nft.getDescription(),nft.getPrice()
                    ,nft.getCurrency().getCoidCode(),nft.getCategory().getName(),nft.getSubCategory().getName()).show();
                }
            });

            btnDeleteNft.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Connection.getInstance().deleteNft(nft);
                    Dialog.show("Success","Nft Supprimé","ok",null);
                    Form indexForm = new InboxForm();
                    new Explore(indexForm).show();
                }
            });


        //Comment Section
        ArrayList<NftComment> comments = Connection.getInstance().getComments(id);
        Container addCommentContainer = new Container(new BorderLayout());
        TextField tfComment = new TextField();
        Button btnComment = new Button(">");

        for (NftComment comment : comments) {
            Container ownerCommentContainer = new Container(new BorderLayout());
            Container commentContainer = new Container(new BorderLayout());
            Container reactionContainer = new Container(new BorderLayout());
            Container buttonsCmtContainer = new Container(new BorderLayout());


            Label lblUser = new Label(comment.getUser().substring(18,comment.getUser().length()-1));
            Label lblPostDate = new Label(comment.getPostDate());
            Label lblComment = new Label(comment.getComment());
            Label lblLikes = new Label(comment.getLikes()+"");
            Label lblDislikes = new Label(comment.getDislikes()+ "");
            Button deleteComment = new Button("delete");
            Button updateComment = new Button("Update");


            add(ownerCommentContainer);
            ownerCommentContainer.addComponent(BorderLayout.WEST, lblUser);
            ownerCommentContainer.addComponent(BorderLayout.EAST, lblPostDate);

            add(commentContainer);
            commentContainer.add(BorderLayout.CENTER, lblComment);

            add(reactionContainer);
            reactionContainer.addComponent(BorderLayout.EAST, lblLikes);
            reactionContainer.addComponent(BorderLayout.WEST, lblDislikes);




            if(comment.getUser().substring(4,5).equals(client.getId()+"")){
                add(buttonsCmtContainer);
                buttonsCmtContainer.addComponent(BorderLayout.EAST,updateComment);
                buttonsCmtContainer.addComponent(BorderLayout.WEST,deleteComment);
            }

        }

        add(addCommentContainer);
        addCommentContainer.addComponent(BorderLayout.WEST,tfComment);
        addCommentContainer.addComponent(BorderLayout.EAST, btnComment);

        btnComment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(!tfComment.getText().isEmpty()){
                    NftComment nftComment = new NftComment();
                    nftComment.setComment(tfComment.getText());
                    nftComment.setNft(id+"");
                    nftComment.setUser(client.getId()+"");
                    Connection.getInstance().addComment(nftComment);
                    Dialog.show("success", "commentaire ajouté", "ok",null);
                }
                new afficheNft(new Explore(new InboxForm()),id).show();
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
