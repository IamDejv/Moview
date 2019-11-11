package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.*;
import cz.uhk.fim.pro2.moview.utils.DataHandler;
import cz.uhk.fim.pro2.moview.utils.HttpHandler;
import cz.uhk.fim.pro2.moview.utils.ImageHandeler;
import cz.uhk.fim.pro2.moview.utils.MovieParser;
import sun.awt.AWTAccessor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    //private BufferedImage image = (BufferedImage) m.getPoster();
    private JCheckBox cbRok = new JCheckBox("Vyhledat podle roku");
    private JLabel lblrok = new JLabel("Rok: ");
    private JLabel lblNazev = new JLabel("Název: ");
    private JLabel lblPoster = new JLabel("Image........................................................................................");
    private JLabel lblNazevFilmu = new JLabel("Název");
    private JLabel lblRokFilmu = new JLabel("Rok");
    private JLabel lblTyp = new JLabel("Typ");
    private JTextField tfRok = new JTextField();
    private JTextField tfNazev = new JTextField();
    private JButton btnVyhledat = new JButton("Vyhledat");
    private JButton btnPridat = new JButton("Přidat do seznamu");
    private JButton btnPreskocit = new JButton("Přeskočit");

    public MainFrame() throws HeadlessException {
        initFrame();
        //initGui();

        //initTestData();
    }

    private void initFrame () {
        setTitle("MovieW");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);

        lblrok.setVisible(false);
        tfRok.setVisible(false);

        lblNazev.setBounds(10,10,50,25);
        tfNazev.setBounds(60, 10 , 580, 25);
        lblrok.setBounds(510,10,50,25);
        tfRok.setBounds(570, 10, 100,25);
        btnVyhledat.setBounds(680, 10 , 100, 25);
        cbRok.setBounds(60, 45, 150,25);
        lblPoster.setBounds(10,100,190,350);
        lblNazevFilmu.setBounds(250,100,100,25);
        lblRokFilmu.setBounds(250,135,100,25);
        lblTyp.setBounds(250,170, 100, 25);
        btnPridat.setBounds(520,170,160,25);
        btnPreskocit.setBounds(520,230,160,25);

        cbRok.addActionListener(e->{
            if(cbRok.isSelected()){
                tfRok.setVisible(true);
                lblrok.setVisible(true);
                tfNazev.setBounds(60,10,430,25);
            } else {
                tfRok.setVisible(false);
                lblrok.setVisible(false);
                tfNazev.setBounds(60,10,580,25);
                tfRok.setText("");
            }
        });

        add(lblNazev);
        add(tfNazev);
        add(lblrok);
        add(tfRok);
        add(btnVyhledat);
        add(cbRok);
        add(lblPoster);
        add(lblNazevFilmu);
        add(lblRokFilmu);
        add(lblTyp);
        add(btnPridat);
        add(btnPreskocit);
    }

    private void initGui(){
        setTitle("MovieW");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(800,600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        lblrok.setVisible(false);
        tfRok.setVisible(false);
        JPanel input = new JPanel();
        JPanel poster = new JPanel();
        this.add(poster, BorderLayout.CENTER);
        this.add(input, BorderLayout.NORTH);
        Container pane = input.getRootPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,0,5,5);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        pane.add(lblNazev, g);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 0;
        g.ipadx = 10;
        pane.add(tfNazev, g);
        tfNazev.setPreferredSize(new Dimension(200,25));
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 2;
        g.gridy = 0;
        g.ipadx = 10;
        pane.add(lblrok, g);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 3;
        g.gridy = 0;
        g.ipadx = 10;
        pane.add(tfRok, g);
        tfRok.setPreferredSize(new Dimension(75,25));
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 1;
        g.gridy = 1;
        pane.add(cbRok, g);
        g.fill = GridBagConstraints.VERTICAL;
        g.gridx = 4;
        g.gridy = 0;
        pane.add(btnVyhledat, g);

        cbRok.addActionListener(e->{
            if(cbRok.isSelected()){
                tfRok.setVisible(true);
                lblrok.setVisible(true);
            } else {
                tfRok.setVisible(false);
                lblrok.setVisible(false);
            }
        });
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
