package com.codename1.uikit.pheonixui.Transaction;


import com.codename1.Services.ServiceCart;
import com.codename1.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.entities.Cart;
import com.codename1.entities.Nft;
import com.codename1.ui.*;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.uikit.pheonixui.BaseForm;
import com.codename1.views.Explore;
import java.io.IOException;


public class AjouterPanier extends BaseForm{
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    BaseForm current;
    public AjouterPanier(Form previous, Cart cart) {
        current=this;

        if( ServiceCart.getInstance().getAllNftFromCart().size() == 0) {
            getToolbar().setTitleComponent(
                    FlowLayout.encloseCenterMiddle(
                            new Label("Cart", "Title"),
                            new Label("0", "InboxNumber")
                    )
            );
        }
        else
        {
            getToolbar().setTitleComponent(
                    FlowLayout.encloseCenterMiddle(
                            new Label("Cart", "Title"),
                            new Label(String.valueOf(ServiceCart.getInstance().getAllNftFromCart().size()), "InboxNumber")
                    )
            );
        }
        String[] columnT={"TitleNFT", "Description","Price (BTC)        "};
        Object[][] rowt={};
        DefaultTableModel model =new DefaultTableModel(columnT,rowt);
        float total=0;
        for (Nft nft : ServiceCart.getInstance().getAllNftFromCart()) {
            model.addRow( nft.getTitle(), nft.getDescription(),nft.getPrice() );
            total+=nft.getPrice();
        };

        if(model.getRowCount()==0)
        {
        }else
        {
            model.insertRow(model.getRowCount(),"Total",null,total);
        }
        Table table = new Table(model){
            @Override
            protected Component createCell(Object value, int row, int column, boolean editable) { // (1)
                Component cell;
                cell= super.createCell(value, row, column, editable);
                if(row > -1 && row % 2 == 0) { // (5)
                    // pinstripe effect
                    cell.getAllStyles().setBgColor(0xeeeeee);
                    cell.getAllStyles().setBgTransparency(255);

                }
                return cell;
            }
        };
        add(table);
        for (Nft nft : ServiceCart.getInstance().getAllNftFromCart()) {
            Container container =new Container(new BoxLayout(BoxLayout.X_AXIS));
            setScrollableY(true);
            setScrollVisible(false);
            container.add(nft.getTitle());
            try{
                imgv = new ImageViewer(Image.createImage("/load.png"));
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }

            try{
                enc = EncodedImage.create("/load.png");
            }catch(IOException ex){
                Dialog.show("Error",ex.getMessage(),"ok",null);
            }
            String url = Statics.URL_REP_IMAGES + nft.getImage();
            imgs = URLImage.createToStorage(enc,url,url,URLImage.RESIZE_SCALE);
            imgv.setImage(imgs);
            container.add(imgv);
            Button myButton = new Button();
            myButton.setUIID("container");
            Style myButtonStyle= new Style(myButton.getUnselectedStyle());
            myButtonStyle.setFgColor(0xf21f1f);
            FontImage myButtonImage =FontImage.createMaterial(FontImage.MATERIAL_DELETE,myButtonStyle);
            myButton.setIcon(myButtonImage);
            myButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceCart.getInstance().deleteNftFromCart(nft);
                    System.out.println("supp");
                    System.out.println(ServiceCart.getInstance().getCartClientConnecte().get(0));
                    new AjouterPanier(current,ServiceCart.getInstance().getCartClientConnecte().get(0)).show();
                }
            });
            container.add(myButton);
            container.getAllStyles().getBorder();
            add(container);
        };
        setLayout(BoxLayout.y());
        if (ServiceCart.getInstance().getAllNftFromCart().size() == 0 )
        {
            Button aj=new Button("Fill your cart >> ");
            aj.addActionListener(e -> new Explore(current).show());
            add(aj);
        }
        else
        {

            Button aj2=new Button("  << Fill your cart  ");
            aj2.addActionListener(e -> new Explore(current).show());
            add(aj2);

            Button payer=new Button("PROCEED TO PAYMENT WITH WALLET >>");
            float finalTotal = total;
            payer.addActionListener((e)-> new AjoutTransactionWallet(current, ServiceCart.getInstance().getCartClientConnecte().get(0), finalTotal).show());
            add(payer);
        }

        Form last =  Display.getInstance().getCurrent();
        getToolbar().setBackCommand("", e -> last.show());

    }
}