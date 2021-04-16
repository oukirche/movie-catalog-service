package org.mql.spring.moviecatalogservices.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
    @JsonProperty("id")
    private String idMovie;
    @JsonProperty("original_title")
    private String name;
    @JsonProperty("overview")
    private String description;


    public Movie() {
    }

    public Movie(String movieId, String name, String description) {
        this.idMovie = movieId;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String movieId) {
        this.idMovie = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
