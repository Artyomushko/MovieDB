package com.example.moviedb.model.movie;

import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetails {

    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("genres")
    private List<Genre> genres;
    @SerializedName("id")
    private Integer id;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;
    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("runtime")
    private Integer runtime;
    @SerializedName("tagline")
    private String tagline;
    @SerializedName("title")
    private String title;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("trailers")
    private Trailers trailers;
    @SerializedName("casts")
    private Casts casts;
    @SerializedName("images")
    private MediaStore.Images images;
    @SerializedName("similar")
    private Similar similar;

    public String getBackdropPath() {
        return backdropPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Trailers getTrailers() {
        return trailers;
    }

    public Casts getCasts() {
        return casts;
    }

    public MediaStore.Images getImages() {
        return images;
    }

    public void setImages(MediaStore.Images images) {
        this.images = images;
    }

    public Similar getSimilar() {
        return similar;
    }
}
