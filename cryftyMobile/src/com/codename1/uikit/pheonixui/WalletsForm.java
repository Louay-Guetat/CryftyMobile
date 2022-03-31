package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.pheonixui.model.Wallet;
import com.codename1.uikit.pheonixui.service.WalletService;

import java.util.ArrayList;

public class WalletsForm extends BaseForm {

    private ArrayList<Wallet> wallets = new ArrayList<>();
    private final com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());

    public WalletsForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public WalletsForm(Resources resourceObjectInstance) {
        System.out.println("OnLoad");
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);


        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {
            new ProfileForm(resourceObjectInstance).show();
        });

        System.out.println("Befoore List Generation");

        Container walletList = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0)
                    wallets = WalletService.getInstance().getWallets();

                if (index + amount > wallets.size()) {
                    amount = wallets.size() - index;
                    System.out.println("hello salem");
                    if (amount <= 0) {
                        System.out.println("amount is 0");
                        return null;
                    }
                }
                Component[] more = new Component[amount];
                for (int iter = 0; iter < amount; iter++) {

                    int offset = index + iter;
                    MultiButton mb = new MultiButton(wallets.get(offset).getWalletLabel());
                    mb.setTextLine2("Click for further Details");
                    mb.setTextLine3("Balance : " + wallets.get(offset).getBalance() + " " + wallets.get(offset).getCode());
                    System.out.println("repeat");

                    mb.setNameLine1("Label_3");
                    mb.setUIIDLine2("RedLabel");
                    mb.setUIIDLine3("SmallFontLabel");
                    mb.setIcon(resourceObjectInstance.getImage("label_round.png"));
                    mb.addActionListener(e -> {
                        new WalletInfoForm(wallets.get(offset)).show();
                    });
                    more[iter] = mb;

                }
                return more;
            }
        };
        System.out.println("After List Generation");

        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, walletList);
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder) fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {
            fab.setUIID("FloatingActionButtonClose");
            Image oldImage = fab.getIcon();
            FontImage image = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "FloatingActionButton", 3.8f);
            fab.setIcon(image);
            Dialog popup = new Dialog();
            popup.setDialogUIID("Container");
            popup.setLayout(new LayeredLayout());

            Button c1 = new Button(resourceObjectInstance.getImage("icon-wallet-1.jpg"));

            Button trans = new Button(" ");
            trans.setUIID("Container");
            c1.setUIID("Container");

            Style c1s = c1.getAllStyles();


            c1s.setMarginUnit(Style.UNIT_TYPE_DIPS);


            c1s.setMarginBottom(16);
            c1s.setMarginLeft(12);
            c1s.setMarginRight(3);


            popup.add(trans).
                    add(FlowLayout.encloseIn(c1));

            ActionListener a = ee -> new AddWalletForm(this).show();
            ActionListener b = ee -> popup.dispose();

            trans.addActionListener(b);
            c1.addActionListener(a);


            popup.setTransitionInAnimator(CommonTransitions.createEmpty());
            popup.setTransitionOutAnimator(CommonTransitions.createEmpty());
            popup.setDisposeWhenPointerOutOfBounds(true);
            int t = WalletsForm.this.getTintColor();
            WalletsForm.this.setTintColor(0);
            popup.showPopupDialog(new Rectangle(WalletsForm.this.getWidth() - 10, WalletsForm.this.getHeight() - 10, 10, 10));
            WalletsForm.this.setTintColor(t);
            fab.setUIID("FloatingActionButton");
            fab.setIcon(oldImage);
        });

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Wallets", "Title"),
                        new Label(String.valueOf((long) wallets.size()), "InboxNumber")
                )
        );
    }

    @Override
    protected boolean isCurrentWallets() {
        return true;
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
        addComponent(gui_Container_1);
    }
}
