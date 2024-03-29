package View;

import Controller.BookController;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Book class.
 */

public class Book extends JPanel {
    // instance variables
    private final ProgressListener listener;


    /**
     * Constants for the Buttons and Action Commands
     */
    // Constants of the buttons
    private static final String JB_BOOK = "Book";
    private static final String JB_BACK ="Go back";

    // Constants of the Action Commands
    private static final String AC_BOOK = "BOOK";
    private static final String AC_BACK = "BACK";
    /**
     * Constants for the UI
     */
    // Name of the view
    private static final String WINDOW_TITLE = "Make your booking";

    // General constants
    private static final String BOOK_LABEL_TEXT = "Introduce your Reserve name";
    private static final String RESERVE_LABEL_TEXT = "Your Reserve has been:";
    /**
     * Attributes of the UI
     */
    private JTextField reserveName;
    private JSpinner bookNumber;
    private JButton back,book;

    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    Book(ProgressListener listener, BookController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        controller.setBook(this);
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
        JLabel bookLabel = new JLabel(BOOK_LABEL_TEXT);
        JLabel reserveStateLabel = new JLabel(RESERVE_LABEL_TEXT);
        // We instance the panel body of the window
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        // We create the different parts of the window.
        content.add(title);
        content.add(Box.createVerticalStrut(20));
        content.add(bookLabel);

        SpinnerModel smComnesals = new SpinnerNumberModel(1, 1, 999, 1);
        bookNumber = new JSpinner(smComnesals);
        reserveName = new JTextField();
        content.add(reserveName);
        content.add(reserveStateLabel);
        content.add(bookNumber);
        book = new JButton(JB_BOOK);

        content.add(book);
        content.add(Box.createVerticalStrut(20));
        JLabel reserveState = new JLabel("<html>" +
                "You can use the same password to Login the reserve.<br>" +
                "Just when Logging be sure to change the username camp for the name of your reserve.<br>" +
                "THANK YOU VERY MUCH FOR YOUR ATTENTION"+
                "</html>");
        reserveState.setSize(150,150);
        reserveState.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(reserveState);
        back = new JButton(JB_BACK);

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

    /**
     * Getter name Reserve
     * @return a String.
     */

    public String getReserveName(){
        return reserveName.getText();
    }

    /**
     * Getter Number of Book
     * @return a Integer.
     */

    public int getNumberBook(){
        return (int)bookNumber.getValue();
    }

    /**
     * Register Controller
     * @param e ActionListener.
     */
    private void registerController(ActionListener e){
        back.setActionCommand(AC_BACK);
        back.addActionListener(e);
        book.setActionCommand(AC_BOOK);
        book.addActionListener(e);
    }

}
