package com.spotify.cmdlinespotify.commands;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.spotify.playlistmanager.controllers.PlaylistController;
import com.spotify.playlistmanager.dtos.CreatePlaylistRequestDTO;
import com.spotify.playlistmanager.dtos.ResponseDTO;


@Component
public class CreatePlaylistCommand implements Command{
    private PlaylistController playlistController;

    public CreatePlaylistCommand(PlaylistController playlistController)
    {
        this.playlistController = playlistController;
    }
    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        if(inputWords.size() == 2 && inputWords.get(0).equalsIgnoreCase(CommandKeywords.createPlaylist))
        {
            return true;
        }
        return false;
    }

    @Override
    public void excecute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        String playlistName = inputWords.get(1);
        CreatePlaylistRequestDTO createPlaylistRequestDTO = new CreatePlaylistRequestDTO();
        createPlaylistRequestDTO.setName(playlistName);
        ResponseDTO responseDTO = playlistController.createPlaylist(createPlaylistRequestDTO);
        System.out.println(responseDTO.getStatus());
        return ;
    }
    
}
