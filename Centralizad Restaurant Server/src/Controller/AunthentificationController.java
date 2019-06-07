package Controller;

import Model.Database.Entity.User;
import View.Aunthentification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AunthentificationController implements ActionListener {
    private Aunthentification aunthentification;
    private FormController listener;
    private LinkedList<User> accepted;

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
            accepted.add(aunthentification.getUser(e.getActionCommand()));
            listener.acceptedReserve(aunthentification.getUser(e.getActionCommand()));
            this.listener.changeService();
            aunthentification.cancelItem(e.getActionCommand());
        }else {
            aunthentification.cancelItem(e.getActionCommand());
        }
    }
    public void dropReserve(User user){
        aunthentification.dropUser(user);
        accepted.remove(user);
    }
    public void addAuth(User user){
        aunthentification.addItem(user);
    }
    public String getIfWasAccepted(User user){
        for(User i:accepted){
            if(user.getUser().equals(i.getUser())){
                return "YES";
            }
        }
        if(aunthentification.userIsOnWainting(user)) return "UNKNOWN";
        return "NO";
    }
}
