package main.java.server.commands;

import main.java.common.network.Response;

import java.io.Serializable;

public class Execute extends Command{
    @Override
    public Response call(String strArg, Serializable objArg) {

//        File file = new File(arg);
//        if(App.getInstance().getScriptsStack().contains(file)){
//            ConsoleGod.whisper("Recursion detected");
//            return;
//        }
//        try {
//            Scanner oldScanner = App.getInstance().getScanner();
//            Scanner newScanner = new Scanner(file);
//            App.getInstance().getScriptsStack().add(file);
//            CommandCaller commandCaller = App.getInstance().getCommandCaller();
//            while (newScanner.hasNextLine()){
//                String line = newScanner.nextLine();
//                commandCaller.call(line.trim().toLowerCase());
//            }
//            App.getInstance().getScriptsStack().removeLast();
//        } catch (FileNotFoundException e) {
//            ConsoleGod.whisper("didn't find the desired script file");
//        }
        return new Response("kek");
    }

    @Override
    public String getDescription() {
        return "execute script from file. Example usage: \"execute script.txt\"";
    }
}
