package View;

import Controller.MainMenuController;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private final ProgressListener listener;
    private MainMenuController controller;
    private JButton cancelBooking,book,logout;
    // Name of the view
    private static final String WINDOW_TITLE = "WELCOME";

    public MainMenu(ProgressListener listener, MainMenuController controller) {
        this.listener = listener;
        this.controller = controller;
        this.controller.setMainMenu(this);
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        new BorderLayout();
        // We instance the title of the window
        JLabel title = new JLabel(WINDOW_TITLE);
        // We instance the panel body of the window
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        // We create the different parts of the window.
        content.add(title,Box.createHorizontalBox());
        content.add(Box.createVerticalStrut(20));
        content.add(mainPart());
        content.add(Box.createVerticalStrut(20));
        content.add(bottomPart());
        // We add the content to the window.
        add(content,BorderLayout.CENTER);
    }
    /**
     * Function that will return a JPanel.
     * It's responsible for creating the components of the center of the Layout.
     */
    private JPanel mainPart(){
        // instance JPanels that will from the main panel and then returned
        JPanel form = new JPanel();
        // set Layout to a box Form for the content
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        // instance labels of the view
        JLabel bookLabel = new JLabel("Book a Table");
        JLabel cancelLabel = new JLabel("Cancel your Reserve");
        // instance attributes
        book = new JButton("Book");
        cancelBooking = new JButton("Cancel");
        // add user camp to the from
        form.add(bookLabel);
        form.add(book);
        form.add(Box.createVerticalStrut(20));
        // add password camp to the fom
        form.add(cancelLabel);
        form.add(cancelBooking);
        form.add(Box.createVerticalStrut(20));
        // add Sing In button to the bottom
        book.setActionCommand("BOOK");//set action command that will get the ActionListener
        book.addActionListener(controller);//set which ActionListener
        cancelBooking.setActionCommand("CANCEL");//set action command that will get the ActionListener
        cancelBooking.addActionListener(controller);//set which ActionListener
        // return Statement
        return form;
    }
    /**
     * Function that will return a JPanel.
     * It's responsible for creating the components of the bottom of the Layout.
     */
    private JPanel bottomPart(){
        // instance JPanel that will be return
        JPanel bottom = new JPanel(new FlowLayout());//For UX positioning will have a FlowLayout

        // Register option Button
        logout = new JButton("Log Out");
        // controller command
        logout.setActionCommand("LOGOUT");//set action command that will get the ActionListener
        logout.addActionListener(controller);//set which ActionListener
        bottom.add(logout);//set Flow position for UX purposes
        // return Statement
        return bottom;
    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */
    public void goToWindow(String windowName){
        // List of available views from this one
        switch (windowName){
            case "LOGOUT":
                this.listener.progressFrom(ProgressListener.AppState.LAUNCHER);
                break;
            case "BOOK":
                this.listener.progressFrom(ProgressListener.AppState.BOOK);
                break;
            case "CANCEL":
                this.listener.progressFrom(ProgressListener.AppState.CANCEL);
                break;
            default:
                System.err.println("Unknown window name MainMenu");
                break;
        }
    }
}
