package main.java.server;

import main.java.server.modules.CollectionManager;
import main.java.server.modules.CommandCaller;
import main.java.server.modules.CommandManager;
import main.java.server.modules.Server;
import main.java.server.commands.*;

public class Main {
    public static void main(String[] args) {
        Server server = Server.getInstance();
        server.setCommandManager(new CommandManager());
        server.setCommandCaller(new CommandCaller());
        server.setCollectionManager(new CollectionManager());
        server.getCommandManager().add("add", new Add());
        server.getCommandManager().add("clear", new Clear());
        server.getCommandManager().add("remove_by_id", new RemoveById());
        server.getCommandManager().add("show", new Show());
        server.getCommandManager().add("update_id", new UpdateId());
        server.getCommandManager().add("help", new Help());
        server.getCommandManager().add("info", new Info());
        server.getCommandManager().add("execute_script", new Execute());
        server.run(args);

//        Server server = new Server(new InetSocketAddress("localhost", 8000));
//        server.run(new String[]{"collection.csv"});
//        Строчки для теста на локалхосте. Для гелиуса достаточно указать только порт
    }
}
