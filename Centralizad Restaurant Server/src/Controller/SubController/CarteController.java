package Controller.SubController;

import Controller.PreServiceController;
import Resources.Pop;
import View.SubView.Carte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarteController implements ActionListener {
    private Carte carte;
    private PreServiceController listener;

    public CarteController(PreServiceController listener) {
        this.listener = listener;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

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
    public void init(){
        carte.init();
    }
    public void addMenu(String name){
        carte.addCarteOption(name);
    }
}
