package Controller;

import View.ServiceState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceStateController implements ActionListener {
    private ServiceState serviceState;
    private FormController listener;
    public ServiceStateController(FormController listener){
        this.listener=listener;
    }
    public void setServiceState(ServiceState serviceState) {
        this.serviceState = serviceState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "INIT":
                this.listener.changeState("INIT");
                if(!serviceState.getCurrentState().equals("Now is shutted Down the server")){
                    this.listener.resumeServer();
                }else{
                    this.listener.runServer();
                }

                serviceState.changeCurrentState("Now is active the Server");
                break;
            case "STOP":
                this.listener.changeState("STOP");
                this.listener.pauseServer();
                serviceState.changeCurrentState("Now is paused the Server");
                break;
            case "END":
                this.listener.changeState("END");
                this.listener.shutServer();
                serviceState.changeCurrentState("Now is shutted Down the server");
                break;
        }
    }
}
