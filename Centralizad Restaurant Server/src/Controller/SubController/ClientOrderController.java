package Controller.SubController;

import Controller.ServiceController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientOrderController implements ActionListener {
    private ServiceController listener;

    public ClientOrderController(ServiceController listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
