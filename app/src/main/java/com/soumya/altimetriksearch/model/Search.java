package com.soumya.altimetriksearch.model;

public class Search {

    private String artistName, trackName, collectionName, collectionPrice, releaseDate, artworkUrl100;

    public Search(String artistName, String trackName, String collectionName,
                  String collectionPrice, String releaseDate, String artworkUrl100) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.collectionName = collectionName;
        this.collectionPrice = collectionPrice;
        this.releaseDate = releaseDate;
        this.artworkUrl100 = artworkUrl100;
    }

    public Search() {
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }
}
