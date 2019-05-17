package Network;

import Model.Database.Entity.*;
import Model.Database.dao.UserDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ClientManager extends Thread {
    private Socket cSocket;
    private boolean serverStoped;// true -> paused, false -> active
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private Network listener;
    private User client;

    private Object clientRequest;
    private Object clientAnswer;
    public ClientManager(Socket cSocket , Network listener) throws IOException {
        this.listener = listener;
        this.serverStoped = false;
        this.cSocket = cSocket;
        os = new ObjectOutputStream(cSocket.getOutputStream());
        is = new ObjectInputStream(cSocket.getInputStream());

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
                clientRequest = is.readObject();
                //manage petition
                System.out.println("Recibido:"+clientRequest.getClass().getName());

                clientAnswer = treatObject(clientRequest);
                System.out.println("Enviando:"+clientAnswer.getClass().getName());
                os.writeObject(clientAnswer);
                if(clientAnswer=="null") cSocket.close();
                    //finally


            }catch (IOException | ClassNotFoundException e){
                //System.err.println("Error en la comunicacion");
            }
        }

    }
    private Object treatObject(Object obj){
        String answer = "";

        if(obj instanceof User){
           answer =  treatUser((User) obj);
        }
        if(obj instanceof String){
            answer = treatString((String) obj);
        }
        System.out.println(answer);

        return answer;
    }
    private String treatString(String msg){
        System.out.println(msg);
        if(msg.equals("time")){
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(d);
        }
        if(msg.equals("STATE")){
            String response = this.listener.getReserveState(client);
            switch (response){
                case "YES":
                    return "Your reserve was Accepted";
                case "NO":
                    return "Your reserve was Declined";
                case "UNKNOWN":
                    return "Is still being processed";
            }
        }
        if(msg.equals("CANCEL")){
            listener.cancelResere(client);
            return "OK";
        }
        return "null";
    }
    private String treatUser(User obj){
        UserDAO dao = new UserDAO();
        //Es un login
        System.out.println(obj.getReserve());
        if(obj.getReserve().equals("")){
            if(obj.getMail().equals("")){
                System.out.println("LOGIN");
                //ToDo: Cuando tengas queries descomenta para agregar
                /*
                LinkedList<User> users = dao.getAllUsers();
                for(User i: users){
                    if (i.getUser().equals(obj.getUser()) && i.getPassword().equals(obj.getPassword())){
                        System.out.println("Nombres iguales");
                        return "OK";
                    }
                */
                this.client = obj;
                //int puerto = 5555+1+listener.findUser(obj);
                //return ""+puerto;
            }else{//es un register
                System.out.println("REGISTER");
                //dao.addUser(obj);
                return "OK";
            }
        }else{
            client.setReserve(obj.getReserve());
            System.out.println("Got Reserve");
            listener.sendReserve(client);
        }

        return "OK";
        //return "null";
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

    public User getClient() {
        return client;
    }
    public void sendObject(Object toSend){
        try {
            os.writeObject(toSend);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
