package Network;

import Model.Database.Entity.*;
import Model.Database.dao.UserDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

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
        while (true){
            try {
                System.out.println("Reading obj");
                clientRequest = is.readObject();
                //manage petition
                System.out.println(clientRequest.getClass().getName());

                clientAnswer = treatObject(clientRequest);
                os.writeObject(clientAnswer);
                if(clientAnswer=="null") cSocket.close();
                    //finally


            }catch (IOException | ClassNotFoundException e){
                System.err.println("Error en la comunicacion");
            }
        }

    }
    private Object treatObject(Object obj){
        switch (obj.getClass().toString()){
            case "Model.Database.Entity.User":
                System.out.println("Got user");
                User obect = (User) obj;
                UserDAO dao = new UserDAO();
                //Es un login
                if(obect.getMail() == ""){
                    LinkedList<User> users = dao.getAllUsers();
                    for(User i: users){
                        //System.out.println(i.getUser() + obect.getUser());
                        if (i.getUser().equals(obect.getUser()) && i.getPassword().equals(obect.getPassword())){
                            System.out.println("Nombres iguales");
                            return "OK";
                        }
                    }
                }else{//es un register
                    dao.addUser(obect);
                    return "OK";
                }
                return "ERROR";
            default:

                break;
        }
        return "null";
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
