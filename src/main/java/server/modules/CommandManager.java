package main.java.server.modules;

import main.java.server.commands.Command;
import main.java.common.exceptions.NoSuchCommandException;

import java.util.HashMap;

public class CommandManager {

    HashMap<String, Command> commands = new HashMap<>();

    public void add(String key, Command command){
        this.commands.put(key, command);
    }

    public Command getCommandByKey(String key) throws NoSuchCommandException {
        if (commands.get(key) == null){
            throw new NoSuchCommandException(key);
        }
        return commands.get(key);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}
