package View.SubView;



import Controller.SubController.BillController;
import Model.Database.Entity.Dish;
import View.Items.BilledItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bill extends JPanel {


    private ArrayList<BilledItem> items;
    private JPanel view;

    public Bill(BillController controller) {

        controller.setBill(this);
        setLayout(new BorderLayout());
        JButton pay = new JButton("Pay Bill");
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
        view.add(items.get(items.size()-1));
        view.revalidate();
        repaint();
    }


}
