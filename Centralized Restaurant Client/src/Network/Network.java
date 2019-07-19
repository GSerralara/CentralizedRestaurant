package Network;

import Controller.FormController;
import Model.Settings;
import Resources.Pop;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class Network{

    private int PORT;
    private String IP;

    private Socket socket;

    private ObjectInputStream is;
    private ObjectOutputStream os;

    private  Object serverRequest = new Object();
    private  Object serverAnswer = new Object();

    private FormController controller;
    /**
     * Empty constructor by default of the class.
     * */
    public Network(Settings set) {
        this.IP = set.getIp();
        this.PORT = set.getPort();
    }
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
            Pop popup = new Pop("Error trying to connect");
            return 0;
        }
        return 1;
    }

    public void disconnect(){
        try {
            if (!socket.isClosed()) socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void treatResponse(Object obj){
        if(obj instanceof String){
            Pop pop=new Pop((String) obj);
        }
    }
    public void sendObject(Object toSend){
        try {
          os.writeObject(toSend);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Object readObject(){
        try {
            return is.readObject();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e1){
            e1.getMessage();
        }
       return null;
    }

    public void registerController(FormController c){
        this.controller = c;
    }



}
