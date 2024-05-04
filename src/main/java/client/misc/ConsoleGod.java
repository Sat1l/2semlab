package main.java.client.misc;


import main.java.client.modules.Client;
import main.java.server.modules.Server;

public class ConsoleGod {

    public static void whisper(String revelation){
        if (!Client.getInstance().isInteractiveMode()){
            System.out.print("/script output/ ");
        }
        System.out.println(revelation);
    }
}
