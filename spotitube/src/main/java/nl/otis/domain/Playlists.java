package nl.otis.domain;

import nl.otis.dao.PlaylistsDao;
import nl.otis.dto.PlaylistsRequestBody;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Default
public class Playlists {
    @Inject
    PlaylistsDao playlistsDao;

    public void addPlaylist(PlaylistsRequestBody requestBody) {
        playlistsDao.addPlaylist(requestBody);
    }
    public List<PlaylistsRequestBody> retrievePlaylists() {
        List<PlaylistsRequestBody> playlists = new ArrayList<>();
        return playlists = playlistsDao.retrievePlaylists();
    }
    public void deletePlaylist(String id) {
        playlistsDao.deletePlaylist(id);
    }
    public void changeName(PlaylistsRequestBody playlistsRequestBody,String id) {
        playlistsDao.changeName(playlistsRequestBody,id);
    }
}
