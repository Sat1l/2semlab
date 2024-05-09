package main.java.server.modules;

import main.java.common.network.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.NoSuchElementException;

public class Server {

    private static Server server;

    CommandManager commandManager;

    CommandCaller commandCaller;

    CollectionManager collectionManager;

    private String colPath;

    private Server() {
    }

    public static Server setupInstance(String filepath){
        if(server == null){
            server = new Server();
            server.setColPath(filepath);
        }
        return server;
    }

    public static Server getInstance(){
        return server;
    }

    public void run()  {
        collectionManager.setCollection(StorageManager.readStorage());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            StorageManager.saveToStorage();
            System.out.println("Server stopped. Collection saved.");
        }));

        try {
            DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt("5001"));
            System.out.println("Server Started. Listening for Clients on port 5001" + "...");
            // Assume messages are not over 1024 bytes
            byte[] receiveData = new byte[1024];
            byte[] responseData;
            DatagramPacket receivePacket;
            while (true) {
                try {
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receiveData);
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Request receivedObject = (Request) objectInputStream.readObject();

                    InetAddress IPAddress = receivePacket.getAddress();
                    int port = receivePacket.getPort();
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println("[" + timestamp + ", IP: " + IPAddress + ", Port: " + port + "] ");
                    String response = commandCaller.call(receivedObject).getMessage();
                    System.out.println(response);
                    responseData = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, IPAddress, port);
                    serverSocket.send(sendPacket);

                } catch (NoSuchElementException e){
                    e.printStackTrace();
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("got an issue while trying to access file: " + e.getMessage());
        }

    }

    public CommandManager getCommandManager() {
        return commandManager;
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

    public String getColPath() {
        return colPath;
    }

    public void setColPath(String colPath) {
        this.colPath = colPath;
    }
}
