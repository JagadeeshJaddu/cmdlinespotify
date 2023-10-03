package com.spotify.cmdlinespotify.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spotify.playlistmanager.controllers.AlbumController;
import com.spotify.playlistmanager.dtos.AddSongToAlbumRequestDTO;
import com.spotify.playlistmanager.dtos.ResponseDTO;

@Component
public class AddSongToAlbumCommand implements Command{
    private AlbumController albumController;

    public AddSongToAlbumCommand(AlbumController albumController)
    {
        this.albumController = albumController;
    }
    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        if(inputWords.size() == 3 && inputWords.get(0).equalsIgnoreCase(CommandKeywords.addSongToAlbum))
        {
            return true;
        }
        return false;
    }

    @Override
    public void excecute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        Long albumId = Long.parseLong(inputWords.get(1));
        Long songId = Long.parseLong(inputWords.get(2));
        AddSongToAlbumRequestDTO addSongToAlbumRequestDTO = new AddSongToAlbumRequestDTO();
        addSongToAlbumRequestDTO.setAlbumId(albumId);
        addSongToAlbumRequestDTO.setSongId(songId);
        ResponseDTO responseDTO = albumController.addSongToAlbum(addSongToAlbumRequestDTO);
        System.out.println(responseDTO.getStatus());
        return ;
    }
    
}
