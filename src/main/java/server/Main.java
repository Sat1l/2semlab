package main.java.server;

import main.java.server.modules.CollectionManager;
import main.java.server.modules.CommandCaller;
import main.java.server.modules.CommandManager;
import main.java.server.modules.Server;
import main.java.server.commands.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(args[0]);
        Server server = Server.setupInstance(args[0]);
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
        server.getCommandManager().add("add_if_max", new AddIfMax());
        server.getCommandManager().add("remove_greater", new RemoveGreater());
        server.getCommandManager().add("remove_lower", new RemoveLower());
        server.getCommandManager().add("print_dnor", new PrintDescendingNumberOfRooms());
        server.getCommandManager().add("print_dttmof", new PrintDescendingTimeToMetroOnFoot());
        server.run(args);

    }
}
