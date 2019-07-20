package Controller.SubController;

import Controller.PreServiceController;
import Resources.Pop;
import View.SubView.Carte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CarteController class
 */
public class CarteController implements ActionListener {
    private Carte carte;
    private PreServiceController listener;

    /**
     * Default constructor
     * @param listener controller
     */
    public CarteController(PreServiceController listener) {
        this.listener = listener;
    }

    /**
     * view setter
     * @param carte view
     */
    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    /**
     * override function
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOAD":
                if(carte.getCarteListSize() == 0){
                    Pop pop = new Pop("You don't have any carte created, try to create one");
                }else{
                    listener.loadMenu(carte.getSelectedMenu());
                }
                break;
            case "SAVE":
                if(carte.isNameIntroduced()){
                    listener.createMenu(carte.getCarteName());
                    carte.addCarteOption(carte.getCarteName());
                }else {
                    Pop pop = new Pop("At least introduce a dish name");
                }
                break;
        }
    }

    /**
     * init
     */
    public void init(){
        carte.init();
    }

    /**
     * add a menu
     * @param name of menu
     */
    public void addMenu(String name){
        carte.addCarteOption(name);
    }
}
