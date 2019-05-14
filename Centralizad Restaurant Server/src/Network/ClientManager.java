package Network;

import Model.DataBase.Entity.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientManager extends Thread {
    private Socket cSocket;
    private boolean serverStoped;// true -> paused, false -> active
    private ObjectInputStream is;
    private ObjectOutputStream os;

    private Object clientRequest;
    private Object clientAnswer;
    public ClientManager(Socket cSocket) throws IOException {
        System.out.println("new Client");
        this.serverStoped = false;
        this.cSocket = cSocket;
        os = new ObjectOutputStream(cSocket.getOutputStream());
        is = new ObjectInputStream(cSocket.getInputStream());

        System.out.println("run dedicated server");
        new Thread(this).start();
    }
    public void setSatus(boolean statusState){
        this.serverStoped = statusState;
    }

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("Reading obj");
            clientRequest = is.readObject();
            //manage petition
            System.out.println(clientRequest.getClass().getName());
            //treatObject(clientRequest);
            clientAnswer = "OK";
            os.writeObject(clientAnswer);

            //finally
            cSocket.close();

        }catch (IOException | ClassNotFoundException e){
           System.err.println("Error en la comunicacion");
        }
    }
    private void treatObject(Object obj){
        if(obj instanceof User){
            System.out.println("Got user");
        }
    }
    public void dropClient(){
        try {
            os.close();
            is.close();
            cSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
