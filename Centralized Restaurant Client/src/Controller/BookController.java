package Controller;

import View.Book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class BookController implements ActionListener {

    private boolean serviceState = true;
    private Book book;
    private FormController listener;


    public BookController(FormController listener) {
        this.listener = listener;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "BACK":
                book.goToWindow(e.getActionCommand());
                break;
            case "BOOK":
                listener.sendReserve(book.getReserveName());
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
    public void reserveState(){
        serviceState = listener.askForReserveState("STATE");
    }
    public void cancelReserve(){
        serviceState = false;

    }
}
