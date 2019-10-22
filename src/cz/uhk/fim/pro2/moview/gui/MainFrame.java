package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.*;
import cz.uhk.fim.pro2.moview.utils.DataHandler;
import cz.uhk.fim.pro2.moview.utils.ImageHandeler;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
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
                "Star Wars",
                1977, DataHandler.getDateFromString("22 May 1977"),
                121,
                genres,
                "Georgfe lucas",
                "Geoired Lucas",
                "...",
                "USA",
                "ENglisg",
                actors,
                ratings,
                ImageHandeler.getImageFromUrl("http://cronicadexalapa.com/wp-content/uploads/2019/08/the-rise-of-skywaker-d23-poster_ec214fd3.jpg"),
                MovieType.MOVIE
        );
        System.out.println(movie);
    }
}
