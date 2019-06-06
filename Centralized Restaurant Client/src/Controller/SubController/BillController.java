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
    LinkedList<Boolean> alreadyOnBill;

    public BillController(MainTableController listener) {
        this.listener = listener;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
        this.listener.setBillController(this);
        alreadyOnBill = new LinkedList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "PAY":
                listener.changeSendRequest();
                listener.giveCommand("PAY", null);
                break;
            default:
                System.err.println("Unknown Button Bill");
                break;
        }
    }
    public void addToPay(LinkedList<Boolean> bools, LinkedList<Dish> order){
        if(bools.size()>alreadyOnBill.size()){
            for(int i=alreadyOnBill.size(); i<bools.size();i++){
                alreadyOnBill.add(false);
            }
        }
        for(int i=0; i<bools.size();i++){
            if(bools.get(i) && !alreadyOnBill.get(i)){
                bill.addBill(order.get(i));
                alreadyOnBill.set(i,true);
            }
        }
    }
}
