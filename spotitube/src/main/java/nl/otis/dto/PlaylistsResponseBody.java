package nl.otis.dto;

import nl.otis.dao.PlaylistsDao;
import java.util.List;

public class PlaylistsResponseBody {

    private List<PlaylistsRequestBody> playlists;
    private int length;

    public List<PlaylistsRequestBody> getPlaylistsRequestBodies() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setPlaylists(List<PlaylistsRequestBody> playlists) {
        this.playlists = playlists;
    }
}
