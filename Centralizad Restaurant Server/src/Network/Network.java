package Network;

import Controller.FormController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
/**
 * Network class
 * that manages the net connection
 * implements Runnable
 */
public class Network implements Runnable {
    private FormController controller;
    private ServerSocket serverSocket;
    private LinkedList<ClientManager> clients;
    private boolean active;
    private boolean paused;
    private final Object pauseLock = new Object();

    /**
     * default constructor of the class
     */
    public Network() {
        active = true;
        paused = false;
        clients = new LinkedList<>();
    }

    /**
     * procedure registerController that links the controller to the network
     * @param c represents the controller
     */
    public void registerController(FormController c) {
        this.controller = c;
    }
    /**
     * This method start the Server.
     *
     * @param port it's a port that server connect.
     */

    public void startServer(int port) {
        try {
            if(serverSocket ==null) serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            new Thread(this).start();
        }
    }

    /**
     * procedure that reinits the server
     */
    public void reStartServer(){
        new Thread(this).start();
    }

    /**
     * This method starts the server. Also this method have a differents print if the communication is not possible to estbalish or the channel is close.
     */

    @Override
    public void run() {
        while (active) {

            try {
                ClientManager client = new ClientManager(serverSocket.accept(), this);
                clients.add(client);
            } catch (IOException e) {
                System.out.println("Communication error");
                active = false;
                try {
                    serverSocket.close();
                } catch (IOException e1) {
                    System.out.println("ERROR clossing comunication channel");
                }
            }

        }
    }
    /**
     * This method stop the Service.
     */

    public void stopService() {
        active = false;
        for (ClientManager i : clients) {
            i.dropClient();
        }
        clients.clear();
    }
    /**
     * This method pause the Service.
     */
    public void pause() {
        System.out.println("System paused");
        paused = true;
        for (ClientManager i : clients) {
            i.setSatus(true);
        }
    }

    /**
     * pauses that sets to false the client status
     */
    public void resume() {
        for (ClientManager i : clients) {
            i.setSatus(false);
        }
        paused = false;
    }
    /**
     * This method that send a Reserve of a table.
     * @param u it's a parameter that user that have all the information of client.
     */
    public void sendReserve(Reserve u){
        controller.addReserve(u);
    }

    /**
     * This method that find a User.
     * @param u it's a parameter that user that have all the information of client.
     */
    public int findUser(User u){
        for(int i=0;i< clients.size();i++){
            if(clients.get(i).getClient() == u){
                return i;
            }
        }
        return 0;
    }
    /**
     * This method send the message.
     * @param msg it's a parameter that have the message.
     * @param u it's a parameter that user that have all the information of client.
     */
    public void sendMissage(String msg,User u){
        clients.get(findUser(u)).sendObject(msg);
    }
    /**
     *
     * @param u it's a parameter that user that have all the information of client.
     * @return This method return one String about the State of Reserve
     */
    public String getReserveState(User u){
        return this.controller.reserveState(u);

    }
    /**
     * This method is based on making a cancellation of a reservation.
     * @param u it's a parameter that user that have all the information of client.
     */
    public void cancelReserve(User u){
        controller.reserveCancelation(u);
    }
    /**
     *
     * @param instance it's a parameter that user that have all the information of client.
     * @return This method returns a boolean that indicates whether it is reserve or not.
     */

    public boolean isAReserve(User instance){
        LinkedList<Reserve> reserves = controller.getReserved();
        System.out.println(reserves.size()+"<-reservas");
        for(Reserve i: reserves){
            System.out.println(i.getReserveName()+" == "+ instance.getUser());
            if(i.getReserveName().equals(instance.getUser())&&i.getUser().getPassword().equals(instance.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * function that tells if its active the server
     * @return false if the server state is false, true if the server is running
     */
    public boolean isActiveServer(){
        String answer = controller.getState();
        if(answer.equals("END")) return false;
        return true;
    }

    /**
     * function that tells if the client can log now or not
     * @param reservaName name of the reserve of the client
     * @return true in case it can log, false otherwise
     */
    public boolean isYourTurn(String reservaName){
        return  controller.youCanEnter(reservaName);
    }

    /**
     * drops the reserve
     * @param instance of the reserve log
     */
    public void billedReserve(User instance){
        LinkedList<Reserve> reserves = controller.getReserved();
        for(Reserve i: reserves){
            if(i.getReserveName().equals(instance.getUser())){
                controller.reserveCancelation(instance);
            }
        }
    }

    /**
     * getter of the current carte dishes
     * @return current dishes
     */
    public LinkedList<Dish> getCurrentCarte(){
        return controller.getDishes();
    }

    /**
     * procedure that adds a client in the service
     * @param obj that represent the client
     */
    public void addClient(User obj){
        controller.addClientToService(obj);
    }

    /**
     * procedure that gets the client order
     * @param client that makes the order
     * @param plate that has to be registered in the bbdd
     */
    public void addDishToOrder(User client, Dish plate){
        controller.addDishToService(client,plate);
    }
    /**
     * procedure that removes a client order
     * @param client that makes the order
     * @param plate that has to be registered in the bbdd
     */
    public void removeDishToOrder(User client, String plate){
        controller.removeDishOrder(client, plate);
    }
    /**
     * function that gets if the dishes are cooking or not
     * @param client that refers its comands
     * @return order states
     */
    public LinkedList<Boolean> getDishStates(User client){
        return controller.dishState(client);
    }
}
