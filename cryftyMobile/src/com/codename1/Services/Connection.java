/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.Services;

import com.codename1.entities.Nft;
import com.codename1.entities.NftComment;
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

    public boolean addNft(Nft t) {
        System.out.println(t);
        System.out.println("********");
        String url = Statics.BASE_URL + "/nft/AjoutNftJson?title="+t.getTitle()+"&description="+t.getDescription()
                +"&price="+t.getPrice()+"&likes="+t.getLikes()+"&image="+t.getImage();
        req.setPost(false);
        req.setUrl(url);
        req.addArgument("title", t.getTitle());
        req.addArgument("description", t.getDescription() + "");
        req.addArgument("price", t.getPrice()+"");
        req.addArgument("creationDate", t.getCreationDate()+ "");
        req.addArgument("image", t.getImage());
        req.addArgument("likes", t.getLikes() + "");
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
        //String url = Statics.BASE_URL+"/tasks/";
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

    public ArrayList<Nft> getNftByUser(int id){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
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
        //String url = Statics.BASE_URL+"/tasks/";
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
        //String url = Statics.BASE_URL+"/tasks/";
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

                //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
            //List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            Nft nft = new Nft();

            float id = Float.parseFloat(tasksListJson.get("id").toString());
            nft.setId((int) id);

            nft.setPrice((Float.parseFloat(tasksListJson.get("price").toString())));

            nft.setTitle(tasksListJson.get("title").toString());

            nft.setDescription(tasksListJson.get("description").toString());

            //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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

}
