package com.spotify.cmdlinespotify.commands;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spotify.playlistmanager.controllers.ArtistController;
import com.spotify.playlistmanager.dtos.AddArtistRequestDTO;
import com.spotify.playlistmanager.dtos.ResponseDTO;

@Component
public class AddArtistCommand implements Command{
    private ArtistController artistController;

    public AddArtistCommand(ArtistController artistController)
    {
        this.artistController = artistController;
    }
    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        if(inputWords.size() == 2 && inputWords.get(0).equalsIgnoreCase(CommandKeywords.addArtist))
        {
            return true;
        }
        return false;
    }

    @Override
    public void excecute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        String artistName = inputWords.get(1);
        AddArtistRequestDTO artistRequestDTO = new AddArtistRequestDTO();
        artistRequestDTO.setName(artistName);
        ResponseDTO responseDTO = artistController.addArtist(artistRequestDTO);
        System.out.println(responseDTO.getStatus());

        return ;
    }
    
}
