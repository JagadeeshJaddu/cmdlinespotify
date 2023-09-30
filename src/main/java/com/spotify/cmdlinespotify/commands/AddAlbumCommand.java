package com.spotify.cmdlinespotify.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spotify.playlistmanager.controllers.AlbumController;
import com.spotify.playlistmanager.dtos.AddAlbumRequestDTO;
import com.spotify.playlistmanager.dtos.ResponseDTO;

@Component
public class AddAlbumCommand implements Command{
    private AlbumController albumController;

    public AddAlbumCommand(AlbumController albumController)
    {
        this.albumController = albumController;
    }
    @Override
    public boolean matches(String input) {
        List<String> inputWords =  Arrays.stream(input.split(" ")).toList();
        if(inputWords.size() == 3 && inputWords.get(0).equalsIgnoreCase(CommandKeywords.addAlbum))
        {
            return true;
        }
        return false;
    }

    @Override
    public void excecute(String input) {
        List<String> inputWords =  Arrays.stream(input.split(" ")).toList();
        String albumName = inputWords.get(1);
        Long artistId = Long.parseLong(inputWords.get(2));
        AddAlbumRequestDTO addAlbumRequestDTO = new AddAlbumRequestDTO();
        addAlbumRequestDTO.setName(albumName);
        addAlbumRequestDTO.setArtistId(artistId);
        ResponseDTO responseDTO = albumController.addAlbum(addAlbumRequestDTO);
        System.out.println(responseDTO.getStatus());

        return ;
    }
    
}
