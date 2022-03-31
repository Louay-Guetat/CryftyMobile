package com.codename1.uikit.pheonixui;

import com.codename1.Services.servicesblogs;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import com.mycomany.entites.BlogArticle;

import java.util.ArrayList;

public class BOblogsForm extends BaseForm {
    public BOblogsForm(Resources res) {
        Form hi = new Form("Table", new BorderLayout());

        ArrayList<BlogArticle> list = servicesblogs.getInstance().listarticle();
        Object[][] rows = new Object[list.size()][];

        for(int iter = 0 ; iter < rows.length ; iter++) {
            rows[iter] = new Object[] {
                    list.get(iter).getTitle(), list.get(iter).getContents(), list.get(iter).getCategory(), list.get(iter).getDate(), list.get(iter).getAuthor()
            };
        }

        TableModel model = new DefaultTableModel(new String[]{"Title", "contents", "category", "date", "author"}, rows);
        Table table = new Table(model){

          /*  @Override
            protected Component createCell(Object value, int rows, int column, boolean editable) {
                Component cell;*/
              /*  if(column == 2) {
                    Picker p = new Picker();
                    p.setType(Display.PICKER_TYPE_STRINGS);
                    p.setStrings("Verified", "Not Verified");
                    p.setSelectedString((String)value);
                    p.setUIID("TableCell");
                    p.addActionListener((e) -> getModel().setValueAt(rows, column, p.getSelectedString()));


                    cell = p;
                } else {
                    cell = super.createCell(value, rows, column, editable);
                }*/
               /* if(rows == -1) {
                    // pinstripe effect
                    cell.getAllStyles().setBgColor(0xeeeeee);
                    cell.getAllStyles().setBgTransparency(255);
                }
                return cell;
            }*/
          @Override
          protected Component createCell(Object value, int rows, int column, boolean editable) {
              Component cell;
              cell = super.createCell(value, rows, column, editable);
              cell.getAllStyles().setBgColor(0xeeeeee);
              cell.getAllStyles().setBgTransparency(255);
              return cell;
          }

            @Override
            protected TableLayout.Constraint createCellConstraint(Object value, int row, int column) {
                TableLayout.Constraint con =  super.createCellConstraint(value, row, column);

              //  if(row == rows.length-1 ) {
                //    con.setVerticalSpan(2);
                //}
                return con;
            }
        };
        Button toggle = new Button("");
        toggle.setUIID("CenterWhite");
        FontImage.setMaterialIcon(toggle, FontImage.MATERIAL_BOOKMARK);
        toggle.getAllStyles().setMargin(0, 0, 0, 0);
        toggle.getAllStyles().setBorder(RoundBorder.create().
                rectangle(true).
                color(0x9b4c3f));
        Button placeholder = new Button("");
        placeholder.setUIID("CenterWhite");
        Container buttonGrid = GridLayout.encloseIn(2, toggle, placeholder);
        Label leftLabel = new Label("", "CenterWhite");
        FontImage.setMaterialIcon(leftLabel, FontImage.MATERIAL_BOOKMARK);
        Label rightLabel = new Label("", "CenterWhite");
        FontImage.setMaterialIcon(rightLabel, FontImage.MATERIAL_DATA_SAVER_OFF);
        Container labelGrid = GridLayout.encloseIn(2, leftLabel, rightLabel);
        labelGrid.getAllStyles().setBorder(RoundBorder.create().
                rectangle(true).
                color(0xd27d61));

        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        LayeredLayout.encloseIn(labelGrid, buttonGrid)
                )
        );
        ActionListener al = e -> {
            if(buttonGrid.getComponentAt(0) == toggle) {
                toggle.remove();
                buttonGrid.add(toggle);
                buttonGrid.animateLayoutAndWait(150);
                FontImage.setMaterialIcon(toggle, FontImage.MATERIAL_DATA_SAVER_OFF);
                new TrendingBO(Resources.getGlobalResources()).show();
            } else {
                placeholder.remove();
                buttonGrid.add(placeholder);
                buttonGrid.animateLayoutAndWait(150);
                FontImage.setMaterialIcon(toggle, FontImage.MATERIAL_BOOKMARK);
               hi.show();
            }
        };
        toggle.addActionListener(al);
        placeholder.addActionListener(al);



        table.setScrollableX(true);
        hi.add(BorderLayout.CENTER,table);

        hi.show();
    }

    public void show() {
    }
}