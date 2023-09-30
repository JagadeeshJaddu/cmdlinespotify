package com.spotify.cmdlinespotify.commands;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.spotify.playlistmanager.controllers.SongController;
import com.spotify.playlistmanager.dtos.AddSongRequestDTO;
import com.spotify.playlistmanager.dtos.ResponseDTO;

@Component
public class AddSongCommand implements Command {
    private SongController songController;

    public AddSongCommand(SongController songController) {
        this.songController = songController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        if (inputWords.size() == 4 && inputWords.get(0).equalsIgnoreCase(CommandKeywords.addSong)) {
            return true;
        }
        return false;
    }

    @Override
    public void excecute(String input) {
        List<String> inputWords = Arrays.stream(input.split(" ")).toList();
        String songName = inputWords.get(1);
        Long artistId = Long.parseLong(inputWords.get(2));
        int duration = Integer.parseInt(inputWords.get(3));
        AddSongRequestDTO addSongRequestDTO = new AddSongRequestDTO();
        addSongRequestDTO.setName(songName);
        addSongRequestDTO.setArtistId(artistId);
        addSongRequestDTO.setDuration(duration);

        ResponseDTO responseDTO = songController.addSong(addSongRequestDTO);

        System.out.println(responseDTO.getStatus());
        return;
    }

}
