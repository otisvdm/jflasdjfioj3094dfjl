package nl.otis.domain;

import java.text.Format;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Track {
    private Integer id;
    private String title;
    private String performer;
    private Integer duration;
    private String album;
    private Integer playcount;
    private String publicationDate;
    private String description;
    private Boolean offlineAvailable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(publicationDate);
        this.publicationDate = strDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(Boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }

}
