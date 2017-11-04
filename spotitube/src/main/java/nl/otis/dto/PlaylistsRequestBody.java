package nl.otis.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsRequestBody {
    private int id;
    private String name;
    private boolean owner;
    private List<TracksRequestBody> tracks = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TracksRequestBody> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksRequestBody> tracks) {
        this.tracks = tracks;
    }

}
