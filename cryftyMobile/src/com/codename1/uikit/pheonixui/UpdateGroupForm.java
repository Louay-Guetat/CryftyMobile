package com.codename1.uikit.pheonixui;

import Entities.Conversation;
import Entities.GroupChat;
import Entities.User;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import services.GroupeService;

import java.util.ArrayList;

public class UpdateGroupForm extends BaseForm{
    Form current;
    public UpdateGroupForm( Conversation c) {
        current = this;
        Container inputContainer = new Container(new BorderLayout(BoxLayout.Y_AXIS));
        TextComponent inputNom = new TextComponent().label("Group name");
        inputNom.text(c.getNom());
       addComponent(inputContainer);

        inputContainer.addComponent(BorderLayout.EAST,inputNom);
        ArrayList<User> users = GroupeService.getInstance().Listusers();
        ArrayList<User> participants=  GroupeService.getInstance().ListParticipants(c.getId());
        System.out.println(participants);
        System.out.println(users);
        CheckBox ckusers[] = new CheckBox[users.size()];
        Container usersContainer = new Container();

        addComponent(usersContainer);
        for(int i=0; i<ckusers.length;i++)
        {
            ckusers[i] = new CheckBox (users.get(i).getUsername());
            for(User pr : participants)
            {if (users.get(i).getUsername().contains(pr.getUsername()))

            {
                ckusers[i].setSelected(true);
            }

         }
            usersContainer.addComponent(ckusers[i]);
        }
        Button btnValider = new Button("Update Group");
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
                    if (GroupeService.getInstance().UpdateGroup(t,c.getId())) {
                        Dialog.show("Success", "Group updated ", new Command("OK"));
                        new InboxForm().show();

                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                }

            }
        });
        Container btnContainer = new Container();
        addComponent(btnContainer);
        btnContainer.addComponent(btnValider);
        getToolbar().setBackCommand("", e -> new ConversationForm(c).show());
    }

}
