package com.codename1.uikit.pheonixui;

import Entities.*;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;


import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import services.GroupeService;
import services.MessageService;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codename1.charts.util.ColorUtil.BLUE;


public class ConversationForm extends BaseForm {
Form current;
    String TabUsername [];
    int i=0;
    public ConversationForm( Conversation c) {
        current = this;

        for (Message message : MessageService.getInstance().ListMsgs(c.getId())) {
            Container MessagesList = new Container(new BorderLayout(BoxLayout.Y_AXIS));
            MultiButton gui_Multi_Button_1 = new MultiButton();


            Button lsupp = new Button("");
            lsupp.setUIID("Container");
            Style supprimerStyle = new Style(lsupp.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf21f1f);
            FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
            lsupp.setIcon(supprimerImage);
            lsupp.getAllStyles().setMarginLeft(450);
            //  lsupp.setTextPosition(RIGHT);
            lsupp.addActionListener((e) -> {
                        Dialog diag = new Dialog("suppression");
                        if (diag.show("suppression", "Vous voulez supprimer ce message?", "Annuler", "Oui")) {
                            diag.dispose();
                        } else {
                            diag.dispose();
                            if (MessageService.getInstance().deleteMsg(message.getId())) {
                                new ConversationForm(c).show();
                            }

                        }
                    }
            );


            String datetime = message.getCreatedAt();
            String date = datetime.substring(0, 10);
            String time = datetime.substring(11, 19);
            //gui_Multi_Button_1.setUIID("Label");
            //MessagesList.getAllStyles().getBorder();

            gui_Multi_Button_1.setName("Multi_Button_1");

            gui_Multi_Button_1.setPropertyValue("line1", "" + date.concat(" " + time));
            gui_Multi_Button_1.setPropertyValue("line2", "" + message.getSender().substring(18, message.getSender().length() - 1));
            gui_Multi_Button_1.setPropertyValue("line3", "" + message.getContenu());
            gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid2", "Label");
            gui_Multi_Button_1.setPropertyValue("uiid3", "Label");

            addComponent(MessagesList);
            MessagesList.getAllStyles().setBgColor(0xeeeeee);
            MessagesList.setName("Container_1");
            if (message.getSender().substring(4, 7).contains("2")) {
                MessagesList.addComponent(BorderLayout.EAST, gui_Multi_Button_1);
                MessagesList.addComponent(BorderLayout.SOUTH, lsupp);
            } else {
                MessagesList.addComponent(BorderLayout.WEST, gui_Multi_Button_1);
            }


        }
        Container addMessageContainer = new Container(new BorderLayout());
        setLayout(BoxLayout.yLast());
        TextField tfMSG = new TextField();
        Stroke borderStroke = new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1);
        tfMSG.getAllStyles().setBorder(RoundBorder.create().
                rectangle(true).
                color(0xffffff).
                strokeColor(0).
                strokeOpacity(50).
                stroke(borderStroke));
        tfMSG.setWidth(20);
        //tfMSG.getAllStyles().setPaddingRight(30);
        Button lSendMSG = new Button("");
        lSendMSG.setUIID("NewsTopLine");
        Style SendMSGStyle = new Style(lSendMSG.getUnselectedStyle());
        SendMSGStyle.setFgColor(BLUE);
        FontImage SendMSGImage = FontImage.createMaterial(FontImage.MATERIAL_SEND, SendMSGStyle);
        lSendMSG.setIcon(SendMSGImage);
        lSendMSG.setTextPosition(RIGHT);
        lSendMSG.addActionListener((e) -> {
            Message msg = new Message(tfMSG.getText().toString());
            if ((tfMSG.getText().equals(""))) {
                Dialog.show("Alert", "Please send a message", new Command("OK"));
            } else if (MessageService.getInstance().SendMessage(msg, c.getId())) {
                new ConversationForm(c).show();
                // Dialog.show("Success", "message est envoyée", new Command("OK"));

            } else {
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
        });

        add(addMessageContainer);
        addMessageContainer.addComponent(BorderLayout.WEST, tfMSG);
        addMessageContainer.addComponent(BorderLayout.EAST, lSendMSG);

        if (c instanceof GroupChat) {
            getToolbar().setTitleComponent(
                    FlowLayout.encloseCenterMiddle(
                            new Label(c.getNom(), "Title")
                            //new Label("19", "InboxNumber")
                    )
            );
            System.out.println(((GroupChat) c).getOwner().substring(4,7));
            if (((GroupChat) c).getOwner().substring(4,7).contains("2")) {
                System.out.println(((GroupChat) c).getOwner());
                getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_EDIT, e -> {

                   new UpdateGroupForm(c).show();
                });

            }
                    getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PEOPLE, e -> {

                        MySheet sheet = new MySheet(null, c.getId());

                        sheet.show();
                    });

            } else {

                PrivateChat p = (PrivateChat) c;
                String idReceived = p.getReceived().substring(4, 7);
                String idSender = p.getSender().substring(4, 7);
                System.out.println(idReceived);
                System.out.println(idSender);
                if (idReceived.contains("2")) {
                    getToolbar().setTitleComponent(
                            FlowLayout.encloseCenterMiddle(
                                    new Label(p.getSender().substring(18, p.getSender().length() - 1) + "", "Title")
                                    //new Label("19", "InboxNumber")
                            )
                    );
                } else {
                    getToolbar().setTitleComponent(
                            FlowLayout.encloseCenterMiddle(
                                    new Label(p.getReceived().substring(18, p.getReceived().length() - 1) + "", "Title")
                                    //new Label("19", "InboxNumber")
                            )
                    );
                }

            }

            getToolbar().setBackCommand("", e -> new InboxForm().show());

        }
    }
