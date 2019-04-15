package View.SubView;



import Controller.SubController.BillController;

import javax.swing.*;
import java.awt.*;

public class Bill extends JPanel {
    private BillController controller;
    private JButton pay;

    public Bill(BillController controller) {

        this.controller = controller;
        this.controller.setBill(this);
        setLayout(new BorderLayout());
        pay = new JButton("Pay Bill");
        pay.setActionCommand("PAY");
        pay.addActionListener(controller);
        add(pay,BorderLayout.SOUTH);
    }

}
