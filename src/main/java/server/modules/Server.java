package main.java.server.modules;

import main.java.common.network.Request;
import main.java.common.network.Response;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Server {

    private static Server server;

    CommandManager commandManager;

    CommandCaller commandCaller;

    CollectionManager collectionManager;

    private InetSocketAddress address;
    private Response response;
    private Request request;

    private Server() { }

    public static Server getInstance(){
        if(server == null){
            server = new Server(new InetSocketAddress(5001));
        }
        return server;
    }

    public Server(InetSocketAddress address) {
        this.address = address;
    }


    public void run(String[] args)  {

        String retrieved;

        try {
            DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt("5001"));
            System.out.println("Server Started. Listening for Clients on port 5001" + "...");
            // Assume messages are not over 1024 bytes
            byte[] receiveData = new byte[1024];
            byte[] responseData = new byte[1024];
            DatagramPacket receivePacket;
            while (true) {
                try {
                    // Server waiting for clients message
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receiveData);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Request receivedObject = (Request) objectInputStream.readObject();

                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println("[" + timestamp.toString() + ", IP: " + IPAddress + ", Port: " + port + "] ");
                    String response = commandCaller.call(receivedObject).getMessage();
                    System.out.println(response);
                    responseData = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, IPAddress, port);
                    serverSocket.send(sendPacket);

                } catch (NoSuchElementException e){
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CommandCaller getCommandCaller() {
        return commandCaller;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void setCommandCaller(CommandCaller commandCaller) {
        this.commandCaller = commandCaller;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
