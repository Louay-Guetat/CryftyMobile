/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui.service;

import com.codename1.io.*;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.WalletsForm;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.util.Map;

/**
 *
 * @author chemez
 */
public class ServiceUtilisateur {
    
    
    public static ServiceUtilisateur instance = null;
    public static boolean resultOK = true;
        String json;
    
    //Initilisation connection request 
    private ConnectionRequest req;
    
    public static ServiceUtilisateur getInstance(){
        if(instance == null)
            instance = new ServiceUtilisateur();
        return instance;
    }
    public ServiceUtilisateur(){
        
        req = new ConnectionRequest();
        
    }
    
    
    //Signup
    public void signup(TextField username,TextField firstName,TextField lastName,TextField email,TextField phoneNumber,TextField age,TextField address,TextField password,TextField confirmPassword,ComboBox<String> roles,Resources res){
        
        
            
            String url = Statics.BASE_URL+"/client/signup?username="+username.getText().toString()+"&firstName="+firstName.getText().toString()+"&lastName="+lastName.getText().toString()+"&email="+email.getText().toString()+"&phoneNumber="+phoneNumber.getText().toString()+"&age="+age.getText().toString()+"&address="+address.getText().toString()+"&roles="+roles.getSelectedItem().toString()+"&password="+password.getText().toString();
        
            req.setUrl(url);
            //Control saisi
            if(username.getText().equals(" ")&&password.getText().equals(" ")&&email.getText().equals(" ")){
                Dialog.show("Erreur","Veuilez remplir les champs","ok",null);
            }
            
            //after execution url
            req.addResponseListener((e)->{
                
                byte[]data = (byte[])e.getMetaData();
                
                String responseData = new String(data);
                
                System.out.println("data ==="+responseData);
            });
            // after requete
            NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
    
    //SignIn
    
    public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/client/signin?username="+username.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray())); 
                
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                //photo 
                
                /*if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("photo").toString());*/
                
                System.out.println("current user ==>"+SessionManager.getEmail());
//                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                new WalletsForm().show();
                    
                    
            }     
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         NetworkManager.getInstance().addToQueueAndWait(req);
     //ba3d execution ta3 requete ely heya url nestanaw response ta3 server
    }
    
    

    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
    //edit user
    
    public static void EditUser(String username, String password, String email){
        String url = Statics.BASE_URL +"/client/editClient?username="+username+"&password="+password+"&email="+email;
                    MultipartRequest req = new MultipartRequest();
                    
                    req.setUrl(url);
                    req.setPost(true);
                    req.addArgument("id",String.valueOf(SessionManager.getId()));
                    req.addArgument("username", username);
                    req.addArgument("password", password);
                    req.addArgument("email", email);
                    System.out.println(email);
                    req.addResponseListener((response)-> {
                        byte[] data = (byte[]) response.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")){
                            
                        }else {
                            Dialog.show("Erreur","Succès","ok",null);
                        }
                        
                    });
                    NetworkManager.getInstance().addToQueueAndWait(req);
    }
    


}

