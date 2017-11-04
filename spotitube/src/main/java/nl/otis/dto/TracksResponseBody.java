package nl.otis.dto;
import nl.otis.dao.TracksDao;

import java.util.List;

public class TracksResponseBody {
    private List<TracksRequestBody> tracks;

    public List<TracksRequestBody> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksRequestBody> tracks) {
        this.tracks = tracks;
    }
}
