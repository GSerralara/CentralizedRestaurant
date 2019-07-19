package Controller;

import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import View.Aunthentification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AunthentificationController implements ActionListener {
    private Aunthentification aunthentification;
    private FormController listener;
    private LinkedList<Reserve> accepted;

    public AunthentificationController(FormController listener) {
        this.listener = listener;
        accepted = new LinkedList<>();
    }

    public void setAunthentification(Aunthentification aunthentification) {
        this.aunthentification = aunthentification;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] op = e.getActionCommand().split(":");
        if(e.getActionCommand().charAt(0) == 'A'){
            listener.acceptedReserve(aunthentification.getReserve(e.getActionCommand()),aunthentification.getTable(e.getActionCommand()));
            this.listener.changeService();
            accepted.add(aunthentification.getReserve(e.getActionCommand()));
            aunthentification.cancelItem(e.getActionCommand());
        }else {
            aunthentification.cancelItem(e.getActionCommand());
        }
    }
    public void dropReserve(User user){
        for(Reserve i: accepted){
            System.out.println("DROPING "+i.getReserveName()+" "+user.getUser());
            if(i.getUser().getUser().equals(user.getUser())){
                System.out.println("DROPING RESERVE");
                aunthentification.dropUser(i.getUser());
                accepted.remove(i);
            }
        }

    }
    public void addAuth(Reserve user){
        aunthentification.addItem(user, listener.getTables());
    }
    public String getIfWasAccepted(User user){
        for(Reserve i:accepted){
            if(user.getUser().equals(i.getUser().getUser())){
                return "YES";
            }
        }
        if(aunthentification.userIsOnWainting(user)) return "UNKNOWN";
        return "NO";
    }

    public LinkedList<Reserve> getAccepted() {
        return accepted;
    }
}
