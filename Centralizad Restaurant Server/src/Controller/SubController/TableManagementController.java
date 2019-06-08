package Controller.SubController;

import Controller.PreServiceController;
import View.SubView.TableMangement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableManagementController implements ActionListener {
    private PreServiceController listener;
    private TableMangement tableMangement;

    public TableManagementController(PreServiceController listener) {
        this.listener = listener;
    }

    public void setTableMangement(TableMangement tableMangement) {
        this.tableMangement = tableMangement;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ADD")){
            listener.addTable(tableMangement.getNumCustomers());
            tableMangement.addItem(""+tableMangement.getNumCustomers());
        }else {
            tableMangement.cancelItem(e.getActionCommand());
            listener.removeTable(e.getActionCommand());
        }
    }
    public void init(){
        tableMangement.init();
    }
    public void addExistingTable(int q){
        tableMangement.addItem(""+q);
    }
}
