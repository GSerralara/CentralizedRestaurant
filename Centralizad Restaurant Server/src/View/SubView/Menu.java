package View.SubView;

import Controller.SubController.MenuController;
import Model.Database.Entity.Dish;
import Model.Database.dao.DishDAO;
import View.Items.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Menu extends JPanel {
    private JPanel items;
    private ArrayList<MenuItem> menu;
    private MenuController controller;
    private JButton add;
    private TextField fieldName;
    private JSpinner fieldUnits, fieldTime, fieldPrice;

    public Menu(MenuController controller) {
        this.controller = controller;
        this.controller.setMenu(this);
        menu = new ArrayList<>();
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
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);

        Date startTime = cal.getTime();
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date endTime = cal.getTime();

        //default value,lower bound,upper bound,increment by
        SpinnerModel smUnits = new SpinnerNumberModel(1, 1, 999, 1);
        SpinnerModel smPrice = new SpinnerNumberModel(1.0, 1.0, 999.99, 0.1);
        SpinnerDateModel model = new SpinnerDateModel(startTime, null, endTime, Calendar.SECOND);
        fieldName = new TextField(10);
        fieldTime = new JSpinner(model);
        fieldTime.setEditor(new JSpinner.DateEditor(fieldTime, "mm:ss"));
        fieldTime.setValue(startTime);
        JLabel nameLabel = new JLabel("Name: ");
        JLabel timeLabel = new JLabel("Time: ");
        up.add(nameLabel);
        up.add(fieldName);
        up.add(timeLabel);
        up.add(fieldTime);
        form.add(up);

        fieldPrice = new JSpinner(smPrice);
        fieldUnits = new JSpinner(smUnits);
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
    public boolean isNameIntroduced(){
        return !fieldName.getText().equals("");
    }
    public void init(){
        items.removeAll();
        //get add data
        /*
        DishDAO dao = new DishDAO();
        LinkedList<Dish> dishes = dao.getAllDishes();
        for(Dish i: dishes){
            SimpleDateFormat df = new SimpleDateFormat("mm:ss");
            menu.add(new MenuItem(df.format(i.getTime()),i.getPrice(),i.getQuantety(),i.getName(),menu.size(),controller));
            items.add(menu.get(menu.size()-1));
        }
        */
        items.revalidate();
        repaint();
    }
    public Dish addItem(){
        int pos = menu.size();
        BigDecimal number = new BigDecimal(fieldPrice.getValue().toString());
        float price = number.floatValue();
        int units = (int)fieldUnits.getValue();
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        Date date = (Date)fieldTime.getValue();
        String strTime = df.format(date);
        this.menu.add(new MenuItem(strTime,  price,units, fieldName.getText(),pos,controller));
        this.items.add(menu.get(menu.size()-1));
        items.revalidate();
        repaint();

        Date time = new Date();
        try {
            time = df.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Dish d = new Dish(units,price,fieldName.getText(),time);
        return d;
    }
    public void addDish(Dish d){
        int pos = menu.size();
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        Date date = d.getTime();
        float price = d.getPrice();
        int units = d.getQuantety();
        String strTime = df.format(date);
        this.menu.add(new MenuItem(strTime,price,units, fieldName.getText(),pos,controller));
        this.items.add(menu.get(menu.size()-1));
        items.revalidate();
        repaint();
    }
    public void enableButton(Boolean set){
        add.setEnabled(set);
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
            String strTime = (String) fieldTime.getValue();
            time = df.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        float f = (float)fieldPrice.getValue();
        Dish d = new Dish((int)fieldUnits.getValue(),f,fieldName.getName(),time);
        return d;
    }
}
