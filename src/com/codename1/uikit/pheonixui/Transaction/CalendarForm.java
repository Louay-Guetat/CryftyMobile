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
package com.codename1.uikit.pheonixui.Transaction;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.uikit.pheonixui.BaseForm;


/**
 * GUI builder created Form
 *
 * @author shai
 */
public class CalendarForm extends BaseForm {

    public CalendarForm() {
        this(Resources.getGlobalResources());
    }
    
    public CalendarForm(Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Card", "Title")
                        //new Label("20", "InboxNumber")
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
    private Container gui_Container_1 = new Container(new BorderLayout());

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {

        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");
        Label cardImage = new Label(resourceObjectInstance.getImage("card.png"), "");
        addComponent(gui_Container_1);



        TextField name = new TextField("", "", 20, TextArea.ANY);
        name.setUIID("NewCardField");

        TextField month = new TextField("", "Month", 20, TextArea.NUMERIC);
        month.setUIID("NewCardField");
        month.getHintLabel().setUIID("NewCardFieldHint");

        TextField year = new TextField("", "Year", 20, TextArea.NUMERIC);
        year.setUIID("NewCardField");
        year.getHintLabel().setUIID("NewCardFieldHint");

        TextField num1 = new TextField("", "1234", 4, TextArea.NUMERIC);
        num1.setUIID("NewCardField");
        num1.getHintLabel().setUIID("NewCardFieldHint");
        TextField num2 = new TextField("", "1234", 4, TextArea.NUMERIC);
        num2.setUIID("NewCardField");
        num2.getHintLabel().setUIID("NewCardFieldHint");
        TextField num3 = new TextField("", "1234", 4, TextArea.NUMERIC);
        num3.setUIID("NewCardField");
        num3.getHintLabel().setUIID("NewCardFieldHint");
        TextField num4 = new TextField("", "1234", 4, TextArea.NUMERIC);
        num4.setUIID("NewCardField");
        num4.getHintLabel().setUIID("NewCardFieldHint");

        num1.addDataChangedListener((i, ii) -> {
            if(num1.getText().length() == 4) {
                num1.stopEditing(()->{
                    num2.startEditing();
                });
            }
        });

        num2.addDataChangedListener((i, ii) -> {
            if(num2.getText().length() == 4) {
                num2.stopEditing(num3::startEditing);
            }
        });
        num3.addDataChangedListener((i, ii) -> {
            if(num3.getText().length() == 4) {
                num3.stopEditing(num4::startEditing);
            }
        });
        num4.addDataChangedListener((i, ii) -> {
            if(num4.getText().length() == 4) {
                num4.stopEditing();
            }
        });

        Validator validator = new Validator();
        validator.addConstraint(name, new LengthConstraint(1));
        Constraint monthConstraint = new Constraint(){
            @Override
            public boolean isValid(Object value) {
                if (value instanceof String){
                    String text = (String) value;
                    try{
                        return (Integer.valueOf(text) < 13 && Integer.valueOf(text) > 0);
                    }catch(NumberFormatException e){
                        return false;
                    }
                }
                return false;
            }
            @Override
            public String getDefaultFailMessage() {
                return null;
            }
        };
        validator.addConstraint(month, monthConstraint);
        Constraint yearConstraint = new Constraint(){
            @Override
            public boolean isValid(Object value) {
                if (value instanceof String){
                    String text = (String) value;
                    try{
                        return (Integer.valueOf(text) > 2000 && Integer.valueOf(text) < 2100);
                    }catch(NumberFormatException e){
                        return false;
                    }
                }
                return false;
            }
            @Override
            public String getDefaultFailMessage() {
                return null;
            }
        };
        validator.addConstraint(year, yearConstraint);
        Constraint creditCardFieldConstraint = new Constraint(){
            @Override
            public boolean isValid(Object value) {
                if (value instanceof String){
                    String text = (String) value;
                    return (text.length() == 4);
                }
                return false;
            }
            @Override
            public String getDefaultFailMessage() {
                return null;
            }
        };
        validator.addConstraint(num1, creditCardFieldConstraint);
        validator.addConstraint(num2, creditCardFieldConstraint);
        validator.addConstraint(num3, creditCardFieldConstraint);
        validator.addConstraint(num4, creditCardFieldConstraint);
        Container wrapper = new Container(new BorderLayout());
        wrapper.add(BorderLayout.CENTER, BoxLayout.encloseY(
                new Label(resourceObjectInstance.getImage("card.jpg"), "AddImageCard"),
                new Label("Cardholder Name", "AddNewCardExplanation"),
                name,
                new Label("Credit Card Number", "AddNewCardExplanation"),
                GridLayout.encloseIn(4, num1, num2, num3, num4),
                new Label("Expiration Date", "AddNewCardExplanation"),
                GridLayout.encloseIn(2, month, year)
        ));

        Button saveCard = new Button("SAVE CARD", "SaveCardConfirmButton");
        validator.addSubmitButtons(saveCard);
        /*saveCard.addActionListener(evt -> {
            evt.consume();
            Entity creditCard = new CreditCardModel(name.getText(), num1.getText() + num2.getText() +num3.getText() +num4.getText(), year.getText(), month.getText());

            ActionNode action = viewNode.getInheritedAction(AddCreditCardView.FINISH_CREATING_CARD);
            if (action != null) {
                action.fireEvent(creditCard, AddCreditCardView.this);
            }
        });*/

        wrapper.add(BorderLayout.SOUTH, saveCard);
        add(wrapper);


        //gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER,walletList);
    }

}



    

