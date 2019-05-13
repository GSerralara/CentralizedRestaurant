package Network;

import Controller.FormController;

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
    private WaitingRoom waitingRoom;
    private boolean active;
    private boolean paused;
    private final Object pauseLock = new Object();


    public Network() {
        active = true;
        paused = false;
        clients = new LinkedList<>();
        waitingRoom = new WaitingRoom();
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
                ClientManager client = new ClientManager(serverSocket.accept());
                System.out.println("add client to the list");
                clients.add(client);
                System.out.println(clients.size());
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
}
