package View;

import Controller.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    // instance variables
    private final ProgressListener listener;


    /**
     * Constants for the Buttons and Action Commands
     */
    // Constants of the buttons
    private static final String JB_BOOK = "Book";
    private static final String JB_CANCEL ="Cancel";
    private static final String JB_LOGOUT = "Log Out";

    // Constants of the Action Commands
    private static final String AC_BOOK = "BOOK";
    private static final String AC_CANCEL = "CANCEL";
    private static final String AC_LOGOUT = "LOGOUT";
    /**
     * Constants for the UI
     */
    // Name of the view
    private static final String WINDOW_TITLE = "WELCOME";

    // General constants
    private static final String BOOK_LABEL_TEXT = "Book a Table";
    private static final String CANCEL_LABEL_TEXT = "Cancel your Reserve";
    /**
     * Attributes of the UI
     */
    private JButton cancelBooking,book,logout;

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    MainMenu(ProgressListener listener, MainMenuController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        controller.setMainMenu(this);
        // UI configuration of the panel
        windowConfiguration();
        registerController(controller);
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
        JLabel bookLabel = new JLabel(BOOK_LABEL_TEXT);
        JLabel cancelLabel = new JLabel(CANCEL_LABEL_TEXT);
        // instance attributes
        book = new JButton(JB_BOOK);
        cancelBooking = new JButton(JB_CANCEL);
        // add user camp to the from
        form.add(bookLabel);
        form.add(book);
        form.add(Box.createVerticalStrut(20));
        // add password camp to the fom
        form.add(cancelLabel);
        form.add(cancelBooking);
        form.add(Box.createVerticalStrut(20));
        // add Sing In button to the bottom

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
        logout = new JButton(JB_LOGOUT);
        // controller command
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
            default:
                System.err.println("Unknown window name MainMenu");
                break;
        }
    }
    private void registerController(ActionListener e){
        logout.setActionCommand(AC_LOGOUT);//set action command that will get the ActionListener
        logout.addActionListener(e);//set which ActionListener
        book.setActionCommand(AC_BOOK);//set action command that will get the ActionListener
        book.addActionListener(e);//set which ActionListener
        cancelBooking.setActionCommand(AC_CANCEL);//set action command that will get the ActionListener
        cancelBooking.addActionListener(e);//set which ActionListener
    }
}
