package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.uikit.pheonixui.service.ServiceUtilisateur;

import java.util.Vector;

public class SignUpForm extends BaseForm {
    public SignUpForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public SignUpForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField firstName = new TextField("", "FirstName", 20, TextField.ANY);
        TextField lastName = new TextField("", "LastName", 20, TextField.ANY);
        TextField phoneNumber = new TextField("", "PhoneNumber", 20, TextField.ANY);
        TextField address = new TextField("", "Address", 20, TextField.ANY);
        TextField age = new TextField("", "Age", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);


        //Role
        Vector<String> vectorRole;
        vectorRole = new Vector();
        vectorRole.add("Client");
        vectorRole.add("Admin");

        ComboBox<String> roles = new ComboBox<>(vectorRole);



        username.setSingleLineTextArea(false);
        firstName.setSingleLineTextArea(false);
        lastName.setSingleLineTextArea(false);
        phoneNumber.setSingleLineTextArea(false);
        address.setSingleLineTextArea(false);
        age.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("SignUp");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(resourceObjectInstance).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                username,
                firstName,
                lastName,
                phoneNumber,
                address,
                age,
                email,
                password,
                confirmPassword,
                roles//
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e )->{
            Dialog.show("Button tenzel","button tenzel","OK",null);
            ServiceUtilisateur.getInstance().signup(username, firstName, lastName, email, phoneNumber, age, address, password, confirmPassword, roles, resourceObjectInstance);
            Dialog.show("Success","account is saved","OK",null);
            new SignInForm(resourceObjectInstance).show();

        });
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("SignUpForm");
        setName("SignUpForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
