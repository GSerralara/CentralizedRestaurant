package Controller;

import Model.Database.Entity.Reserve;
import View.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * BookController class
 */
public class BookController implements ActionListener {

    private boolean serviceState = true;
    private Book book;
    private FormController listener;

    /**
     * Default constructor
     * @param listener controller
     */
    BookController(FormController listener) {
        this.listener = listener;
    }

    /**
     * view setter
     * @param book view
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * override function
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "BACK":
                book.goToWindow(e.getActionCommand());
                break;
            case "BOOK":

                Reserve reserve = new Reserve(listener.getuser(),book.getNumberBook(),book.getReserveName());
                System.out.println(reserve.getUser().getUser()+" "+reserve.getReserveName()+" "+reserve.getBookNumber());
                listener.sendReserve(reserve);
                serviceState = true;
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if(serviceState){
                            reserveState();
                        }else{
                            this.cancel();
                        }
                    }
                };
                timer.schedule(task,0,10000);
                break;
            default:
                System.err.println("Unknown window name Book");
                break;
        }
    }

    /**
     * reserveState asker
     */
    private void reserveState(){
        serviceState = listener.askForReserveState("STATE");
    }

    /**
     * cancel reserve
     */
    void cancelReserve(){
        serviceState = false;

    }
}
