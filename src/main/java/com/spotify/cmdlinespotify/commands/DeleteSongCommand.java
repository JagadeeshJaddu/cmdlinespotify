package com.spotify.cmdlinespotify.commands;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.spotify.playlistmanager.controllers.SongController;
import com.spotify.playlistmanager.dtos.RemoveSongRequestDTO;
import com.spotify.playlistmanager.dtos.ResponseDTO;


@Component
public class DeleteSongCommand implements Command{

    private SongController songController;

    public DeleteSongCommand(SongController songController)
    {
        this.songController = songController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        if(inputWords.size()==2 && inputWords.get(0).equalsIgnoreCase(CommandKeywords.deleteSong))
        {
            return true;
        }
        return false;
    }

    @Override
    public void excecute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        Long songId = Long.parseLong(inputWords.get(1));
        RemoveSongRequestDTO removeSongRequestDTO = new RemoveSongRequestDTO();
        removeSongRequestDTO.setSongId(songId);
        ResponseDTO responseDTO = songController.deleteSong(removeSongRequestDTO);
        System.out.println(responseDTO.getStatus()+"");
        return ;
    }
    
}
