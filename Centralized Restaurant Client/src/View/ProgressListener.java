package View;

/**
 * Interface responsible of  r
 * Extends from JFrame and implements ActionListener
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 */
interface ProgressListener {
    /**
     * List of possible views of the application
     */
    enum AppState {
        LAUNCHER,REGISTER,MAINTABLE,MENU,BOOK,CANCEL
    }

    /**
     * Method responsible of swinging the view from a JPanel to another
     * @param currentState it will tell the JPanel that the program should call
     */
    void progressFrom(AppState currentState);
}
