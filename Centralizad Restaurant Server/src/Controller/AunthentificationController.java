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
            accepted.add(aunthentification.getReserve(e.getActionCommand()));
            System.out.println(accepted.get(accepted.size()-1).getReserveName()+" added");
            System.out.println("id table"+ aunthentification.getTable(e.getActionCommand()));
            System.out.println("Tenmos "+accepted.size()+" clientes reservados");
            listener.acceptedReserve(aunthentification.getReserve(e.getActionCommand()),aunthentification.getTable(e.getActionCommand()));
            this.listener.changeService();
            listener.updateReserves(accepted);
            aunthentification.cancelItem(e.getActionCommand());
        }else {
            aunthentification.cancelItem(e.getActionCommand());
        }
    }
    public void dropReserve(User user){
        for(Reserve i: accepted){
            if(i.getUser().getUser().equals(user.getUser())){
                System.out.println("DROPING RESERVE");
                aunthentification.dropUser(i.getUser());
                accepted.remove(i);
            }
        }
        if(accepted.size()==0){
            this.listener.changeService();
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
