package main.java.client;

import main.java.client.modules.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        Client client = Client.getInstance();
        client.run();
    }
}