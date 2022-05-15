package com.codename1.Services;

import com.codename1.Statics;
import com.codename1.entities.*;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codename1.uikit.pheonixui.BaseForm.*;

public class ServiceCart {
    public ArrayList<Nft> nftt;

    public ArrayList<Cart> cartt;

    public static ServiceCart instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private ServiceCart() {
        req = new ConnectionRequest();
    }

    public static ServiceCart getInstance() {
        if (instance == null) {
            instance = new ServiceCart();
        }
        return instance;
    }

    public boolean addNftToCart(Nft n) {
        String url = Statics.BASE_URL + "/ajouterNftToCartTest/"+Preferences.get("id", "1")+"?nftProd="+n.getId();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("nftProd", n.getId()+"");
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

    public ArrayList<Nft> parseNftCart(String jsonText){
        try {
            nftt=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> nftListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)nftListJson.get("root");

            for(Map<String,Object> obj : list) {
                Nft nft = new Nft();

                float id = Float.parseFloat(obj.get("id").toString());
                nft.setId((int) id);

                nft.setPrice((Float.parseFloat(obj.get("price").toString())));

                nft.setTitle(obj.get("title").toString());

                nft.setDescription(obj.get("description").toString());

                nft.setCreationDate(obj.get("creationDate").toString());

                nftt.add(nft);

            }
        } catch (IOException ex) {
        }
        return nftt;
    }

    public ArrayList<Nft> getAllNftFromCart(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/afficheNftfromCartTest/"+ Preferences.get("id", "1");
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nftt = parseNftCart(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nftt;
    }

    public ArrayList<Cart> parseCart(String jsonText){
        try {
            cartt=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> cartListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));


            Cart cart=new Cart();

            float id = Float.parseFloat(cartListJson.get("id").toString());
            cart.setId((int) id);

            cart.setTotal((Float.parseFloat(cartListJson.get("total").toString())));

            cart.setDateCreation(cartListJson.get("date_creation").toString());

            cartt.add(cart);


        } catch (IOException ex) {
        }
        return cartt;
    }

    public ArrayList<Cart> getCartClientConnecte(){
        req = new ConnectionRequest();
        String url = Statics.BASE_URL+"/oneCart/"+ Preferences.get("id", "1");
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cartt = parseCart(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cartt;
    }

    public void deleteNftFromCart(Nft nft){
        System.out.println(nft);
        System.out.println("********");
        String url = Statics.BASE_URL + "/deleteNftFromCart/"+nft.getId();
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



}
