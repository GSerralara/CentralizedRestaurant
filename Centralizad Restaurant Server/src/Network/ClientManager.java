package Network;

import Model.Database.Entity.*;
import Model.Database.dao.DishDAO;
import Model.Database.dao.UserDAO;
import Resources.Pop;

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

/**
 * Client manager class
 * that manages the client requests
 * extends Thread class
 */

public class ClientManager extends Thread {
    private Socket cSocket;
    private boolean serverStoped;// true -> paused, false -> active
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private Network listener;
    private User client;
    private Reserve reserve;
    private Boolean goingToRegister;

    private Object clientRequest;
    private Object clientAnswer;

    /**
     * contructor of the class
     * @param cSocket that serves the purpose of channel of comunication with the client
     * @param listener thats the network with the program through the controller to get the data and send it
     * @throws IOException
     */
    public ClientManager(Socket cSocket , Network listener) throws IOException {
        this.goingToRegister = false;
        this.listener = listener;
        this.serverStoped = false;
        this.cSocket = cSocket;
        os = new ObjectOutputStream(cSocket.getOutputStream());
        is = new ObjectInputStream(cSocket.getInputStream());

        new Thread(this).start();
    }

    /**
     * setter of server state
     * @param statusState that tells if the server is stoped or not
     */
    public void setSatus(boolean statusState){
        this.serverStoped = statusState;
    }

    /**
     * In this method what we will do is that the client is listening and can send and receive objects.
     */
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

    /**
     * function that treats the info gotten by the client
     * @param obj represent the request of the client
     * @return a generic answer
     */
    private Object treatObject(Object obj){
        String answer = "";
        if(obj instanceof User){
           answer =  treatUser((User) obj);
        }
        if(obj instanceof Reserve){
            answer = treatReserve((Reserve) obj);
        }
        if(obj instanceof Dish){
            return treatDish((Dish) obj);
        }
        if(obj instanceof String){
            answer = treatString((String) obj);
        }
        System.out.println(answer);
        if(answer.equals("MENU")){
            LinkedList<Dish> carte = listener.getCurrentCarte();
            System.out.println(carte.size()+" dishes");
            return carte;
        }
        if(answer.equals("STATE")){
            System.out.println("Look to order dishes state");
            LinkedList<Boolean> cooks = listener.getDishStates(client);
            System.out.println(cooks.size()+" lol");
            String array = "";
            for(Boolean c :cooks){
                System.out.println(c);
                array += c+""+":";
            }
            System.out.println(array==null);
            return array;
        }

        return answer;
    }

    /**
     * function that treats the Reserve gotten by the client
     * @param obj represent the reserve request of the client
     * @return a generic answer
     */
    private String treatReserve(Reserve obj){
        //client.setReserve(obj.getReserve());
        reserve = obj;
        listener.sendReserve(reserve);
        Pop pop = new Pop("Authorization gotten");
        return "Reserve";
    }

    /**
     * function that treats the info gotten by the client
     * @param obj represent the request of the client
     * @return a generic answer
     */
    private Object treatDish(Dish obj){
        System.out.println("Gotten dish to order");
        for(int i=0; i<obj.getQuantety();i++){
            listener.addDishToOrder(client,obj);
        }
        return listener.getCurrentCarte();
    }

    /**
     * function that treats the info gotten by the client
     * @param msg represent the request of the client
     * @return a generic answer
     */
    private String treatString(String msg){
        System.out.println(msg);
        if(msg.equals("time")){
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(d);
        }
        if(msg.equals("Register")){
            goingToRegister = true;
            return "Ok";
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
            listener.cancelReserve(client);
            this.reserve = null;
            return "OK";
        }
        if(msg.equals("BILLED")){
            listener.billedReserve(client);
            return "BILLED";
        }
        if(msg.equals("DISHES")){
            return "MENU";
        }
        if(msg.equals("CLOCK")){
            return "STATE";
        }
        if(msg.charAt(0)=='C' && msg.charAt(1)=='N'){
            String dish = "";
            int j =2;
            for(int i= 2;i< msg.length() && msg.charAt(i) != ':';i++){
                dish += msg.charAt(i);
                j++;
            }
            j++;
            String number = "";
            for (int i=j;i<msg.length();i++){
                number += msg.charAt(i);
            }
            int num = Integer.parseInt(number);
            listener.removeDishToOrder(client,dish,num);
            return "OK";
        }
        return "null";
    }

    /**
     * function that treats the info gotten by the client
     * @param obj represent the request of the client
     * @return a generic answer
     */
    private String treatUser(User obj){
        //instance user data access object
        UserDAO dao = new UserDAO();
        if(!obj.getRegiser()){
            //check if user is already on the system (LOG IN)
            LinkedList<User> users = dao.getAllUsers();
            for (User i : users) {
                if (i.getUser().equals(obj.getUser()) && i.getPassword().equals(obj.getPassword())) {
                    //if exists is a log
                    System.out.println(i.getUser()+" from server"+ obj.getUser()+"from client");
                    this.client = obj;
                    return "Login";
                }
            }
            //check if reserve
            if(listener.isAReserve(obj)){

                if(listener.isYourTurn(obj.getUser())){
                    listener.addClient(obj);
                    this.client = obj;
                    return "Reserve";
                }
                return "NOT";


            }{
                return "OUT";
            }
        }else{
            //if not exists its a register
            dao.addUser(obj);
        }

        return "OK";
    }

    /**
     * procedure that drops the client
     */
    public void dropClient(){
        try {
            os.close();
            is.close();
            cSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter of the client
     * @return the current client
     */
    public User getClient() {
        return client;
    }

    /**
     * procedure that sends an object to the client
     * @param toSend object that is sent
     */
    public void sendObject(Object toSend){
        try {
            os.writeObject(toSend);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

