package main.java.client;

import main.java.client.modules.Client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client client = Client.getInstance();
        Client.getInstance().setScanner(new Scanner(System.in));
        client.run();
    }
}