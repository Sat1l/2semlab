package main.java.client.modules;

import main.java.common.misc.FlatData;
import main.java.common.model.Flat;
import main.java.common.network.Request;
import main.java.server.modules.Server;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Client {

    private static Client client;

    private InetAddress host;
    private int port;
    private SocketChannel channel;
    private final Deque<File> scriptsStack = new ArrayDeque<>();

    private Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public static Client getInstance(){
        if(client == null){
            try {
                client = new Client(InetAddress.getByName("localhost"), 5001);
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return client;
    }

    public void run()  {
        try {
            Scanner input = new Scanner(System.in);
            while (input.hasNext()) {
                var command = "";
                var arguments = "";
                String[] prompt = (input.nextLine() + " ").trim().split(" ", 2);
                if (prompt.length == 2) {
                    arguments = prompt[1].trim();
                }
                command = prompt[0].trim();

                processUserPrompt(command, arguments);
                System.out.print("> ");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IOException occurred.");
        }
    }

    private void processUserPrompt(String command, String arguments) throws IOException, ClassNotFoundException {
        Request request;
        if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("update_id")){
            FlatData objArgument = new DataFetcher().fetch();
            request = new Request(command, arguments, objArgument);
            send(request);
        }
        else if (command.equalsIgnoreCase("exit")){
            System.out.println("client connection stopped");
            System.exit(0);
        }
        else if (command.equalsIgnoreCase("executeScript")){
            executeScript(arguments);
        }
        else {
            request = new Request(command, arguments);
            send(request);
        }
    }

    private String send(Request request) throws IOException{
        String response;
        try{
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(1000);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(request);
            byte[] data = bos.toByteArray();
            byte[] responseData = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length, host, port);
            socket.send(packet);

            DatagramPacket receivePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(receivePacket);
            response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(response);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private

    public Deque<File> getScriptsStack() {
        return scriptsStack;
    }

    public boolean isInteractiveMode() {
        return scriptsStack.isEmpty();
    }
}