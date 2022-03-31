package com.codename1.uikit.pheonixui;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.uikit.pheonixui.model.Wallet;
import com.codename1.uikit.pheonixui.service.WalletService;

public class UpdateWalletInfo extends BaseForm {
    private final Wallet updatedWallet;
    //-- DON'T EDIT BELOW THIS LINE!!!
    private final com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private final com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private final com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    private final com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    private final Label gui_label_1 = new Label();
    private final Label gui_label_2 = new Label();
    private final com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    public UpdateWalletInfo(Wallet wallet) {
        this(com.codename1.ui.util.Resources.getGlobalResources(), wallet);
    }
    public UpdateWalletInfo(com.codename1.ui.util.Resources resourceObjectInstance, Wallet wallet) {
        updatedWallet = wallet;
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SignInTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getContentPane().setUIID("SignInForm");

        gui_Text_Field_2.setText(wallet.getId());
        gui_Button_2.addActionListener(evt -> {
            updatedWallet.setId(gui_Text_Field_2.getText());
            updatedWallet.setWalletLabel(gui_Text_Field_1.getText());
            if (WalletService.getInstance().updateWallet(updatedWallet))
                Dialog.show("Success", "Wallet Updated", "OK", null);
        });

        getToolbar().addCommandToLeftBar("", mat, e -> new WalletInfoForm(updatedWallet).showBack());


    }

    @Override
    protected boolean isCurrentWallets() {
        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Update Wallet");
        setName("UpdateWallet");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");

        gui_Component_Group_1.addComponent(gui_label_1);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);

        gui_Component_Group_1.addComponent(gui_Text_Field_2);

        gui_Text_Field_1.setText("");
        gui_Text_Field_1.setName("Updated Label");

        gui_Text_Field_2.setName("Wallet id");
        gui_Text_Field_2.setEditable(false);

        gui_label_1.setText("Wallet Label");
        gui_label_1.setUIID("label");

        gui_label_2.setUIID("label");
        gui_label_2.setText("Your id");


        gui_Container_1.addComponent(gui_Button_2);

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Create Wallet");
        gui_Button_2.setName("Button_2");

        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");

    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
