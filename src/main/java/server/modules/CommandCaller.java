package main.java.server.modules;

import main.java.common.network.Request;
import main.java.common.network.Response;
import main.java.server.commands.Command;
import main.java.common.exceptions.NoSuchCommandException;
import main.java.client.misc.ConsoleGod;

public class CommandCaller {

    CommandManager commandManager = Server.getInstance().getCommandManager();

    public CommandCaller(){
    }

    public Response call(Request request) {
        try {
            Command command = commandManager.getCommandByKey(request.getCommandName());
            return command.call(request.getCommandStrArg(), request.getCommandObjArg());
        } catch (NoSuchCommandException e){
            return new Response("didn't find command: " + e.getMessage());
        }
    }
}
