package com.codename1.uikit.pheonixui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.pheonixui.model.Wallet;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;

public class UploadWalletImageForm extends com.codename1.ui.Form {
    public UploadWalletImageForm(Wallet wallet) {
        this(com.codename1.ui.util.Resources.getGlobalResources());
        this.wallet = wallet;
    }
        Wallet wallet;
    public UploadWalletImageForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new WalletsForm().showBack());
        gui_button_1.addActionListener(evt -> {
            if(!"".equals(gui_text_1.getText())){
                MultipartRequest multipartRequest = new MultipartRequest();
                String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

                multipartRequest.setUrl(Statics.URL_UPLOAD_IMAGE+wallet.getId());
                multipartRequest.setPost(true);
                String mime = "image/jpeg";
                try {
                    multipartRequest.addData("file",filePath,mime);
                } catch (IOException e) {
                    Dialog.show("Error",e.getMessage(),"OK",null);
                }
                multipartRequest.setFilename("file",gui_text_1.getText()+".jpg");

                InfiniteProgress progress = new InfiniteProgress();
                Dialog dialog = progress.showInfiniteBlocking();
                multipartRequest.setDisposeOnCompletion(dialog);
                NetworkManager.getInstance().addToQueueAndWait(multipartRequest);
                Dialog.show("Success","Image Uploaded","OK",null);
            }else {
                Dialog.show("Error","Invalid Image Name","OK",null);
            }
        });



    }
    
//-- DON'T EDIT BELOW THIS LINE!!!

    private Button gui_button_1 = new Button("Upload Image");
    private TextField gui_text_1 = new TextField("","Image name");
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("WalletInfoForm");
        setName("SigninForm");
        addAll(gui_text_1,gui_button_1);
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
}
