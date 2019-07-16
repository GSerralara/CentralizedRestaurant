package Network;

import Controller.FormController;
import Model.Database.Entity.Dish;
import Model.Database.Entity.Reserve;
import Model.Database.Entity.User;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;

public class Network implements Runnable {
    private FormController controller;
    private ServerSocket serverSocket;
    private LinkedList<ClientManager> clients;
    private boolean active;
    private boolean paused;
    private final Object pauseLock = new Object();


    public Network() {
        active = true;
        paused = false;
        clients = new LinkedList<>();
    }

    public void registerController(FormController c) {
        this.controller = c;
    }

    public void startServer(int port) {
        try {
            if(serverSocket ==null) serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            new Thread(this).start();
        }
    }
    public void reStartServer(){
        new Thread(this).start();
    }

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

    public void stopService() {
        active = false;
        for (ClientManager i : clients) {
            i.dropClient();
        }
        clients.clear();
    }
    public void pause() {
        System.out.println("System paused");
        paused = true;
        for (ClientManager i : clients) {
            i.setSatus(true);
        }
    }
    public void resume() {
        for (ClientManager i : clients) {
            i.setSatus(false);
        }
        paused = false;
    }

    public void sendReserve(Reserve u){
        controller.addReserve(u);
    }

    public int findUser(User u){
        for(int i=0;i< clients.size();i++){
            if(clients.get(i).getClient() == u){
                return i;
            }
        }
        return 0;
    }

    public void sendMissage(String msg,User u){
        clients.get(findUser(u)).sendObject(msg);
    }
    public String getReserveState(User u){
        return this.controller.reserveState(u);
    }
    public void cancelReserve(User u){
        controller.reserveCancelation(u);
    }

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
    public boolean isActiveServer(){
        String answer = controller.getState();
        if(answer.equals("END")) return false;
        return true;
    }
    public boolean isYourTurn(String reservaName){
        return  controller.youCanEnter(reservaName);
    }
    public void billedReserve(User instance){
        LinkedList<Reserve> reserves = controller.getReserved();
        for(Reserve i: reserves){
            if(i.getUser().equals(instance.getUser())){
                controller.reserveCancelation(instance);
            }
        }
    }
    public LinkedList<Dish> getCurrentCarte(){
        return controller.getDishes();
    }
    public void addClient(User obj){
        controller.addClientToService(obj);
    }
    public void addDishToOrder(User client, Dish plate){
        controller.addDishToService(client,plate);
    }
    public LinkedList<Boolean> getDishStates(User client){
        return controller.dishState(client);
    }
}
