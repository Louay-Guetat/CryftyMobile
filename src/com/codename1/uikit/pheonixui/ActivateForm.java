/*package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import java.util.Properties;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.service.ServiceUtilisateur;
import com.ibm.jvm.dtfjview.Session;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ActivateForm extends BaseForm {
    TextField email;
    public ActivateForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public ActivateForm(com.codename1.ui.util.Resources res) {
        initGuiBuilderComponents(res);


        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");

        add(BorderLayout.NORTH,
                BoxLayout.encloseY(
                        new Label(res.getImage("oublie1.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );

        email = new TextField("","saisir votre email",20,TextField.ANY);
        email.setSingleLineTextArea(false);

        Button valider = new Button("Valider");
        Label haveAnAcount = new Label("Retour se connecter?");
        Button signIn = new Button("Renouveler votre mot de passe");
        signIn.addActionListener( e-> previous.showBack());//yarja3 lel window ely9ablha
        signIn.setUIID("CenterLink");

        Container content = BoxLayout.encloseY(



                new FloatingHint(email),
                createLineSeparator(),
                valider,
                FlowLayout.encloseCenter(haveAnAcount),
                signIn
        );

        content.setScrollableY(true);

        add(BorderLayout.CENTER,content);

        valider.requestFocus();

        valider.addActionListener(e -> {

            InfiniteProgress ip = new InfiniteProgress();

            final Dialog ipDialog =  ip.showInfiniteBlocking();

            //houni bch nzido API SEND MAIL autrement bch n3ayto lel function ta3o mais 9bal njibo image oublier.png
            sendMail(res);
            ipDialog.dispose();
            Dialog.show("Mot de passe","Nous avons envoyé le mot de passe a votre e-mail. Veuillez vérifier votre boite de réception",new Command("OK"));
            new SignInForm(res).show();
            refreshTheme();
        });

    }
    public void sendMail(Resources res) {
        try {

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp"); //SMTP protocol
            props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtps.auth", "true"); //enable authentication

            Session session = Session.getInstance(props,null); // nsobo  javax.mail .jar


            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("Reintialisation mot de passe <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
            msg.setSubject("Cryfty  : Confirmation du ");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);//mp taw narj3lo
            String txt = "Bienvenue sur Cryfty : Tapez ce mot de passe : "+mp+" dans le champs requis et appuiez sur confirmer";


            msg.setText(txt);

            SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;

            st.connect("smtp.gmail.com",465,"eesprit248@gmail.com","Chedi123456789");

            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response : "+st.getLastServerResponse());

        }catch(Exception e ) {
            e.printStackTrace();
        }
    }



    
//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ActivateForm");
        setName("ActivateForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
*/