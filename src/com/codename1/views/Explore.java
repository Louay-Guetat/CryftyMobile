package com.codename1.views;

import com.codename1.Services.Connection;
import com.codename1.Statics;
import com.codename1.components.*;
import com.codename1.entities.Category;
import com.codename1.entities.Nft;
import com.codename1.io.MultipartRequest;
import com.codename1.ui.*;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.uikit.pheonixui.InboxForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Explore extends BaseForm {
    EncodedImage enc;
    private static boolean situation=false;
    Image imgs;
    ScaleImageButton imgv;
    Form current;

    public Explore(Form previous){
        current=this;
        ArrayList<Nft> nfts = Connection.getInstance().getAllNfts();


        CheckBox cbCategory [] = new CheckBox[allCategories.size()];
        Container categoryContainer = new Container();
        for(int i=0; i<cbCategory.length;i++){
            cbCategory[i] = new CheckBox(allCategories.get(i).getName());
            categoryContainer.addComponent(cbCategory[i]);
        }

        CheckBox cbSubCategories [] = new CheckBox[allSubCategories.size()];
        Container subCategoryContainer = new Container();
        for(int i=0; i<cbSubCategories.length;i++){
            cbSubCategories[i] = new CheckBox(allSubCategories.get(i).getName());
            subCategoryContainer.addComponent(cbSubCategories[i]);
        }

        CheckBox cbCurrencies [] = new CheckBox[allCurrencies.size()];
        Container currenciesContainer = new Container();
        for(int i=0; i<cbCurrencies.length;i++){
            cbCurrencies[i] = new CheckBox(allCurrencies.get(i).getCoidCode());
            currenciesContainer.addComponent(cbCurrencies[i]);
        }


        TextField tfRecherche = new TextField("","Search by title");
        TextField prixMin = new TextField("","minimum price");
        TextField prixMax = new TextField("","maximum price");
        Container maxMinContainer = new Container();
        maxMinContainer.addAll(prixMin, prixMax);

        RadioButton nonePrice = new RadioButton("None");
        RadioButton prixCroissant = new RadioButton("Tri par prix croissant");
        RadioButton prixDecroissant = new RadioButton("Tri par prix decroissant");
        RadioButton triPrix [] = {nonePrice,prixCroissant,prixDecroissant};
        Container priceContainer = new Container();
        priceContainer.addAll(triPrix[0],triPrix[1],triPrix[2]);

        RadioButton noneLikes = new RadioButton("None");
        RadioButton likesCroissant = new RadioButton("Tri par pertinence croissante");
        RadioButton likesDecroissant = new RadioButton("Tri par pertinence decroissante");
        RadioButton triLikes [] = {noneLikes,likesCroissant,likesDecroissant};
        Container LikesContainer = new Container();
        LikesContainer.addAll(triLikes[0],triLikes[1],triLikes[2]);

        Button btnFilter = new Button("Search");
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ArrayList<String> cat = new ArrayList<>();
                for(int i=0; i<cbCategory.length; i++){
                    if(cbCategory[i].isSelected()){
                        cat.add(cbCategory[i].getText());
                    }
                }
                String [] tabCats = new String[cat.size()];
                for(int i=0; i<cat.size();i++){
                    tabCats[i] = cat.get(i);
                }

                ArrayList<String> subCat = new ArrayList<>();
                for(int i=0; i<cbSubCategories.length; i++){
                    if(cbSubCategories[i].isSelected()){
                        subCat.add(cbSubCategories[i].getText());
                    }
                }
                String [] tabSubCats = new String[subCat.size()];
                for(int i=0; i<subCat.size();i++){
                    tabSubCats[i] = subCat.get(i);
                }

                ArrayList<String> currencies = new ArrayList<>();
                for(int i=0; i<cbCurrencies.length; i++){
                    if(cbCurrencies[i].isSelected()){
                        currencies.add(cbCurrencies[i].getText());
                    }
                }
                String [] tabCurrencies = new String[currencies.size()];
                for(int i=0; i<currencies.size();i++){
                    tabCurrencies[i] = currencies.get(i);
                }

                new Explore(new InboxForm(),tfRecherche.getText(),tabCats,tabSubCats,tabCurrencies
                            ,prixMin.getText(),prixMax.getText(),"","").show();
            }
        });

        Container filterContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        filterContainer.addAll(tfRecherche,categoryContainer,subCategoryContainer,currenciesContainer,
             maxMinContainer,priceContainer,LikesContainer,btnFilter);


        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        for(Nft nft : nfts){
            System.out.println("NFT");
            Label separator = new Label(" ");
            separator.setUIID("separator");
            separator.setShowEvenIfBlank(true);

            try{
                imgv = new ScaleImageButton(Image.createImage("/load.png"));
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }

            Container nftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container gui_Container_1 = new Container(new BorderLayout());
            Container gui_imageContainer1 = new Container(new BorderLayout());
            Container gui_Container_2 = new Container(new BorderLayout());
            Container gui_Container_3 = new Container(new BorderLayout());
            MultiButton gui_Multi_Button_1 = new MultiButton();
            MultiButton gui_LA = new MultiButton();
            Label gui_Text_Area_1 = new Label();
            MultiButton gui_Multi_Button_2 = new MultiButton();
            MultiButton gui_LA2 = new MultiButton();

            try{
                enc = EncodedImage.create("/load.png");
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            String url = Statics.URL_REP_IMAGES + nft.getImage();
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setIcon(imgs);

            gui_imageContainer1.add(BorderLayout.CENTER, imgv);

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

            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(BorderLayout.SOUTH, gui_Container_2);

            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(BorderLayout.CENTER, gui_Text_Area_1);

            gui_Text_Area_1.setText("" + nft.getDescription());
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");

            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(BorderLayout.CENTER, gui_Multi_Button_2);
            gui_Container_3.addComponent(BorderLayout.EAST, gui_LA2);

            gui_Multi_Button_2.setUIID("Label");
            gui_Multi_Button_2.setName("Multi_Button_2");
            gui_Multi_Button_2.setPropertyValue("line1", "" + nft.getPrice() +" "+ nft.getCurrency().getCoidCode());
            gui_Multi_Button_2.setPropertyValue("uiid1", "Label");


            gui_LA2.setName("likes");
            gui_LA2.setUIID("NewsTopLine");
            Style likeStyle= new Style(gui_LA2.getUnselectedStyle());
            FontImage like = FontImage.createMaterial(FontImage.MATERIAL_SEND,likeStyle);
            gui_LA2.setIcon(like);
            gui_LA2.setPropertyValue("line1", nft.getLikes()+"");
            gui_LA2.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA2.setPropertyValue("uiid2", "RedLabelRight");

            nftContainer.addAll(gui_Container_1,gui_imageContainer1,gui_Container_3,separator);
            addComponent(nftContainer);

            //actionListeners
            gui_Multi_Button_1.addActionListener((e)-> {
                MultipartRequest cr = new MultipartRequest();
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                new afficheNft(current, nft.getId()).showBack();
                cr.setDisposeOnCompletion(dlg);
            });

            imgv.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    MultipartRequest cr = new MultipartRequest();
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    new afficheNft(current, nft.getId()).showBack();
                    cr.setDisposeOnCompletion(dlg);
                }
            });

            gui_LA2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (Connection.getInstance().like(nft)){
                        if(situation == false){
                            gui_LA2.setPropertyValue("line1", nft.getLikes()+1+"");
                            situation=true;
                        }
                        else{
                            gui_LA2.setPropertyValue("line1", nft.getLikes()+"");
                            situation=false;
                        }
                    }
                }
            });


        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Toolbar filters = this.getToolbar();
        filters.addComponentToRightSideMenu(filterContainer);

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

    public Explore(Form previous,String title, String[]categories, String[] subCategories, String[]currencies
            , String prixMin, String prixMax, String prixOrder, String likesOrder){
        current=this;
        ArrayList<Nft> nfts = Connection.getInstance().searchNft(title,categories,subCategories,currencies,
                    prixMin,prixMax,prixOrder,likesOrder);


        CheckBox cbCategory [] = new CheckBox[allCategories.size()];
        Container categoryContainer = new Container();
        for(int i=0; i<cbCategory.length;i++){
            cbCategory[i] = new CheckBox(allCategories.get(i).getName());
            categoryContainer.addComponent(cbCategory[i]);
        }

        CheckBox cbSubCategories [] = new CheckBox[allSubCategories.size()];
        Container subCategoryContainer = new Container();
        for(int i=0; i<cbSubCategories.length;i++){
            cbSubCategories[i] = new CheckBox(allSubCategories.get(i).getName());
            subCategoryContainer.addComponent(cbSubCategories[i]);
        }

        CheckBox cbCurrencies [] = new CheckBox[allCurrencies.size()];
        Container currenciesContainer = new Container();
        for(int i=0; i<cbCurrencies.length;i++){
            cbCurrencies[i] = new CheckBox(allCurrencies.get(i).getCoidCode());
            currenciesContainer.addComponent(cbCurrencies[i]);
        }


        TextField tfRecherche = new TextField("","Search by title");
        TextField tfPrixMin = new TextField("","minimum price");
        TextField tfPrixMax = new TextField("","maximum price");
        Container maxMinContainer = new Container();
        maxMinContainer.addAll(tfPrixMin, tfPrixMax);

        RadioButton nonePrice = new RadioButton("None");
        RadioButton prixCroissant = new RadioButton("Tri par prix croissant");
        RadioButton prixDecroissant = new RadioButton("Tri par prix decroissant");
        RadioButton triPrix [] = {nonePrice,prixCroissant,prixDecroissant};
        Container priceContainer = new Container();
        priceContainer.addAll(triPrix[0],triPrix[1],triPrix[2]);

        RadioButton noneLikes = new RadioButton("None");
        RadioButton likesCroissant = new RadioButton("Tri par pertinence croissante");
        RadioButton likesDecroissant = new RadioButton("Tri par pertinence decroissante");
        RadioButton triLikes [] = {noneLikes,likesCroissant,likesDecroissant};
        Container LikesContainer = new Container();
        LikesContainer.addAll(triLikes[0],triLikes[1],triLikes[2]);

        Button btnFilter = new Button("Search");
        Button clear = new Button("Clear");
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Explore(new InboxForm()).show();
            }
        });

        Container filterContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        filterContainer.addAll(tfRecherche,categoryContainer,subCategoryContainer,currenciesContainer,
                maxMinContainer,priceContainer,LikesContainer,btnFilter,clear);


        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        for(Nft nft : nfts){
            Label separator = new Label(" ");
            separator.setUIID("separator");
            separator.setShowEvenIfBlank(true);

            try{
                imgv = new ScaleImageButton(Image.createImage("/load.png"));
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }

            Container nftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container gui_Container_1 = new Container(new BorderLayout());
            Container gui_imageContainer1 = new Container(new BorderLayout());
            Container gui_Container_2 = new Container(new BorderLayout());
            Container gui_Container_3 = new Container(new BorderLayout());
            MultiButton gui_Multi_Button_1 = new MultiButton();
            MultiButton gui_LA = new MultiButton();
            Label gui_Text_Area_1 = new Label();
            MultiButton gui_Multi_Button_2 = new MultiButton();
            MultiButton gui_LA2 = new MultiButton();

            try{
                enc = EncodedImage.create("/load.png");
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            String url = Statics.URL_REP_IMAGES + nft.getImage();
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setIcon(imgs);

            gui_imageContainer1.add(BorderLayout.CENTER, imgv);

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

            gui_imageContainer1.setName("imageContainer1");
            gui_imageContainer1.addComponent(BorderLayout.SOUTH, gui_Container_2);

            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(BorderLayout.CENTER, gui_Text_Area_1);

            gui_Text_Area_1.setText("" + nft.getDescription());
            gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
            gui_Text_Area_1.setName("Text_Area_1");

            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(BorderLayout.CENTER, gui_Multi_Button_2);
            gui_Container_3.addComponent(BorderLayout.EAST, gui_LA2);

            gui_Multi_Button_2.setUIID("Label");
            gui_Multi_Button_2.setName("Multi_Button_2");
            gui_Multi_Button_2.setPropertyValue("line1", "" + nft.getPrice() +" "+ nft.getCurrency().getCoidCode());
            gui_Multi_Button_2.setPropertyValue("uiid1", "Label");

            gui_LA2.setName("likes");
            gui_LA2.setUIID("NewsTopLine");
            Style likeStyle= new Style(gui_LA2.getUnselectedStyle());
            FontImage like = FontImage.createMaterial(FontImage.MATERIAL_SEND,likeStyle);
            gui_LA2.setIcon(like);
            gui_LA2.setPropertyValue("line1", nft.getLikes()+"");
            gui_LA2.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
            gui_LA2.setPropertyValue("uiid2", "RedLabelRight");

            nftContainer.addAll(gui_Container_1,gui_imageContainer1,gui_Container_3,separator);
            addComponent(nftContainer);

            //actionListeners
            gui_Multi_Button_1.addActionListener((e)-> {
                MultipartRequest cr = new MultipartRequest();
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                new afficheNft(current, nft.getId()).showBack();
                cr.setDisposeOnCompletion(dlg);
            });

            imgv.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    MultipartRequest cr = new MultipartRequest();
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    new afficheNft(current, nft.getId()).showBack();
                    cr.setDisposeOnCompletion(dlg);
                }
            });

            gui_LA2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (Connection.getInstance().like(nft)){
                        if(situation == false){
                            gui_LA2.setPropertyValue("line1", nft.getLikes()+1+"");
                            situation=true;
                        }
                        else{
                            gui_LA2.setPropertyValue("line1", nft.getLikes()+"");
                            situation=false;
                        }
                    }
                }
            });


        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Toolbar filters = this.getToolbar();
        filters.addComponentToRightSideMenu(filterContainer);

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


        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
    }

}

