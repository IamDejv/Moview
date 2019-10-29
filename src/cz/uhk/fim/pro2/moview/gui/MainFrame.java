package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.*;
import cz.uhk.fim.pro2.moview.utils.DataHandler;
import cz.uhk.fim.pro2.moview.utils.HttpHandler;
import cz.uhk.fim.pro2.moview.utils.ImageHandeler;
import cz.uhk.fim.pro2.moview.utils.MovieParser;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        initFrame();
        initTestData();
    }

    private void initFrame () {
        setTitle("MovieW");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(screensize.width * 0.75), (int)(screensize.height * 0.75));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void initTestData(){

        //HttpHandler.searchForMovies("star wars");
        boolean notCallAgain = true;
        for (Movie m : MovieParser.parseMovieSearch("star+wars")){
            if(notCallAgain){
                MovieParser.parseMovieDetail(m.getMovieID());
            }
            System.out.println(m);
        }
        notCallAgain = false;


        List<Rating> ratings = new ArrayList<>(3);
        ratings.add(new Rating("Internet", "8,6/10"));
        ratings.add(new Rating("Web", "93%"));
        ratings.add(new Rating("Intranet", "90/100"));

        List<Genre> genres = new ArrayList<>(4);
        genres.add(new Genre("Action"));
        genres.add(new Genre("Sci-Fi"));
        genres.add(new Genre("Action"));
        genres.add(new Genre("Action"));

        List<Actor> actors = new ArrayList<>(3);
        actors.add(new Actor("Mark Hammil"));
        actors.add(new Actor("Harrison Ford"));
        actors.add(new Actor("Carrie Fisher"));

        Movie movie = new Movie(
                "sd456",
                "Star Wars - ep. 4",
                1977,
                DataHandler.getDateFromString("25 May 1977"),
                121,
                genres,
                "George Lucas",
                "George Lucas",
                actors,
                "...",
                "USA",
                "English",
                ImageHandeler.getImageFromUrl("https://images-na.ssl-images-amazon.com/images/I/81WjGytz7HL._SY445_.jpg"),
                ratings,
                MovieType.MOVIE
        );
        System.out.println(movie);
    }
}
