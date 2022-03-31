package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Wallet;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WalletService {
    public ConnectionRequest request;
    public boolean resultOK;
    private ArrayList<Wallet> wallets = new ArrayList<>();
    private static WalletService instance;

    private WalletService() {
        request = new ConnectionRequest();
    }

    public static WalletService getInstance() {
     if (instance == null)
         instance = new WalletService();
     return instance;
    }

    public boolean addWallet(Wallet wallet){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Adding your new wallet ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/wallet/add";
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.addArgument("label",wallet.getWalletLabel());
        request.addArgument("client",wallet.getClient());
        System.out.println("error 0 here");
        System.out.println("error 0 here");
        System.out.println(request.getRequestBody());

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                System.out.println("error 1 here");
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
        return resultOK;
    }

    public ArrayList<Wallet> getWallets(){
        System.out.println("In Get Wallets Method");


        String id = Preferences.get("id","1");
        System.out.println("Got Id");
        request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/api/wallet/all/"+id;
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetWallets::ResponseListener");
                wallets = parseWallet(new String(request.getResponseData()),id);
            } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return wallets;
    }

    private ArrayList<Wallet> parseWallet(String jsonText, String id) throws IOException, NoSuchFieldException, IllegalAccessException {
        System.out.println("In Parse Wallet Method");
        wallets = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> walletListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) walletListJson.get("root");
        for (Map<String,Object> obj : list){

            float walletIdF = Float.parseFloat(obj.get("id").toString());
            String walletId = String.valueOf((int) walletIdF);
            String walletLabel = obj.get("walletLabel").toString();
            String walletAddress = obj.get("walletAddress").toString();
            String imgPath = obj.get("walletImageFileName").toString();
            boolean isMain = true;
            boolean isActive = true;
            String coinCode;
            if(obj.get("nodeId") != null) {
                Map<String, Object> node = (Map<String, Object>) obj.get("nodeId");
                 coinCode = node.get("coinCode").toString();

            }
            else {
                coinCode = "Not Available";
            }
            Float balance = Float.parseFloat(obj.get("balance").toString());

            Wallet wallet = new Wallet();
            wallet.setId(walletId);
            wallet.setWalletLabel(walletLabel);
            wallet.setClient(id);
            wallet.setBalance(balance);
            wallet.setCode(coinCode);
            wallet.setWalletAddress(walletAddress);
            wallet.setImgPath(imgPath);
            wallet.setActive(isActive);
            wallet.setMain(isMain);
            wallets.add(wallet);
        }


        return wallets;
    }

    public boolean deleteWallet(int id){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Deleting your wallet ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/wallet/delete/"+id;
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();

        return resultOK;
    }

    public boolean updateWallet(Wallet wallet) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Updating Your Wallet ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/api/wallet/update/"+wallet.getId();
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);
        request.addArgument("label",wallet.getWalletLabel());
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
        return resultOK;
    }
}
