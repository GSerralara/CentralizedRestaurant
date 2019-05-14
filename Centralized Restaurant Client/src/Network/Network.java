package Network;

import Controller.FormController;


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
    /**
     * Empty constructor by default of the class.
     * */
    public Network() { }
    /**
     * Function that will return 1 if it connection was successful. 0 if not.
     * It's responsible for creating a connection with the server.
     */
    public int connect() {

        try {
            System.out.println("Iniciar conexion");
            socket = new Socket(IP,PORT);
            System.out.println("Connectado");
            is = new ObjectInputStream(socket.getInputStream());
            os =  new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException e){
            //e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public void registerController(FormController c){
        this.controller = c;
    }
}
