package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.*;
import cz.uhk.fim.pro2.moview.utils.DataHandler;
import cz.uhk.fim.pro2.moview.utils.HttpHandler;
import cz.uhk.fim.pro2.moview.utils.ImageHandeler;
import cz.uhk.fim.pro2.moview.utils.MovieParser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JCheckBox cbRok = new JCheckBox("Vyhledat podle roku");
    private JLabel lblrok = new JLabel("Rok");
    private JTextField tfRok = new JTextField();



    public MainFrame() throws HeadlessException {
        initFrame();
        if(!cbRok.isSelected()){
            lblrok.setVisible(false);
            tfRok.setVisible(false);

        } else {
            lblrok.setVisible(true);
            tfRok.setVisible(true);
        }
        //initTestData();
    }

    private void initFrame () {
        setTitle("MovieW");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();


        JPanel input = new JPanel();

        GroupLayout gl = new GroupLayout(input);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        //TextFields
        JTextField tfNazev = new JTextField();
        tfNazev.setSize(new Dimension(300,20));
        tfRok.setPreferredSize(new Dimension(20, 20));
        //Labels
        JLabel lblNazev = new JLabel("Název");
        //Buttons
        JButton btnVyhledat = new JButton("Vyhledat");
        //CheckBoxes
        //JCheckBox cbRok = new JCheckBox("Vyhledat podle roku");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                        .addComponent(lblNazev)
                        .addComponent(cbRok)
                )
                .addComponent(tfNazev)
                .addComponent(lblrok)
                .addComponent(tfRok)
                .addComponent(btnVyhledat)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup()
                .addComponent(lblNazev)
                .addComponent(tfNazev)
                .addComponent(lblrok)
                .addComponent(tfRok)
                .addComponent(btnVyhledat)
                )
                .addComponent(cbRok)

        );

        input.setLayout(gl);

        setSize((int)(screensize.width * 0.5), (int)(screensize.height * 0.5));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.add(input, BorderLayout.NORTH);
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
