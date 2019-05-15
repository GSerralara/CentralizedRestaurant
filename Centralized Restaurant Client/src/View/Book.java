package View;

import Controller.BookController;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Book extends JPanel {
    // instance variables
    private final ProgressListener listener;
    private BookController controller;
    /**
     * Attributes of the UI
     */
    private JTextField reserveName;
    private JLabel reserveState;
    private JButton back,book;
    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    public Book(ProgressListener listener, BookController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        this.controller.setBook(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    public void windowConfiguration(){
        // We configure the window.
        new BorderLayout();
        // We instance the title of the window
        JLabel title = new JLabel("Make your booking");
        JLabel bookLabel = new JLabel("Introduce your Reserve name");
        JLabel reserveStateLabel = new JLabel("Your Reserve has been:");
        // We instance the panel body of the window
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        // We create the different parts of the window.
        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(bookLabel);
        reserveName = new JTextField();
        content.add(reserveName);
        content.add(reserveStateLabel);
        book = new JButton("BOOK");
        book.setActionCommand("BOOK");
        book.addActionListener(controller);
        content.add(book);
        content.add(Box.createVerticalStrut(20));
        reserveState = new JLabel("...");
        reserveState.setSize(150,150);
        reserveState.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(reserveState);
        back = new JButton("Go back");
        back.setActionCommand("BACK");
        back.addActionListener(controller);
        content.add(back);
        add(content,BorderLayout.CENTER);
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
