package main.java.app;

import main.java.server.modules.CommandCaller;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleRequestPoller {

    private final Scanner scanner;

    private final CommandCaller commandCaller;

    public ConsoleRequestPoller(Scanner scanner, CommandCaller commandCaller) {
        this.scanner = scanner;
        this.commandCaller = commandCaller;
    }

    public void poll(){
//        while(true){
//            try {
//                retrieved = scanner.nextLine().trim().toLowerCase();
//                commandCaller.call(retrieved);
//            } catch (NoSuchElementException e){
//                commandCaller.call("exit");
//            }
//        }
    }
}
