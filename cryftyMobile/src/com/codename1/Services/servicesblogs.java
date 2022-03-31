package com.codename1.Services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entites.BlogArticle;
import com.mycomany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class servicesblogs {
    public boolean resultOK;

    //singleton
    public static servicesblogs instance= null;

    //public static boolean resultOk = true ;
    //initialisation connction request
    private final ConnectionRequest req;

    public static servicesblogs getInstance(){

        if (instance == null)
            instance = new servicesblogs();
        return instance ;
    }
    public servicesblogs () {
        req = new ConnectionRequest() ;
    }
    //ajout

    public boolean ajouterArticle(BlogArticle blogArticle) {
     //   ConnectionRequest req = new ConnectionRequest();
        String url=Statics.BASE_URL+"addArticle?title="+blogArticle.getTitle()+"&contents="+blogArticle.getContents()+"&category="+blogArticle.getCategory()+"&author="+blogArticle.getAuthor()+"&date="+blogArticle.getDate()+"&image="+blogArticle.getImage();
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("title",blogArticle.getTitle());
        req.addArgument("contents",blogArticle.getContents());
        req.addArgument("category",blogArticle.getCategory());
        req.addArgument("author",blogArticle.getAuthor());

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


    //affichage
    public ArrayList<BlogArticle>listarticle() {
        ArrayList<BlogArticle> result = new ArrayList <> ();

        String url =Statics.BASE_URL+"listarticle";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String,Object>mapArticles= jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapArticles.get("root");
                    for(Map<String, Object> obj : listOfMaps) {
                        BlogArticle re = new BlogArticle ();

                        float id = Float.parseFloat(obj.get("id").toString());


                        String title = obj.get("title").toString();
                        String contents = obj.get("contents").toString();
                        String category = obj.get("category").toString();
                        String author = obj.get("author").toString();
                        String Date = obj.get("date").toString();
                        String image =obj.get("image").toString();




                        re.setTitle(title);
                        re.setContents(contents);
                        re.setId((int)id);
                        re.setCategory(category);
                        re.setAuthor(author);
                        re.setDate(Date);
                        re.setImage(image);


                        result.add(re);


                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    //delete
    public  boolean deleteArticle (int id) {
        String url = Statics.BASE_URL +"deleteArticle?id="+id;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){

            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);

            }


        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    //update
    public boolean modificationRestaurant(BlogArticle blogArticle){

        String url = Statics.BASE_URL +"updateArticle?id="+blogArticle.getId()+"&title="+blogArticle.getTitle()+"&contents="+blogArticle.getContents()+"&category="+blogArticle.getCategory()+"&author="+blogArticle.getAuthor()+"&date="+blogArticle.getDate()+"&image="+blogArticle.getImage();

        req.setUrl(url);
        req.addResponseListener(new ActionListener <NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;// code response http 200
                req.removeResponseListener(this);

            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
}
