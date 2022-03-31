package services;
import Entities.GroupChat;
import Entities.User;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class GroupeService {

    public ArrayList<GroupChat> Groups;
    public ArrayList<User> Participants;
    public ArrayList<User> Ow;
    public ArrayList<User> Users;
    public boolean resultOK;
    public ConnectionRequest req;
    private static GroupeService Instance = null;

    private GroupeService() {
        this.req = new ConnectionRequest();
    }

    public static GroupeService getInstance() {
        if (Instance == null) {
            Instance = new GroupeService();
        }
        return Instance;
    }
    String participant="";

    int max=0;
  //  String [] participants=new String[max];

    public boolean addGroup(GroupChat t) {
        System.out.println(t);
        System.out.println("********");
        for(int i =0;i<t.getParticipants().size();i++)
        {
            if (i==0)
            {
                participant+=t.getParticipants().get(i);
            }else
            {
                participant=participant+","+t.getParticipants().get(i);
            }

        }
       String  idOwner = Preferences.get("id","1");
       // participant=t.getParticipants();
        String url = Statics.BASE_URL + "AddGroup?nom="+ t.getNom()+"&participant=%5B"+ participant+"%5D&owner="+idOwner;
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("nom", t.getNom());
        req.addArgument("participant", participant);
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

    public boolean UpdateGroup(GroupChat t , int idGr) {
        System.out.println(t);
        System.out.println("********");
       String participants="";

       System.out.println(participants);

        System.out.println(t.getParticipants());
        for(int i =0;i<t.getParticipants().size();i++)
        {

            if (i==0)
            {
                participants+=t.getParticipants().get(i);
                System.out.println("testif"+participants);
            }else
            {
                participants=participants+","+t.getParticipants().get(i);
                System.out.println("testelse"+participants);
            }

        }
        // participant=t.getParticipants();
        String url = Statics.BASE_URL + "UpdateGroup/"+idGr+"?nom="+ t.getNom()+"&participant=%5B"+ participants+"%5D";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("nom", t.getNom());
        req.addArgument("participant", participants);
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

    public ArrayList<GroupChat> parseGroups(String jsonText) {
        try {
            Groups = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                GroupChat t = new GroupChat();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                /* t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));*/
                if (obj.get("nom") == null) {
                    t.setNom("null");
                } else if (obj.get("participants") == null) {
                    t.setParticipant(null);
                } else if (obj.get("Owner")==null)
                {
                    t.setOwner(null);
                }else {
                    t.setNom(obj.get("nom").toString());
                    t.setOwner(obj.get("Owner").toString());
                  // t.setParticipant(obj.get("participants").toString());
                }
                Groups.add(t);
            }

        } catch (IOException ex) {

        }
        return Groups;
    }

    public ArrayList<GroupChat> ListGroups(int idUser) {
        String url = Statics.BASE_URL + "afficheGroups/"+idUser;
        System.out.println("===>" + url);
        req.setUrl(url);
       // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Groups = parseGroups(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Groups;
    }

    public ArrayList<User> parseUsers(String jsonText) {
        try {
            Users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                if (obj.get("username") == null) {
                    t.setUsername("null");

                } else {
                    t.setUsername(obj.get("username").toString());

                }
                Users.add(t);
            }

        } catch (IOException ex) {

        }
        return Users;
    }

    public ArrayList<User> Listusers() {

        String url = Statics.BASE_URL + "afficheUsers/"+Preferences.get("id","1");;
        System.out.println("===>" + url);
        req.setUrl(url);
       // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
    public boolean deleteGroup(int id) {
        String url = Statics.BASE_URL + "deleteGrp/"+id;
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



    public ArrayList<User> parseParticipants(String jsonText) {
        try {
            Participants = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ParticipantsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ParticipantsListJson.get("root");

            for (Map<String, Object> obj : list) {
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                if (obj.get("username") == null) {
                    t.setUsername("null");
                }else {
                    t.setUsername(obj.get("username").toString());
                }
                Participants.add(t);
            }

        } catch (IOException ex) {

        }
        return Participants;
    }

    public ArrayList<User> ListParticipants(int idGroup) {
        String url = Statics.BASE_URL + "afficheMemberGroup/"+idGroup;
        System.out.println("===>" + url);
        req.setUrl(url);
        //req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Participants = parseParticipants(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Participants;
    }








    public ArrayList<User> parseOwner(String jsonText) {
        try {
            Ow = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> OwnerListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(OwnerListJson);
                User user = new User();
                float id = Float.parseFloat(OwnerListJson.get("id").toString());
                user.setId((int) id);
                if (OwnerListJson.get("username") == null) {
                    user.setUsername("null");
                }else {
                    user.setUsername(OwnerListJson.get("username").toString());
                }
                Ow.add(user);
            //}

        } catch (IOException ex) {

        }
        return Ow;
    }

    public ArrayList<User> ListOwner(int idGr) {
        String url = Statics.BASE_URL + "afficheOwnerGroup/"+idGr;
        System.out.println("===>" + url);
        req.setUrl(url);
      //  req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Ow = parseOwner(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Ow;
    }

}
