package View.SubView;

import Controller.SubController.MenuController;
import Model.Database.Entity.Dish;
import Model.Database.dao.DishDAO;
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
import java.util.LinkedList;

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
        DishDAO dao = new DishDAO();
        LinkedList<Dish> dishes = dao.getAllDishes();
        for(Dish i: dishes){
            SimpleDateFormat df = new SimpleDateFormat("mm:ss");
            menu.add(new MenuItem(df.format(i.getTime()),i.getPrice(),i.getQuantety(),i.getName(),menu.size(),controller));
            items.add(menu.get(menu.size()-1));
        }
        items.revalidate();
        repaint();
    }
    public Dish addItem(){
        int pos = menu.size();
        float price = Float.parseFloat(fieldPrice.getText());
        int units = Integer.parseInt(fieldUnits.getText());
        this.menu.add(new MenuItem(fieldTime.getText(), price,units, fieldName.getText(),pos,controller));
        this.items.add(menu.get(menu.size()-1));
        items.revalidate();
        repaint();
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        Date time = new Date();
        try {
            time = df.parse(fieldTime.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Dish d = new Dish(units,price,fieldName.getText(),time);
        return d;
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

    public Dish getDish(){
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        Date time = new Date();
        try {
            time = df.parse(fieldTime.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String decimal = fieldPrice.getText()+"f";
        System.out.println(decimal);
        float f = Float.parseFloat(fieldPrice.getText());
        Dish d = new Dish(Integer.parseInt(fieldPrice.getText()),Float.parseFloat(decimal),fieldName.getName(),time);
        return d;
    }
}
