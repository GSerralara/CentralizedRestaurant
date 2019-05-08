package View.SubView;

import Controller.SubController.MenuController;
import View.Items.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Menu extends JPanel {
    private JPanel items;
    private ArrayList<MenuItem> menu;
    private MenuController controller;
    private JButton add;
    private TextField fieldTime,fieldPrice,fieldUnits,fieldName;

    public Menu(MenuController controller) {
        this.controller = controller;
        this.controller.setMenu(this);
        setLayout(new BorderLayout());
        items = new JPanel();
        items.setLayout(new BoxLayout(items, BoxLayout.Y_AXIS));
        JScrollPane list = new JScrollPane(items);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(southPart(),BorderLayout.SOUTH);
        add(list, BorderLayout.CENTER);
        init();
    }
    public JPanel southPart(){
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        JPanel up = new JPanel(new FlowLayout());
        JPanel middle = new JPanel(new FlowLayout());
        JPanel down = new JPanel(new FlowLayout());

        fieldName = new TextField(10);
        fieldTime = new TextField(10);
        JLabel nameLabel = new JLabel("Name: ");
        JLabel timeLabel = new JLabel("Time: ");
        up.add(nameLabel);
        up.add(fieldName);
        up.add(timeLabel);
        up.add(fieldTime);
        form.add(up);

        fieldPrice = new TextField(10);
        fieldUnits = new TextField(10);
        JLabel priceLabel = new JLabel("Price: ");
        JLabel unitsLabel = new JLabel("Units: ");
        middle.add(unitsLabel);
        middle.add(fieldUnits);
        middle.add(priceLabel);
        middle.add(fieldPrice);
        form.add(middle);

        add = new JButton("Add Dish");
        add.setActionCommand("ADD");
        add.addActionListener(controller);
        down.add(add);
        form.add(down);

        return form;
    }
    public void init(){
        menu = new ArrayList<>();
        items.removeAll();
        //get add data

        items.revalidate();
        repaint();
    }
    public void addItem(){
        int pos = menu.size();
        float price = Float.parseFloat(fieldPrice.getText());
        int units = Integer.parseInt(fieldUnits.getText());
        this.menu.add(new MenuItem(fieldTime.getText(), price,units, fieldName.getText(),pos,controller));
        this.items.add(menu.get(menu.size()-1));
        items.revalidate();
        repaint();
    }
    public Timestamp getTime(){
        try {
            DateFormat formatter;
            String firstFormat = "HH:mm:ss";
            String secondFormat = "mm:ss";
            String aux = fieldTime.getText();
            int count = aux.length() - aux.replaceAll(":","").length();
            if(count == 2){
                formatter = new SimpleDateFormat(firstFormat);
            }else {
                formatter = new SimpleDateFormat(secondFormat);
            }
            Date date = formatter.parse(fieldTime.getText());
            Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void cancelItem(String pos){
        int index = Integer.parseInt(pos);
        items.remove(index);
        menu.remove(index);
        for(int i =0; i< menu.size();i++){
           menu.get(i).updatePos(i);
        }
        items.revalidate();
        repaint();
    }
}
