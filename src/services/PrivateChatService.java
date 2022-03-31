package services;

import Entities.GroupChat;
import Entities.Message;
import Entities.PrivateChat;
import Entities.User;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import utils.Statics;

import java.io.IOException;
import java.util.*;

public class PrivateChatService {

    public ArrayList<PrivateChat> Privatechat;
    public ArrayList<PrivateChat> Privatechat2;
    public ArrayList<PrivateChat> AddPrivatechat2;
    public ArrayList<User> Users;
    public boolean resultOK;
    public ConnectionRequest req;
    private static PrivateChatService Instance = null;

    private PrivateChatService() {
        this.req = new ConnectionRequest();
    }

    public static PrivateChatService getInstance() {
        if (Instance == null) {
            Instance = new PrivateChatService();
        }
        return Instance;
    }
    public ArrayList<PrivateChat> parseUsersContacter(String jsonText) {
        try {
            Privatechat = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                PrivateChat t = new PrivateChat();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                if (obj.get("Received") == null) {
                    t.setReceived(null);
                } else if (obj.get("Sender") == null) {
                    t.setSender(null);
                } else {
                    t.setSender(obj.get("Sender").toString());
                    t.setReceived(obj.get("Received").toString());
                }
                Privatechat.add(t);
            }

        } catch (IOException ex) {

        }
        return Privatechat;
    }

    public ArrayList<PrivateChat> ListUsersContacter(int idUser) {
        String url = Statics.BASE_URL + "afficheuserContacter/"+idUser;
        System.out.println("===>" + url);
        req.setUrl(url);
       // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Privatechat = parseUsersContacter(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Privatechat;
    }



    public ArrayList<PrivateChat> parselistPrivateChat(String jsonText) {
        try {
            Privatechat2 = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
                for (Map<String, Object> obj : list) {
                    PrivateChat t = new PrivateChat();
                    float id = Float.parseFloat(obj.get("id").toString());
                    t.setId((int) id);

                    if (obj.get("Received") == null) {
                        t.setReceived(null);
                    } else if (obj.get("Sender") == null) {
                        t.setSender(null);
                    } else {
                        t.setSender(obj.get("Sender").toString());
                        t.setReceived(obj.get("Received").toString());
                    }
                    Privatechat2.add(t);
                }
            } catch (IOException ex) {

        }
        return Privatechat2;
    }

    public ArrayList<PrivateChat> listPrivateChat(int idOtherUser) {
        String url = Statics.BASE_URL + "private/"+idOtherUser+"?CurrentUser=1";
        System.out.println("===>" + url);
        req.setUrl(url);
       req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Privatechat2 = parselistPrivateChat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Privatechat2;
    }
    String jsonText;
    public ArrayList<PrivateChat> AddPrivateChat( int idOtherUser) {

        System.out.println("********");
        String url = Statics.BASE_URL + "Addprivate/"+idOtherUser+"?CurrentUser=1";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(true);
       // req.addArgument("contenu", prv.);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Privatechat2;
    }
}
