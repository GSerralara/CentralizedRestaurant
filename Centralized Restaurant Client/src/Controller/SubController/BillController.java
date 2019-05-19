package Controller.SubController;

import Controller.MainTableController;
import Model.Database.Entity.Dish;
import View.SubView.Bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

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
    public void addToPay(LinkedList<Boolean> bools, LinkedList<Dish> order){
        for(int i=0; i<order.size();i++){
            if(bools.get(i)){
                bill.addBill(order.get(i));
            }
        }
    }
}
