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
                clientRequest = is.readObject();
                //manage petition
                System.out.println(clientRequest.getClass().getName());

                clientAnswer = treatObject(clientRequest);
                System.out.println(clientRequest.getClass().getName());
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
        return "null";
    }
    private String treatUser(User obj){
        UserDAO dao = new UserDAO();
        //Es un login
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
        }else{//es un register
            System.out.println("REGISTER");
            //dao.addUser(obj);
            return "OK";
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
}
