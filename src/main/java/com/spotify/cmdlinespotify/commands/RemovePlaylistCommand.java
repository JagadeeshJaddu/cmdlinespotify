package com.spotify.cmdlinespotify.commands;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.spotify.playlistmanager.controllers.PlaylistController;
import com.spotify.playlistmanager.dtos.RemovePlaylistRequestDTO;
import com.spotify.playlistmanager.dtos.ResponseDTO;

@Component
public class RemovePlaylistCommand implements Command{
    private PlaylistController playlistController;

    public RemovePlaylistCommand(PlaylistController playlistController)
    {
        this.playlistController = playlistController;
    }
    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        if(inputWords.size() == 2 && inputWords.get(0).equalsIgnoreCase(CommandKeywords.removePlaylist))
        {
            return true;
        }
        return false;
    }

    @Override
    public void excecute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        Long playlistId = Long.parseLong(inputWords.get(1));
        RemovePlaylistRequestDTO removePlaylistRequestDTO = new RemovePlaylistRequestDTO();
        removePlaylistRequestDTO.setPlaylistId(playlistId);
        ResponseDTO responseDTO = playlistController.removePlaylist(removePlaylistRequestDTO);
        System.out.println(responseDTO.getStatus());
        return ;
    }
    
}