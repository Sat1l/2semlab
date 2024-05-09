package main.java.client.modules;

import main.java.common.misc.FlatData;
import main.java.common.network.Request;

import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Client {

    private static Client client;

    Scanner scanner;

    Scanner tempScanner;

    private final InetAddress host;
    private final int port;
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
        System.out.print("> ");
        try {
            Scanner input = new Scanner(System.in);
            while (input.hasNextLine()) {
                var command = "";
                var arguments = "";
                String inpt = input.nextLine();
                if (inpt.trim().isEmpty()) {
                    System.out.print("> ");
                    continue;
                }
                String[] prompt = (inpt + " ").trim().split(" ", 2);
                if (prompt.length == 2) {
                    arguments = prompt[1].trim();
                }
                command = prompt[0].trim();
                processUserPrompt(command, arguments);
                System.out.print("> ");
            }
            System.out.println("bye-bye🤫🧏");
            System.exit(0);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IOException occurred or class not found.");
        }
    }

    private void processUserPrompt(String command, String arguments) throws IOException, ClassNotFoundException {
        Request request;
        if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("update_id")){
            FlatData objArgument = new DataFetcher().fetch();
            if (objArgument != null) {
                System.out.println(send(new Request(command, arguments, objArgument)));
            }
        }
        else if (command.equalsIgnoreCase("exit")){
            System.out.println("bye-bye🤫🧏");
            System.exit(0);
        }
        else if (command.equalsIgnoreCase("execute")){
            executeScript(arguments);
        }
        else {

            request = new Request(command, arguments);
            String response = send(request);
            System.out.println(response);
        }
    }

    private String send(Request request) throws IOException{
        String response = "";
        try{
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(100);
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
//            System.out.println(response);
            socket.close();
            return response;
        } catch (IOException e) {
            System.out.println("the server is not reachable as of now, try again later...");
        }
        return response;
    }

    private void executeScript(String filepath){
        File file = new File(filepath);
        if(this.getScriptsStack().contains(file)){
            System.out.println("Recursion detected");
            return;
        }
        try {
            this.tempScanner = Client.getInstance().scanner;
            Scanner newScanner = new Scanner(file);
            this.getScriptsStack().add(file);
            this.setScanner(newScanner);
            while (newScanner.hasNextLine()){
                String line = newScanner.nextLine();
                var command = "";
                var arguments = "";

                String[] prompt = (line + " ").trim().split(" ", 2);
                if (prompt.length == 2) {
                    arguments = prompt[1].trim();
                }
                command = prompt[0].trim();
                if (command.equals("execute")){
                    this.executeScript(arguments);
                    continue;

                }
                processUserPrompt(command, arguments);
            }
            setScanner(tempScanner);
            this.getScriptsStack().removeLast();
        } catch (FileNotFoundException e) {
            System.out.println("didn't find the desired script file");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Deque<File> getScriptsStack() {
        return scriptsStack;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}