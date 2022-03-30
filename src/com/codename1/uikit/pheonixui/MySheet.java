package com.codename1.uikit.pheonixui;

import Entities.Conversation;
import Entities.User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Sheet;
import com.codename1.ui.layouts.BoxLayout;
import services.GroupeService;

public class MySheet extends Sheet {
    String username=" ";
    String usernameOwner=" ";
Conversation c;
int id;

    MySheet(Sheet parent,int id) {
        super(parent, "Members");
        Container cnt = getContentPane();
        cnt.setLayout(BoxLayout.y());
        usernameOwner+="Owner : ";
       for (User owner : GroupeService.getInstance().ListOwner(id))
        {
            usernameOwner+=     owner.getUsername();

        }
        for (String t : new String[]{ usernameOwner,"" }) {
            cnt.add(new Label(t));
        }
        for (String t : new String[]{"Participant :" }) {
            cnt.add(new Label(t));
        }
      for (User p : GroupeService.getInstance().ListParticipants(id))
        {
            for (String t : new String[]{ p.getUsername() }) {
                cnt.add(new Label(t));
            }
        }


    }
}