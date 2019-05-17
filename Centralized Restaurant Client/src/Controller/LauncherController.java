package Controller;

import Model.Database.Entity.User;
import Resources.Pop;
import View.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherController implements ActionListener {
    private Launcher launcher;
    private FormController listener;
    public LauncherController(FormController listener) {
        this.listener = listener;
    }

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
                if(listener.tryConnection()){
                    User user = new User(launcher.getUserField(),launcher.getPwField());
                    listener.sendObject(user);

                    launcher.goToWindow("SIGN_IN");
                }else{
                    //launcher.goToWindow("SIGN_IN");
                    Pop popup = new Pop("No connection Possible");
                    //launcher.addWarning("NO Connection Possible");
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
}
