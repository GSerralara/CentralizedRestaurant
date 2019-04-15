package Controller.SubController;

import Controller.MainTableController;
import View.SubView.Bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillController implements ActionListener {
    private MainTableController listener;
    private Bill bill;

    public BillController(MainTableController listener) {
        this.listener = listener;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
        this.listener.setBillController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "PAY":
                listener.giveCommand("PAY", null);
                break;
            default:
                System.err.println("Unknown Button Bill");
                break;
        }
    }
}
