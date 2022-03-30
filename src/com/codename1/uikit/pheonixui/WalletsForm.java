package com.codename1.uikit.pheonixui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import static com.codename1.ui.util.Resources.getGlobalResources;

public class WalletsForm extends BaseForm {

    public WalletsForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentWallets() {
        return true;
    }

    public WalletsForm(Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Wallets", "Title"),
                        new Label("20", "InboxNumber")
                )
        );
        installSidemenu(resourceObjectInstance);
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {
        });

        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> {

        });
    }
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
        addComponent(gui_Container_1);

        Container walletList = new Container(BoxLayout.y());
        for (int i = 0; i < 10; i++) {
            MultiButton mb = new MultiButton("Wallet number " + i);
            mb.setTextLine2("Click for further Details");
            mb.setTextLine3("Balance : "+ i * 6.25);
            walletList.add(mb);
            mb.setNameLine1("Label_3");
            mb.setUIIDLine2("RedLabel");
            mb.setUIIDLine3("SmallFontLabel");
            mb.setIcon(resourceObjectInstance.getImage("label_round.png"));

        }

        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER,walletList);
    }
}
