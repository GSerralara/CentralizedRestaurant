package Controller;

import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;
import View.Aunthentification;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * AunthentificationController class
 */
public class AunthentificationController implements ActionListener {
    private Aunthentification aunthentification;
    private FormController listener;
    private LinkedList<Reserve> accepted;

    /**
     * default constructor
     * @param listener controller
     */
    public AunthentificationController(FormController listener) {
        this.listener = listener;
        accepted = new LinkedList<>();
    }

    /**
     * view setter
     * @param aunthentification view
     */
    public void setAunthentification(Aunthentification aunthentification) {
        this.aunthentification = aunthentification;
    }

    /**
     * override function
     * @param e event
     */
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

    /**
     * drop reserve
     * @param user user
     */
    public void dropReserve(User user){
        int j = 0;

        if(accepted.size()!=0){
            for(Reserve i: accepted){
                System.out.println("DROPING "+i.getReserveName()+" "+user.getUser());
                if(i.getUser().getUser().equals(user.getUser())){
                    System.out.println("DROPING RESERVE" +j);
                    aunthentification.dropUser(i.getUser());
                    accepted.remove(i);
                    aunthentification.removeAccept(j);
                }
                j++;
            }
        }else{
            System.out.println("Accepted size "+accepted.size());
            aunthentification.dropUser(user);

        }

        aunthentification.redraw();
    }

    /**
     * add auth item
     * @param user reserve
     */
    public void addAuth(Reserve user){
        aunthentification.addItem(user, listener.getTables(),this);
    }

    /**
     * getter of auth state
     * @param user user
     * @return string
     */
    public String getIfWasAccepted(User user){
        for(Reserve i:accepted){
            if(user.getUser().equals(i.getUser().getUser())){
                return "YES";
            }
        }
        if(aunthentification.userIsOnWainting(user)) return "UNKNOWN";
        return "NO";
    }

    /**
     * getter accepted
     * @return list
     */
    public LinkedList<Reserve> getAccepted() {
        return accepted;
    }
}
