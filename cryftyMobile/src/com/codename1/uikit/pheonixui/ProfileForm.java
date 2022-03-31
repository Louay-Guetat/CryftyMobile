package com.codename1.uikit.pheonixui;


import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.uikit.pheonixui.service.ServiceUtilisateur;
import com.codename1.uikit.pheonixui.service.SessionManager;
import com.codename1.ui.util.Resources;

public class ProfileForm extends BaseForm {

    private static String i ;
    Form previous = Display.getInstance().getCurrent();
    public ProfileForm(Resources res) {


        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("back-logo.jpg");
        Image mask = res.getImage("back-logo.jpg");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());






        String name = SessionManager.getUserName();
        String emailUser = SessionManager.getEmail();

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label(name, "Title"),
                                new Label(emailUser, "SubTitle")
                        )
                ).add(BorderLayout.WEST, profilePicLabel),
                GridLayout.encloseIn()
        );

        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);

        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        Button modify = new Button("Save Changes");
        Button back = new Button("back");
        Button reclamation = new Button("reclamation");


        back.addActionListener(e -> previous.showBack());

        reclamation.addActionListener((e)->{
            new AjoutReclamationForm(res).show();
        });


        //Button picture=new Button("Photo");

        add(new Label("Modfier your account", "TodayTitle"));

        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        /* add(LayeredLayout.encloseIN(
        s1,
         BorderLayout.south(
                     GridLayout.encloseIn(3,
                             FlowLayout.encloseCenter( )
                     ))
        ));*/
        String us = SessionManager.getUserName();
        System.out.println(us);
        TextField username = new TextField(us);
        username.setUIID("TextFieldBlack");
        addStringValue("username", username);
        TextField password = new TextField(SessionManager.getPassowrd(), "password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("password", password);
        TextField email = new TextField(SessionManager.getEmail(), "email", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("email", email);
        modify.setUIID("Edit");
        back.setUIID("back");
        reclamation.setUIID("reclamation");
        //addStringValue("",picture)
        addStringValue("", modify);
        addStringValue("", back);
        addStringValue("", reclamation);

        /* TextField path= new TextField("");
       picture.addActionListener(e->{
       i=Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
       if(i != null){
           Image im;
           try{
           im=Image.createImage(i);
           im=im.scaled(res.getImage("photo-profile.jpg").getWidth(),res.gaetImage("photo-profile.jpg").getHeight());
           System.out.println(i);
           path.setText(i);

           }
           catch(IOException ex) {
               System.out.println("Could not load image");

           }

       }

       });*/

        modify.addActionListener((edit) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            ServiceUtilisateur.EditUser(username.getText(), password.getText(), email.getText());
            SessionManager.setUserName(username.getText());
            SessionManager.setPassowrd(password.getText());
            SessionManager.setEmail(email.getText());
            //SessionManager.setPhoto(username.getText()+".jpg");
            //Dialog.show("SUCCESS","Changed was saved","OK",null);
            ipDlg.dispose();
            refreshTheme();

        });

    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }



    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, v));
        //  add(createLineSeparator(0xeeeeee));
    }


    
//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ProfileForm");
        setName("ProfileForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
