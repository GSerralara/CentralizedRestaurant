package View;

import Controller.BookController;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Book extends JPanel {
    private final ProgressListener listener;
    private BookController controller;
    private JTextField reserveName;
    private JLabel reserveState;
    private JButton back;
    public Book(ProgressListener listener, BookController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setBook(this);
        windowConfiguration();
    }
    public void windowConfiguration(){
// We configure the window.
        new BorderLayout();
        // We instance the title of the window
        JLabel title = new JLabel("Make your booking");
        JLabel bookLabel = new JLabel("Introduce your Reserve name");
        JLabel reserveStateLabel = new JLabel("Your Reserve has been:");
        // We instance the panel body of the window
        JPanel content = new JPanel(new BorderLayout());
        // We create the different parts of the window.
        content.add(title,BorderLayout.NORTH);
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        form.add(bookLabel);
        reserveName = new JTextField();
        form.add(reserveName);
        form.add(reserveStateLabel);
        reserveState = new JLabel("...");
        reserveState.setBorder(BorderFactory.createLineBorder(Color.black));
        form.add(reserveState);
        content.add(form,BorderLayout.CENTER);
        add(title,BorderLayout.NORTH);
        add(content,BorderLayout.CENTER);
        back = new JButton("Go back");
        add(back, BorderLayout.SOUTH);
        back.setActionCommand("BACK");
        back.addActionListener(controller);
    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */
    public void goToWindow(String windowName){
        // List of available views from this one
        switch (windowName){
            case "BACK":
                this.listener.progressFrom(ProgressListener.AppState.MENU);
                break;
            default:
                System.err.println("Unknown window name Book");
                break;
        }
    }



}
