package services;

import Entities.GroupChat;
import Entities.Message;
import Entities.User;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import utils.Statics;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageService {
    public ArrayList<Message> message;
    public ArrayList<Message> messages;
    public boolean resultOK;
    public ConnectionRequest req;
    private static MessageService Instance = null;

    private MessageService() {
        this.req = new ConnectionRequest();
    }

    public static MessageService getInstance() {
        if (Instance == null) {
            Instance = new MessageService();
        }
        return Instance;
    }

    public ArrayList<Message> parseMsgs(String jsonText) {
        try {
            messages = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Message msgs = new Message();
                float id = Float.parseFloat(obj.get("id").toString());
                msgs.setId((int) id);
                if (obj.get("contenu") == null) {
                    msgs.setContenu(null);
                } else if((obj.get("createdAt").equals("")))
                {
                    msgs.setCreatedAt(null);
                }else if(obj.get("Sender").equals(""))
                {
                    msgs.setSender(null);
                }else {
                    msgs.setContenu(obj.get("contenu").toString());
                    msgs.setCreatedAt((obj.get("createdAt").toString()));
                    msgs.setSender((obj.get("Sender").toString()));
                }
                messages.add(msgs);
            }

        } catch (IOException ex) {

        }
        return messages;
    }

    public ArrayList<Message> ListMsgs(int id) {
        String url = Statics.BASE_URL + "afficheMsg/"+id;
        System.out.println("===>" + url);
        req.setUrl(url);
      //  req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                messages = parseMsgs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return messages;
    }
    public boolean deleteMsg(int id) {
        String url = Statics.BASE_URL + "deleteMsg/"+id;
        System.out.println("===>" + url);
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
return true;
    }

    public ArrayList<Message> parseLastMessage(String jsonText) {
        try {
            message = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Message msg = new  Message();

                if (obj.get("contenu") == null) {
                    msg.setContenu("null");
                }  else {
                    msg.setContenu(obj.get("contenu").toString());
                    // t.setParticipant(obj.get("participants").toString());
                }
                message.add(msg);
            }

        } catch (IOException ex) {

        }
        return message;
    }

    public ArrayList<Message> Listlastmsg(int id) {
        String url = Statics.BASE_URL + "affichelastMsg/"+id;
        System.out.println("===>" + url);
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                message = parseLastMessage(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return message;
    }

    public boolean SendMessage(Message msg,int idConv) {
        System.out.println(msg);
        System.out.println("********");
        String url = Statics.BASE_URL + "sendmsg/"+idConv+"/2?contenu="+msg.getContenu();
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("contenu", msg.getContenu());

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
}
