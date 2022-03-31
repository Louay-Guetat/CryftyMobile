package com.codename1.uikit.pheonixui.Transaction;

import com.codename1.entities.Cart;
import com.codename1.entities.Nft;
import com.codename1.entities.Transaction;
import com.codename1.entities.Wallets;
import com.codename1.Services.ServiceTransaction;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.ui.util.Resources;

import java.util.ArrayList;
import java.util.Iterator;


public class AjoutTransactionWallet extends BaseForm {
    BaseForm current;
    public AjoutTransactionWallet(Form previous,Cart idCart,float total) {
        current=this;
        setTitle("Transaction Wallet");
        setLayout(BoxLayout.y());
        Label cardImage = new Label(Resources.getGlobalResources().getImage("wallet.png"), "");
        add(cardImage);
        ComboBox c=new ComboBox();
        Label l=new Label("Address wallet :", "CenterLabel");
        l.setWidth(500);
        Button payer= new Button("Payer");
        payer.getAllStyles();
        ArrayList<Wallets> wallets=ServiceTransaction.getInstance().getAllTransactionWallet();
        String w="null";
        for(Wallets wallet: wallets)
        {
            w=wallet.toString();
            c.addItem(w);
        }
        System.out.println(idCart.toString());
        payer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {

                    Transaction t = new Transaction( c.getSelectedItem().toString().substring(35),idCart.toString().substring(8,9));
                    if( ServiceTransaction.getInstance().addTransaction(t,total))
                    {
                        Dialog.show("Success","Paiement réussi ",new Command("OK"));
                        new AfficheTransaction(current,total).show();
                        for (Iterator<Nft> iterator = cart.getNftProd().iterator(); iterator.hasNext();) {
                            Nft nft = iterator.next();
                            iterator.remove();
                        }
                    }else
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                } catch (NumberFormatException e) {

                }
            }
        });

        addAll(l,c,payer);
        Form last = Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());

    }



}