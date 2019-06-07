package Controller;

import Model.Database.Entity.User;
import Resources.Pop;
import View.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * LauncherController class
 * Implements ActionListener
 * It's function is to manage what happens in the Register view
 *
 * @author Guillermo Serraclara
 * @author Aleix Olle
 * @author David Diego
 * @author Pablo Nogueras
 * @author Victor Salvador
 */
public class LauncherController implements ActionListener {

    // attributes of the class
    private Launcher launcher;
    private FormController listener;
    //indicates if it goes to the MainMenu(false) or MainTable(true)
    private String logType;
    /**
     * Constructor by default of the class.
     * @param listener it's the controller father that manages all petitions
     * */
    public LauncherController(FormController listener) {
        this.listener = listener;
        logType = new String();
    }
    /**
     * Setter that sets the view that will retrieve data from.
     * @param launcher the view corresponding to this controller
     * */
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }
    /**
     * Override Method from ActionListener that activates when a Swing element,
     * with this class as an ActionListener, is interacted with.
     * @param e ActionEvent that will get the method.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Validate login, or whatever.
        // All went well, notify listener to progress.
        switch (e.getActionCommand()){
            case "SIGN_IN":
                //first we check that all camps are filled
                if(launcher.allFieldsFilled()){
                    //after that we try to connect to the server
                    if(listener.tryConnection()){
                        User user = new User(launcher.getUserField(),launcher.getPwField());
                        listener.login(user);
                        //ToDo: check if log was successful and kind of log
                        listener.startSession(user);
                        launcher.goToWindow(logType);
                    }
                }
                break;
            case "REGISTER":
                launcher.goToWindow("REGISTER");
                break;
            default:
                System.err.println("Unknown Button Launcher");
                break;
        }
        //this.listener.progressFrom(ProgressListener.AppState.LAUNCHER);
    }
    /**
     * Getter that returns the Log Type
     * @return a bool that indicates the kind of log will do
     */
    public String isLogType() {
        return logType;
    }
    public void setLogType(String state){
        this.logType = state;
    }
}
