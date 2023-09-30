package com.spotify.cmdlinespotify.commands;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommandRegistry {
    private List<Command> commandList;

    public CommandRegistry(List<Command> commandList)
    {
        this.commandList = commandList;
    }
    public void excecute(String input)
    {
        for(Command command: commandList)
        {
            if(command.matches(input))
            {
                command.excecute(input);
                return ;
            }
        }
        return ;
    }
}
