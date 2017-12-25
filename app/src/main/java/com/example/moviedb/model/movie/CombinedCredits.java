
package com.example.moviedb.model.movie;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CombinedCredits {

    @SerializedName("cast")
    @Expose
    private List<Cast> cast;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }


    public class Cast {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("first_air_date")
        @Expose
        private String firstAirDate;
        @SerializedName("original_name")
        @Expose
        private String originalName;

        public String getOriginalName() {
            return originalName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirstAirDate() {
            return firstAirDate;
        }

    }
}
