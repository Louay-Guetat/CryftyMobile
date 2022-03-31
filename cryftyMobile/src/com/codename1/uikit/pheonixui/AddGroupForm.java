package com.codename1.uikit.pheonixui;

import Entities.GroupChat;
import Entities.User;
import com.codename1.components.*;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.RoundBorder;
import services.GroupeService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class AddGroupForm extends BaseForm {
    BaseForm current;

    public AddGroupForm(com.codename1.ui.util.Resources resourceObjectInstance) {
      initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Add new Group", "Title")
                      //  new Label("19", "InboxNumber")
                )
        );

       installSidemenu(resourceObjectInstance);

        /* getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});



        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        rb.uiid(true);
        fab.bindFabToContainer(getContentPane());
        fab.addActionListener(e -> new ConversationForm(resourceObjectInstance).show());*/
    }
    private Container gui_Container_1 = new Container(new BorderLayout());


    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        current=this;
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
        addComponent(gui_Container_1);

        Container AddGroupList = new Container(BoxLayout.y());
        TextComponent inputNom = new TextComponent().label("Group name");
        AddGroupList.add(inputNom);

       ArrayList<User> users = GroupeService.getInstance().Listusers();

        CheckBox ckusers[] = new CheckBox[users.size()];
        Container usersContainer = new Container();
        for(int i=0; i<ckusers.length;i++)
        {
            ckusers[i] = new CheckBox (users.get(i).getUsername());
            usersContainer.addComponent(ckusers[i]);

        }

     gui_Container_1.addComponent(BorderLayout.NORTH, usersContainer);
        Button btnValider = new Button("Add Group");
        Container btnContainer = new Container();
        btnContainer.addComponent(btnValider);
        gui_Container_1.addComponent(BorderLayout.SOUTH, btnContainer);

        ArrayList<String>  listParticipants = new ArrayList<>() ;
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((inputNom.getText().equals(""))) {
                    Dialog.show("Alert", "name is required", new Command("OK"));
                }
                else {
                    for(int i =0; i<users.size();i++){



                        if(ckusers[i].isSelected())
                        {

                             System.out.println("r"+ckusers[i].isSelected());
                             System.out.println("id"+  users.get(i).getId());
                             listParticipants.add(String.valueOf(users.get(i).getId()));


                         }
                    }
                        GroupChat t = new GroupChat(listParticipants,String.valueOf(inputNom.getText()).toString());
                        if (GroupeService.getInstance().addGroup(t)) {
                            //Dialog.show("Success", "Group created ", new Command("OK"));
                           new InboxForm().show();

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                }

            }
        });
      gui_Container_1.addComponent(BorderLayout.CENTER,AddGroupList);

    }
}
