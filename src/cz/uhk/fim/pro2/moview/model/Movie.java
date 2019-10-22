package cz.uhk.fim.pro2.moview.model;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class Movie {

    private String title;
    private int year;
    private Date releaseDate;
    private int runtime;
    private List<Genre> genreList;
    private String director;
    private String writer;
    private String plot;
    private String country;
    private String language;
    private List<Actor> actors;
    private List<Rating> ratings;
    private Image poster;
    private MovieType type;

    public Movie(String title, int year, Date releaseDate, int runtime, List<Genre> genreList, String director, String writer, String plot, String country, String language, List<Actor> actors, List<Rating> ratings, Image poster, MovieType type) {
        this.title = title;
        this.year = year;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.genreList = genreList;
        this.director = director;
        this.writer = writer;
        this.plot = plot;
        this.country = country;
        this.language = language;
        this.actors = actors;
        this.ratings = ratings;
        this.poster = poster;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Image getPoster() {
        return poster;
    }

    public void setPoster(Image poster) {
        this.poster = poster;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", releaseDate=" + releaseDate +
                ", runtime=" + runtime +
                ", genreList=" + genreList +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", plot='" + plot + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", actors=" + actors +
                ", ratings=" + ratings +
                ", poster=" + poster +
                ", type=" + type +
                '}';
    }
}
