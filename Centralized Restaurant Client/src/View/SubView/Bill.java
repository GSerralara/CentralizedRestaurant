package View.SubView;



import Controller.SubController.BillController;
import Model.Database.Entity.Dish;
import View.Items.BilledItem;

import javax.swing.*;
import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

public class Bill extends JPanel {
    private BillController controller;
    private JButton pay;
    private ArrayList<BilledItem> items;
    private JPanel view;

    public Bill(BillController controller) {

        this.controller = controller;
        this.controller.setBill(this);
        setLayout(new BorderLayout());
        pay = new JButton("Pay Bill");
        pay.setActionCommand("PAY");
        pay.addActionListener(controller);
        add(pay,BorderLayout.SOUTH);
        view = new JPanel();
        view.setLayout(new BoxLayout(view,BoxLayout.Y_AXIS));
        JScrollPane list = new JScrollPane(view);
        list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(list,BorderLayout.CENTER);
        view.removeAll();
        items = new ArrayList<>();
        view.revalidate();
        repaint();
    }
    public void addBill(Dish d){
        items.add(new BilledItem(d));
        view.add(items.get(items.size()));
        view.revalidate();
        repaint();
    }


}
