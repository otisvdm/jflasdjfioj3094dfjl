package nl.otis.domain;

import nl.otis.dao.TracksDao;
import nl.otis.dto.TracksRequestBody;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.List;

@Default
public class TrackDm {
    @Inject
    TracksDao tracksDao;
    public List<TracksRequestBody> retrieveTracks(int playlistId, boolean inPlaylist) {
        List<TracksRequestBody> tracks = tracksDao.retrieveTracks(playlistId,inPlaylist);
        return tracks;
    }

    public void deleteTrack(int trackId,int playlistId) {
        tracksDao.deleteTrack(trackId,playlistId);
    }
    public void addTrack(int trackId,int playlistId,boolean offlineavailable) {
        tracksDao.addTrack(trackId,playlistId,offlineavailable);
    }
}
