/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui.service;


import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Reclamation;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chemez
 */
public class ServiceReclamation {

    public static ServiceReclamation instance = null;

    public static boolean resultOk = true;

    //Initilisation connection request
    private ConnectionRequest req;

    public static ServiceReclamation getInstance(){
        if(instance == null)
            instance = new ServiceReclamation();
        return instance;
    }
    public ServiceReclamation(){

        req = new ConnectionRequest();

    }
    //Ajout
    public void ajoutReclamation(Reclamation reclamation){
        String url = Statics.BASE_URL+"/addReclamation?name="+reclamation.getName()+"&message="+reclamation.getMessage()+"&email="+reclamation.getEmail()+"&subject="+reclamation.getSubject()+"&client="+reclamation.getIduser();

        req.setUrl(url);
        req.addResponseListener((e) ->{

            String str = new String(req.getResponseData());//Response json
            System.out.println("data == "+str);


        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de requette
    }

    //Affiche
    public ArrayList<Reclamation>affichageReclamations(){
        ArrayList<Reclamation> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/displayReclamations";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt){
                JSONParser jsonp;
                jsonp = new JSONParser();

                try{
                    Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapReclamations.get("root");

                    for(Map<String, Object> obj : ListOfMaps){
                        Reclamation re = new Reclamation();

                        //id float
                        float id = Float.parseFloat(obj.get("id").toString());
                        String subject = obj.get("subject").toString();
                        String message = obj.get("message").toString();
                        String email = obj.get("email").toString();
                        String name = obj.get("name").toString();

                        re.setId((int)id);
                        re.setName(name);
                        re.setEmail(email);
                        re.setMessage(message);
                        re.setSubject(subject);
                        //insert data into ArrayList result
                        result.add(re);

                    }


                } catch(Exception ex){

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;


    }


    //Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update

    public Reclamation DetailRecalamation( int id , Reclamation reclamation) {

        String url = Statics.BASE_URL+"/detailReclamation?"+id;
        req.setUrl(url);

        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {

            JSONParser jsonp = new JSONParser();
            try {

                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                reclamation.setName(obj.get("name").toString());
                reclamation.setEmail(obj.get("email").toString());
                reclamation.setMessage(obj.get("message").toString());
                reclamation.setSubject(obj.get("subject").toString());


            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }


            System.out.println("data === "+str);



        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return reclamation;


    }


    //Delete
    public boolean deleteReclamation(int id ) {
        String url = Statics.BASE_URL +"/deleteReclamation?id="+id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }



    //Update
    public boolean modifierReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL +"/updateReclamation?id="+reclamation.getId()+"&name="+reclamation.getName()+"&email="+reclamation.getEmail()+"&subject="+reclamation.getSubject()+"&message="+reclamation.getMessage();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }
}
