package Network;

import Model.Database.Entity.*;
import Model.Database.dao.DishDAO;
import Model.Database.dao.UserDAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.function.DoubleUnaryOperator;

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
        if(obj instanceof Dish){
            answer = treatDish((Dish) obj);
        }
        if(obj instanceof String){
            answer = treatString((String) obj);
        }
        System.out.println(answer);
        if(answer.equals("MENU")){
            DishDAO dao = new DishDAO();
            LinkedList<Dish> empty = new LinkedList<>();
            empty = dao.getAllDishes();
            return empty;//dao.getAllDishes();
        }
        if(answer.equals("STATE")){

            LinkedList<Boolean> cooks = listener.getDishStates(client);
            String array = "";
            for(Boolean c :cooks){
                System.out.println(c);
                array += c+""+":";
            }
            return array;
        }

        return answer;
    }
    private String treatDish(Dish obj){
        //ToDo: TAKE OUT DISHES AND PUT IT IN SERVICE
        for(int i=0; i<obj.getQuantety();i++){
            listener.addDishToOrder(client,obj);
        }
        return "OK";
    }
    private String treatString(String msg){
        System.out.println(msg);
        if(msg.equals("time")){
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(d);
        }
        if(msg.equals("STATE")){
            //ToDO: que se genere una pwd para el user y se vea algo en el service
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
        if(msg.equals("CANCEL")) {
            listener.cancelResere(client);
            this.client.setReserve("");
            return "OK";
        }
        if(msg.equals("BILLED")){
            //ToDo: that billed remove actually the reserve
            listener.billedReserve(client);
            return "BILLED";
        }
        if(msg.equals("DISHES")){
            return "MENU";
        }
        if(msg.equals("CLOCK")){
            return "STATE";
        }
        return "null";
    }
    private String treatUser(User obj){
        //instance user data access object
        UserDAO dao = new UserDAO();
        //check if its a reserve
        System.out.println("r"+obj.getReserve());
        if(obj.getReserve().equals("")) {
            //check if user is already on the system
            LinkedList<User> users = dao.getAllUsers();
            for (User i : users) {
                if (i.getUser().equals(obj.getUser()) && i.getPassword().equals(obj.getPassword())) {
                    //if exists is a log
                    System.out.println(i.getUser()+" from server"+ obj.getUser()+"from client");
                    this.client = obj;
                    return "Login";
                }
            }
            //lastly check that the log was not a reserve log
            if(listener.isAReserve(obj)){
                listener.addClient(client);
                return "Reserve";
            }{
                String response = this.listener.getReserveState(client);
                if(response.equals("UNKNOWN") || response.equals("NO")){
                    return "OUT";
                }
            }
            //if not exists must be a reserve
            dao.addUser(obj);
            return "OK";
        }else {
            client.setReserve(obj.getReserve());
            listener.sendReserve(client);
        }
        return "OK";
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
