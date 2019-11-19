package cz.uhk.fim.pro2.moview.utils;

import cz.uhk.fim.pro2.moview.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieParser {
    public static List<Movie> parseMovieSearch (String query){
        List<Movie> searchList = new ArrayList<>();

        String jsonResponse = HttpHandler.searchForMovies(query);

        JSONObject rootObject = new JSONObject(jsonResponse);

        JSONArray movieArray = rootObject.getJSONArray("Search");
        for(int i =0 ; i < movieArray.length(); i++){
            JSONObject movieObject = movieArray.getJSONObject(i);
            Movie movie = new Movie();

            movie.setTitle(movieObject.getString("Title"));
            movie.setYear((movieObject.getString("Year")));
            movie.setPoster(ImageHandeler.getImageFromUrl(movieObject.getString("Poster"))) ;
            movie.setType(MovieType.fromString(movieObject.getString("Type")));
            movie.setMovieID(movieObject.getString("imdbID"));

            System.out.println(movie);

            searchList.add(movie);
        }

        System.out.println(rootObject);

        System.out.println(movieArray);


        return searchList;
    }

    public static Movie parseMovieDetail(String id){
        String jsonResponse = HttpHandler.getDetailByID(id);
        JSONObject rootObject = new JSONObject(jsonResponse);
        System.out.println(rootObject);

        String movieID = rootObject.getString("imdbID");


        String title = rootObject.getString("Title");

        //int year = Integer.parseInt(rootObject.getString("Year"));
        String year = rootObject.getString("Year");
        Date releaseDate = DataHandler.getDateFromString(rootObject.getString("Released"));

        int runtime = Integer.parseInt(rootObject.getString("Runtime").replace(" min", ""));

        List<Genre> genres = new ArrayList<>();
        String[] genreArray = rootObject.getString("Genre").split(",");
        for(String s : genreArray){
            genres.add(new Genre(s));
        }

        String director = rootObject.getString("Director");

        String writer = rootObject.getString("Writer");

        List<Actor> actors = new ArrayList<>();
        String[] actorArray = rootObject.getString("Actors").split(",");
        for(String s : actorArray){
            actors.add(new Actor(s));
        }

        String plot = rootObject.getString("Plot");

        String country = rootObject.getString("Country");

        String language = rootObject.getString("Language");

        Image poster = ImageHandeler.getImageFromUrl(rootObject.getString("Poster"));

        List<Rating> ratings = new ArrayList<>();
        JSONArray ratingArray = rootObject.getJSONArray("Ratings");
        for (int i = 0; i  < ratingArray.length(); i++){
            JSONObject rating = ratingArray.getJSONObject(i);
            ratings.add(new Rating(rating.getString("Source"), rating.getString("Value")));
        }

        MovieType type = MovieType.fromString(rootObject.getString("Type"));

        return new Movie(movieID, title, year,releaseDate, runtime, genres, director, writer, actors, plot, country, language, poster, ratings, type);

    }
}
