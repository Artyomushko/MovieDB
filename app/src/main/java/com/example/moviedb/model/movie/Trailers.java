package com.example.moviedb.model.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trailers {

    @SerializedName("youtube")
    @Expose
    private List<Youtube> youtube;

    public List<Youtube> getYoutube() {
        return youtube;
    }

    public class Youtube {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("size")
        @Expose
        private String size;
        @SerializedName("source")
        @Expose
        private String source;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSource() {
            return source;
        }

    }
}
