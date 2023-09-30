package com.spotify.cmdlinespotify.commands;

public interface Command {
    boolean matches(String input);

    void excecute(String input);
}
