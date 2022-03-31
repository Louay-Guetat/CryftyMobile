package com.codename1.Services;


import com.codename1.entities.Transaction;
import com.codename1.entities.Wallets;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServiceTransaction {

    public ArrayList<Transaction> transaction;
    public ArrayList<Wallets> wallet;
    public static ServiceTransaction instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTransaction() {
        req = new ConnectionRequest();
    }

    public static ServiceTransaction getInstance() {
        if (instance == null) {
            instance = new ServiceTransaction();
        }
        return instance;
    }

    public boolean addTransaction(Transaction t,float total) {
        System.out.println(t);
        System.out.println("******");
        String url = Statics.BASE_URL + "/AddTransactionTest/"+total+"?wallets="+t.getWallet()+"&cartId="+ Preferences.get("id", "1");;
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("wallets", t.getWallet());
        //req.addArgument("cartId",t.getCart2().getId()+"");
        System.out.println(t.getWallet());
        System.out.println(t.getCart());
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

    public ArrayList<Transaction> parseTransaction(String jsonText){
        try {
            transaction=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> transactionListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)transactionListJson.get("root");

            for(Map<String,Object> obj : list){
                Transaction t = new Transaction();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                if (obj.get("cartId")==null)
                {
                    t.setCart("null");
                }
                else
                {
                    t.setCart(obj.get("cartId").toString());
                }

                if (obj.get("wallets")==null)
                {
                    t.setWallet("null");
                }
                else
                {
                    t.setWallet(obj.get("wallets").toString());
                }

                transaction.add(t);
            }


        } catch (IOException ex) {
        }
        return transaction;
    }

    public ArrayList<Transaction> getAllTransaction(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/afficheTransactiontest/"+ Preferences.get("id", "1");;
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                transaction = parseTransaction(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return transaction;

    }


    public ArrayList<Wallets> parseTransactionWallet(String jsonText){
        try {
            wallet=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> transactionWalletListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)transactionWalletListJson.get("root");

            for(Map<String,Object> obj : list){
                System.out.println(obj.get("id").toString());
                Wallets w =new Wallets();
                float id = Float.parseFloat(obj.get("id").toString());
                w.setId((int)id);
                String walletAddress= obj.get("walletAddress").toString();
                if (walletAddress==null)
                {
                    w.setAdressWallet("null");
                }
                else
                {
                    w.setAdressWallet(walletAddress);
                }
                wallet.add(w);
            }
        } catch (IOException ex) {
        }
        return wallet;
    }

    public ArrayList<Wallets> getAllTransactionWallet(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+ "/afficheTransactionWalletTest?clientWallet="+ Preferences.get("id", "1");;
        System.out.println("===>"+url);
        //req.addArgument("clientWallet", 3+"");
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                wallet = parseTransactionWallet(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return wallet;

    }


}
