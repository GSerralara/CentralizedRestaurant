package Controller;

import View.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceController implements ActionListener {
    private FormController listener;
    private Service service;

    public ServiceController(FormController listener) {
        this.listener = listener;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
