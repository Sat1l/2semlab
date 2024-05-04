package main.java.server.commands;

import main.java.common.network.Response;
import main.java.server.modules.CommandManager;
import main.java.client.misc.ConsoleGod;
import main.java.server.modules.Server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Help extends Command {
    @Override
    public Response call(String strArg, Serializable objectArg) {
        CommandManager manager = Server.getInstance().getCommandManager();
        HashMap<String, Command> commands = manager.getCommands();
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<String, Command> entry : commands.entrySet()){
            ret.append(entry.getKey() + " - " + entry.getValue().getDescription() + "\n");
        }
        return new Response(ret.toString());
    }

    @Override
    public String getDescription() {
        return "this command. Returns a list of all available commands";
    }
}
