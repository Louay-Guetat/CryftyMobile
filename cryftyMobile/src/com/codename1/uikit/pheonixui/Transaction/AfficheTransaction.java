package com.codename1.uikit.pheonixui.Transaction;
import com.codename1.components.SpanLabel;
import com.codename1.Services.ServiceTransaction;
import com.codename1.entities.Nft;
import com.codename1.entities.Transaction;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.uikit.pheonixui.InboxForm;
import com.codename1.uikit.pheonixui.WalletsForm;

import java.util.ArrayList;
import java.util.Date;


public class AfficheTransaction extends BaseForm{
    public AfficheTransaction(Form previous,float total) {
        setTitle("Transaction");
        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceTransaction.getInstance().getAllTransaction().toString());
        //add(sp);
        ArrayList<Transaction> tr=ServiceTransaction.getInstance().getAllTransaction();
        String[] columnT={"Réf", "AddressWallet","Montant"};
        Object[][] rowt={};
        DefaultTableModel model =new DefaultTableModel(columnT,rowt);
        for(Transaction t:tr)
        {
            // total à modifier t.getCart2().getTotal()
            model.addRow( t.getId(), t.getWallet().substring(23),total);
        }
        Table table = new Table(model);
        table.getAllStyles().setBgColor(0xeeeeee);
        add(table);
        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> new WalletsForm().show());
    }
}