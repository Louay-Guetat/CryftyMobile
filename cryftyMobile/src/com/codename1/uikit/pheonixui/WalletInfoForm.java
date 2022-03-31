package com.codename1.uikit.pheonixui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.pheonixui.model.Wallet;
import com.codename1.uikit.pheonixui.service.WalletService;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;

public class WalletInfoForm extends BaseForm {

    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;

    public WalletInfoForm(Wallet wallet) {
        this(com.codename1.ui.util.Resources.getGlobalResources(), wallet);

    }

    public WalletInfoForm(com.codename1.ui.util.Resources resourceObjectInstance, Wallet wallet) {
        initGuiBuilderComponents(resourceObjectInstance);
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new WalletsForm().showBack());


        String url = Statics.IMG_URL + "/uploads/walletImages/" + wallet.getImgPath();

        //getting image
        try {
            enc = EncodedImage.create("/load.png");
            System.out.println("before");
            imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);

            System.out.println("after" + imgs.getImageName());
            imgv = new ImageViewer();
            imgv.setImage(imgs);

        } catch (Exception e) {
            try {
                imgv = new ImageViewer(Image.createImage("/load.png"));
                System.out.println("loading Catch 1 ");
            } catch (IOException ex) {
                System.out.println("error Catch1");
            }
            System.out.println("error Catch2");
        }

        //gui elements
        Label label = new Label(wallet.getWalletLabel(), "Label_3_1");
        Button button = new Button("Upload New Image");
        Button buttonUpdate = new Button("Update Wallet");
        Button buttonDelete = new Button("Delete Wallet");

        Label address = new Label(wallet.getWalletAddress());
        Label balance = new Label(wallet.getBalance().toString() +" "+ wallet.getCode(),"RedLabel");

        //logging
        System.out.println(url);
        System.out.println(wallet);
        //end_logging

        // 1-upload image // 2-update wallet // 3-deleteWallet
        button.addActionListener(evt -> {
            new UploadWalletImageForm(wallet).show();
        });
        buttonUpdate.addActionListener(evt -> {
            new UpdateWalletInfo(wallet).show();
        });
        buttonDelete.addActionListener(evt -> {
            boolean bool = WalletService.getInstance().deleteWallet(Integer.parseInt(wallet.getId()));
            if (bool) {
                Dialog.show("Success", "Wallet has been deleted , redirecting back the the list", "OK", null);
            }

        });
        //end_buttons
        addComponent(new Container(BoxLayout.yCenter()).addAll(imgv, label, address, balance, button, buttonUpdate, buttonDelete));


    }

////-- DON'T EDIT BELOW THIS LINE!!!


    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("WalletInfoForm");
        setName("SigninForm");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
