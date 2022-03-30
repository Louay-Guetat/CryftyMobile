/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.codename1.uikit.pheonixui;

import Entities.GroupChat;
import Entities.Message;
import Entities.PrivateChat;
import Entities.User;
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
import services.GroupeService;
import services.MessageService;
import services.PrivateChatService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class InboxForm extends BaseForm {

    public InboxForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public InboxForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
        
        installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});
        

        
        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> new AddGroupForm(resourceObjectInstance).show());
    }

//-- DON'T EDIT BELOW THIS LINE!!!


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
        addComponent(gui_Container_1);

        Container GroupsList = new Container(BoxLayout.y());
        Container PrivateChatList = new Container(BoxLayout.y());
        for (GroupChat c : GroupeService.getInstance().ListGroups(2))
        {
            MultiButton mb = new MultiButton(c.getNom());
            mb.addPointerPressedListener(e -> new ConversationForm(c).show());
            for (Message msg : MessageService.getInstance().Listlastmsg(c.getId()))
            {
                 mb.setTextLine2(msg.getContenu());
            }
            Button lsupp=new Button("");
            lsupp.setUIID("NewsTopLine");
            Style supprimerStyle= new Style(lsupp.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf21f1f);
            FontImage supprimerImage =FontImage.createMaterial(FontImage.MATERIAL_DELETE,supprimerStyle);
            lsupp.setIcon(supprimerImage);
            lsupp.setTextPosition(RIGHT);
            lsupp.addActionListener((e) -> {
                Dialog diag = new Dialog("suppression");
                if(diag.show("suppression","Vous voulez supprimer ce group?","Annuler","Oui"))
                {
                    diag.dispose();
                }else{
                    diag.dispose();
                    if (GroupeService.getInstance().deleteGroup(c.getId())) {
                       new InboxForm().show();
                    }

                }}
            );
            GroupsList.add(mb);
            if(c.getOwner().substring(4,7).contains("2")){
                gui_Container_2.getAllStyles().setMarginTop(20);
                gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
                gui_Container_2.setName("Container_2");
                gui_Container_2.addComponent(lsupp);

           // GroupsList.add(lsupp);
             }
            mb.setNameLine1("Label_3");
            mb.setUIIDLine2("Label");
            mb.setUIIDLine3("SmallFontLabel");
            mb.setIcon(resourceObjectInstance.getImage("label_round.png"));

        }
        ArrayList<User> users = GroupeService.getInstance().Listusers();
        for(int i=0; i<users.size();i++)
        {
            MultiButton mb =new MultiButton(users.get(i).getUsername());

            int finalI = i;

            mb.addPointerPressedListener((e)->{
                 PrivateChatService.getInstance().listPrivateChat(users.get(finalI).getId());
                {
                   // PrivateChat prv = new PrivateChat();
                    //if (PrivateChatService.getInstance().AddPrivateChat(prv,users.get(finalI).getId()))
                    {
                     //   new ConversationForm(prv).show();
                    }
                }

            });
        //for (PrivateChat prv : PrivateChatService.getInstance().listPrivateChat(users.get(i).getId()))
        {

            {

               // System.out.println(prv);
             //   mb.addPointerPressedListener(e -> new ConversationForm(prv).show());
               /* for (Message msg : MessageService.getInstance().Listlastmsg(prv.getId()))
                {
                    mb.setTextLine2(msg.getContenu());
                }*/
                GroupsList.add(mb);
                mb.setNameLine1("Label_3");
                mb.setUIIDLine2("Label");
                mb.setUIIDLine3("SmallFontLabel");
                mb.setIcon(resourceObjectInstance.getImage("label_round.png"));
            }
        }
    }
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER,GroupsList);
    }
//-- DON'T EDIT ABOVE THIS LINE!!!
}
