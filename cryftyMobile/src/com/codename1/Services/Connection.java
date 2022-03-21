/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.Services;

import com.codename1.entities.*;
import com.codename1.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author LOUAY
 */
public class Connection {

    public ArrayList<Nft> nfts;
    public ArrayList<NftComment> comments;
    public ArrayList<Category> categories;
    public ArrayList<SubCategory> subCategories;
    public ArrayList<Node> currencies;

    public static Connection instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Connection() {
        req = new ConnectionRequest();
    }

    public static Connection getInstance() {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public boolean addNft(Nft nft) {
        System.out.println(nft);
        System.out.println("********");
        String url = Statics.BASE_URL + "/nft/AjoutNftJson?title="+nft.getTitle()+"&description="+nft.getDescription()
                +"&price="+nft.getPrice()+"&likes="+nft.getLikes()+"&image="+nft.getImage()+"&category="+nft.getCategory()
                +"&subCategory="+nft.getSubCategory()+"&currency="+nft.getCurrency()+"&owner="+nft.getOwner();

        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean updateNft(Nft nft){
        System.out.println(nft);
        System.out.println("********");
        String url = Statics.BASE_URL + "/nft/ModifierNftJson/"+nft.getId()+"?title="+nft.getTitle()+"&description="+nft.getDescription()
                +"&price="+nft.getPrice()+"&likes="+nft.getLikes()+"&image="+nft.getImage()+"&category="+nft.getCategory()
                +"&subCategory="+nft.getSubCategory()+"&currency="+nft.getCurrency();

        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public void deleteNft(Nft nft){
        System.out.println(nft);
        System.out.println("********");
        String url = Statics.BASE_URL + "/nft/deleteNftJson/"+nft.getId();
        req.setUrl(url);
        System.out.println("===>" + url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean addComment(NftComment comment) {
        System.out.println(comment.getComment());
        System.out.println("********");
        String url = Statics.BASE_URL + "/nft/AjoutNftCommentJson?comment="+comment.getComment()+"&nft="+comment.getNft()+
                "&owner="+comment.getUser();
        req.setUrl(url);
        System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }


    public ArrayList<Nft> getAllNfts() {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/nft/AfficheNftJson";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nfts = parseNfts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nfts;
    }

    public ArrayList<Category> getAllCategories() {
        req = new ConnectionRequest();

        String url = Statics.BASE_URL + "/category/AfficheCatJson";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }

    public ArrayList<SubCategory> getAllSubCategories() {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/category/AfficheSubCatJson";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                subCategories = parseSubCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return subCategories;
    }

    public ArrayList<Node> getAllCurrencies() {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/nft/CurrencyJson";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                currencies = parseCurrencies(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return currencies;
    }

    public ArrayList<Nft> getNftByUser(int id){
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/nft/AfficheProfileJson/"+id;
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nfts = parseNfts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nfts;
    }

    public ArrayList<Nft> afficheOneNft(int id){
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/nft/AfficheItemJson/"+id;
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nfts = parseOneNft(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nfts;
    }

    public ArrayList<NftComment> getComments(int id) {
        req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/nft/AfficheCommentsJson/"+id;
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parseComments(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }


    public ArrayList<Nft> parseNfts(String jsonText) {
        try {
            nfts = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Nft nft = new Nft();

                float id = Float.parseFloat(obj.get("id").toString());
                nft.setId((int) id);

                nft.setPrice((Float.parseFloat(obj.get("price").toString())));

                nft.setTitle(obj.get("title").toString());

                nft.setDescription(obj.get("description").toString());

                nft.setCreationDate(obj.get("creationDate").toString());

                nft.setImage(obj.get("image").toString());

                float likes = Float.parseFloat(obj.get("likes").toString());
                nft.setLikes((int) likes);

                if(obj.get("category")!=null)
                    nft.setCategory(obj.get("category").toString());
                else nft.setCategory("");

                if(obj.get("subCategory")!=null)
                    nft.setSubCategory(obj.get("subCategory").toString());
                else nft.setSubCategory("");

                if(obj.get("owner")!=null)
                    nft.setOwner(obj.get("owner").toString());
                else nft.setOwner("");

                if(obj.get("currency")!=null)
                    nft.setCurrency(obj.get("currency").toString());
                else nft.setCurrency("");

                nfts.add(nft);
            }
        } catch (IOException ex) {

        }
        return nfts;
    }

    public ArrayList<NftComment> parseComments(String jsonText) {
        try {
            comments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                NftComment comment = new NftComment();
                float id = Float.parseFloat(obj.get("id").toString());
                comment.setId((int) id);

                comment.setComment(obj.get("comment").toString());

                comment.setPostDate(obj.get("postDate").toString());

                float likes = Float.parseFloat(obj.get("likes").toString());
                comment.setLikes((int)likes);

                float dislikes = Float.parseFloat(obj.get("dislikes").toString());
                comment.setDislikes((int)dislikes);

                comment.setNft("");

                comment.setUser(obj.get("user").toString());

                comments.add(comment);
            }
        } catch (IOException ex) {

        }
        return comments;
    }

    public ArrayList<Nft> parseOneNft(String jsonText) {
        try {
            nfts = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            Nft nft = new Nft();

            float id = Float.parseFloat(tasksListJson.get("id").toString());
            nft.setId((int) id);

            nft.setPrice((Float.parseFloat(tasksListJson.get("price").toString())));

            nft.setTitle(tasksListJson.get("title").toString());

            nft.setDescription(tasksListJson.get("description").toString());

            nft.setCreationDate(tasksListJson.get("creationDate").toString());

            nft.setImage(tasksListJson.get("image").toString());

            float likes = Float.parseFloat(tasksListJson.get("likes").toString());
            nft.setLikes((int) likes);

            if(tasksListJson.get("category")!=null)
                nft.setCategory(tasksListJson.get("category").toString());
            else nft.setCategory("");

            if(tasksListJson.get("subCategory")!=null)
                nft.setSubCategory(tasksListJson.get("subCategory").toString());
            else nft.setSubCategory("");

            if(tasksListJson.get("owner")!=null)
                nft.setOwner(tasksListJson.get("owner").toString());
            else nft.setOwner("");

            if(tasksListJson.get("currency")!=null)
                nft.setCurrency(tasksListJson.get("currency").toString());
            else nft.setCurrency("");

            nfts.add(nft);

        } catch (IOException ex) {

        }
        return nfts;
    }

    public ArrayList<Category> parseCategories(String jsonText) {
        try {
            categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Category category = new Category();
                float id = Float.parseFloat(obj.get("id").toString());
                category.setId((int) id);

                category.setName(obj.get("name").toString());

                category.setCreationDate(obj.get("creationDate").toString());

                float nbrNft = Float.parseFloat(obj.get("nbrNft").toString());
                category.setNbrNft((int)nbrNft);

                float nbrSubCategories = Float.parseFloat(obj.get("nbrSubCategory").toString());
                category.setNbrSubCategory((int)nbrSubCategories);

                categories.add(category);
            }
        } catch (IOException ex) {

        }
        return categories;
    }

    public ArrayList<SubCategory> parseSubCategories(String jsonText) {
        try {
            subCategories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                SubCategory subCategory = new SubCategory();
                float id = Float.parseFloat(obj.get("id").toString());
                subCategory.setId((int) id);

                subCategory.setName(obj.get("name").toString());

                subCategory.setCreationDate(obj.get("creationDate").toString());

                float nbrNft = Float.parseFloat(obj.get("nbrNft").toString());
                subCategory.setNbrNft((int)nbrNft);

                subCategory.setCategory(obj.get("category").toString());

                subCategories.add(subCategory);
            }
        } catch (IOException ex) {

        }
        return subCategories;
    }

    public ArrayList<Node> parseCurrencies(String jsonText) {
        try {
            currencies = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Node currency = new Node();
                float id = Float.parseFloat(obj.get("id").toString());
                currency.setId((int) id);

                currency.setNodeLabel(obj.get("nodeLabel").toString());

                currency.setCoidCode(obj.get("coinCode").toString());

                float nbrNft = Float.parseFloat(obj.get("nodeReward").toString());
                currency.setNodeReward(nbrNft);

                currencies.add(currency);
            }
        } catch (IOException ex) {

        }
        return currencies;
    }

}
