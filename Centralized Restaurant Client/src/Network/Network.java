package Network;

import Controller.FormController;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network {

    private static final int PORT = 55555;
    private static final String IP = "localhost";

    private Socket socket;

    private ObjectInputStream is;
    private ObjectOutputStream os;

    private  Object serverRequest = new Object();
    private  Object serverAnswer = new Object();

    private FormController controller;
    public Network() { }
    public void connect() {

        try {
            System.out.println("Iniciar conexion");
            socket = new Socket(IP,PORT);
            System.out.println("Connectado");
            is = new ObjectInputStream(socket.getInputStream());
            os =  new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void registerController(FormController c){
        this.controller = c;
    }
}
