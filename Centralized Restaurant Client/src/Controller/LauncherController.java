package Controller;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        // Validate login, or whatever.
        // All went well, notify listener to progress.
        switch (e.getActionCommand()){
            case "SIGN_IN":
                launcher.goToWindow("SIGN_IN");
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
