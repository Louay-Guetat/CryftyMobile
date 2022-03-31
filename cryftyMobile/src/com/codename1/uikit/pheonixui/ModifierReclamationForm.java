package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Reclamation;
import com.codename1.uikit.pheonixui.service.ServiceReclamation;


public class ModifierReclamationForm extends BaseForm {

    Form current;
    public ModifierReclamationForm(Resources res , Reclamation r) {




        current = this ;
 
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);


        installSidemenu(res);

        TextField name = new TextField(r.getName() , "Name" , 20 , TextField.ANY);
        TextField email = new TextField(r.getEmail() , "Email" , 20 , TextField.ANY);
        TextField subject = new TextField(r.getSubject() , "Subject" , 20 , TextField.ANY);
        TextField message = new TextField(r.getMessage() , "message" , 20 , TextField.ANY);


        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox

        ComboBox etatCombo = new ComboBox();

        etatCombo.addItem("Non Traiter");

        etatCombo.addItem("Traiter");







        name.setUIID("NewsTopLine");
        email.setUIID("NewsTopLine");
        subject.setUIID("NewsTopLine");
        message.setUIID("NewsTopLine");

        name.setSingleLineTextArea(true);
        email.setSingleLineTextArea(true);
        subject.setSingleLineTextArea(true);
        message.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick btnModifer

        btnModifier.addPointerPressedListener(l ->   {

            r.setName(name.getText());
            r.setEmail(email.getText());
            r.setSubject(subject.getText());
            r.setMessage(message.getText());

           /*if(etatCombo.getSelectedIndex() == 0 ) {
               r.setEtat(0);
           }
           else
               r.setEtat(1);*/


            //appel fonction modfier reclamation men service

            if(ServiceReclamation.getInstance().modifierReclamation(r)) { // if true
                new ListReclamationForm(res).show();
            }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListReclamationForm(res).show();
        });


        Label l2 = new Label("");

        Label l3 = new Label("");

        Label l4 = new Label("");

        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                new FloatingHint(name),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(subject),
                createLineSeparator(),//ligne de séparation
                new FloatingHint(message),
                createLineSeparator(),
                btnModifier,
                btnAnnuler


        );

        add(content);
        show();


    }

    public ModifierReclamationForm(Resources globalResources) {
        super();
    }


//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.LayeredLayout());
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("ModifierReclamationForm");
        setName("ModifierReclamationForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
