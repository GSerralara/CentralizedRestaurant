package Controller.SubController;

import Controller.PreServiceController;
import View.SubView.TableMangement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class
 */
public class TableManagementController implements ActionListener {
    private PreServiceController listener;
    private TableMangement tableMangement;

    /**
     * * Constructor by default of the class.
     * @param listener it's a PreServiceController that the class will use to move to other views
     */

    public TableManagementController(PreServiceController listener) {
        this.listener = listener;
    }

    /**
     * setter of the view
     * @param tableMangement view
     */
    public void setTableMangement(TableMangement tableMangement) {
        this.tableMangement = tableMangement;
    }


    /**
     * This method override the method ActionPerformed.
     * @param e it's a parameter the type of ActionEvent.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("ADD")){
            listener.addTable(tableMangement.getNumCustomers());
            tableMangement.addItem(""+tableMangement.getNumCustomers(),this);
        }else {
            tableMangement.cancelItem(e.getActionCommand());
            listener.removeTable(e.getActionCommand());
        }
    }

    /**
     * Function that start the table management.
     */
    public void init(){
        tableMangement.init();
    }

    /**
     * adds a existing table to the view
     * @param q quantety
     */
    public void addExistingTable(int q){
        tableMangement.addItem(""+q,this);
    }
}
