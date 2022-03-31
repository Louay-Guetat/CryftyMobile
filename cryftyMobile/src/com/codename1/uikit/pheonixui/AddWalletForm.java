package com.codename1.uikit.pheonixui;

import com.codename1.io.Preferences;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.uikit.pheonixui.model.Wallet;
import com.codename1.uikit.pheonixui.service.WalletService;

public class AddWalletForm extends BaseForm {

    //-- DON'T EDIT BELOW THIS LINE!!!
    private final com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private final com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private final com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private final com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private final Label gui_label_1 = new Label();
    private final Label gui_label_2 = new Label();
    private final com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    public AddWalletForm(WalletsForm walletsForm) {
        this(com.codename1.ui.util.Resources.getGlobalResources(), walletsForm);

    }
    public AddWalletForm(com.codename1.ui.util.Resources resourceObjectInstance, WalletsForm walletsForm) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> walletsForm.showBack());
        getContentPane().setUIID("SignInForm");
    }

    @Override
    protected boolean isCurrentWallets() {
        return true;
    }

    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Add Wallet");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");

        gui_Component_Group_1.addComponent(gui_label_1);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);

        gui_Component_Group_1.addComponent(gui_Text_Field_2);

        gui_Text_Field_1.setText("");
        gui_Text_Field_1.setName("label");
        gui_Text_Field_2.setText("1");
        gui_Text_Field_2.setName("client");
        gui_Text_Field_2.setEditable(false);

        gui_label_1.setText("Wallet Label");
        gui_label_1.setUIID("label");

        gui_label_2.setUIID("label");
        gui_label_2.setText("Your id ");


        gui_Container_1.addComponent(gui_Button_2);

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Create Wallet");
        gui_Button_2.setName("Button_2");

        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");

    }// </editor-fold>

    //-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {

        if (gui_Text_Field_1.getText() == "") {
            Dialog.show("Error", "Label is required", "OK", null);
        } else {
            Wallet wallet = new Wallet();
            wallet.setWalletLabel(gui_Text_Field_1.getText());
            System.out.println("error 0 here");
            wallet.setClient(Preferences.get("id", "1"));
            System.out.println("error 0.1 here");
            System.out.println(wallet.getClient());

            if (WalletService.getInstance().addWallet(wallet)) {
                System.out.println("error 1 here");
                Dialog.show("Success", "Wallet added", "OK", null);

            } else {
                System.out.println("error 2 here");
                Dialog.show("Error", "Request Error", "OK", null);
            }
        }

    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;

        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if (sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if (sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
}
