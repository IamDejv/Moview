package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.*;
import cz.uhk.fim.pro2.moview.utils.*;
import sun.awt.AWTAccessor;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {
    //private BufferedImage image = (BufferedImage) m.getPoster();
    private JCheckBox cbRok = new JCheckBox("Vyhledat podle roku");
    private JLabel lblRok = new JLabel("Rok: ");
    private JLabel lblNazev = new JLabel("Název: ");
    private JLabel lblPoster = new JLabel();
    private JLabel lblNazevFilmu = new JLabel();
    private JLabel lblRokFilmu = new JLabel();
    private JLabel lblTyp = new JLabel();
    private JTextField tfRok = new JTextField();
    private JTextField tfNazev = new JTextField();
    private JButton btnVyhledat = new JButton("Vyhledat");
    private JPanel rootPanel;
    private JButton btnAdd = new JButton("Přidat do seznamu");
    private JButton btnSkip = new JButton("Přeskočit");
    private List<Movie> movies;
    private HashMap<String, String> genresMap;
    private HashMap<String, String> yearsMap;
    private JMenu mainMenu, moviesSubmenu, genresMenu, yearsMenu;

    private List<MenuItem> menuItems;
    private JMenuItem menuItem;

    public MainFrame() throws HeadlessException {
        initFrame();
        //initGui();
        //initUI();
        //initTestData();
    }

    private void initUI(){
        setContentPane(new UI().getRootPanel());
        setTitle("Moview");
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
        try {
            genresMap = FileUtils.decomposeData(FileUtils.readStringFromFile(FileUtils.TYPE_GENRES));
            yearsMap = FileUtils.decomposeData(FileUtils.readStringFromFile(FileUtils.TYPE_YEARS));
        } catch (IOException e){
            genresMap = new HashMap<>();
            yearsMap = new HashMap<>();
        }

        menuItems = new ArrayList<>();

        JMenuItem moviesItem = new JMenuItem("Seznam filmů");
        moviesItem.addActionListener(this);
        try {
            String movieList = FileUtils.readStringFromFile(FileUtils.TYPE_ALL);
            menuItems.add(new MenuItem("Seznam filmů", null, movieList, moviesItem));

        } catch (IOException e) {
            e.printStackTrace();
        }

        lblRok.setVisible(false);
        tfRok.setVisible(false);
        JMenuBar  menuBar = new JMenuBar();
        mainMenu = new JMenu("Menu");
        moviesItem = new JMenuItem("Seznam filmů");
        moviesItem.addActionListener(this);
        moviesSubmenu = new JMenu("Seznam filmů podle");
        genresMenu = new JMenu("žánrů");
        yearsMenu = new JMenu("roku");

       // String [] genres = {"Akční", "Sci-Fi", "Horor", "Drama"};
       // String [] years = {"1977", " 1985", "1990", "1993", "2000", "2005", "2017", "2020"};

        for(String s : genresMap.keySet()){
            JMenuItem item = new JMenuItem(s);
            item.addActionListener(this);
            menuItems.add(new MenuItem(String.format("Seznam filmů podle: %s", genresMap.get(s)),
                    "genres",
                    genresMap.get(s),
                    item));
            genresMenu.add(item);
        }

        for(String s : yearsMap.keySet()){
            JMenuItem item = new JMenuItem(s);
            item.addActionListener(this);
            menuItems.add(new MenuItem(String.format("Seznam filmů podle: %s", yearsMap.get(s)), "years", yearsMap.get(s), item));
            yearsMenu.add(item);
        }

        mainMenu.add(moviesItem);
        mainMenu.add(moviesSubmenu);

        moviesSubmenu.add(genresMenu);
        moviesSubmenu.add(yearsMenu);

        menuBar.add(mainMenu);

        setJMenuBar(menuBar);

        lblNazev.setBounds(10,10,50,25);
        tfNazev.setBounds(60, 10 , 580, 25);
        lblRok.setBounds(510,10,50,25);
        tfRok.setBounds(570, 10, 100,25);
        btnVyhledat.setBounds(680, 10 , 100, 25);
        cbRok.setBounds(60, 45, 150,25);
        lblPoster.setBounds(10,100,300,350);
        lblNazevFilmu.setBounds(350,100,200,25);
        lblRokFilmu.setBounds(350,135,200,25);
        lblTyp.setBounds(350,170, 200, 25);
        btnAdd.setBounds(580,170,160,25);
        btnSkip.setBounds(580,230,160,25);

        cbRok.addActionListener(e->{
            if(cbRok.isSelected()){
                tfRok.setVisible(true);
                lblRok.setVisible(true);
                tfNazev.setBounds(60,10,430,25);
            } else {
                tfRok.setVisible(false);
                lblRok.setVisible(false);
                tfNazev.setBounds(60,10,580,25);
                tfRok.setText("");
            }
        });

        add(lblNazev);
        add(tfNazev);
        add(lblRok);
        add(tfRok);
        add(btnVyhledat);
        add(cbRok);
        add(lblPoster);
        add(lblNazevFilmu);
        add(lblRokFilmu);
        add(lblTyp);
        add(btnAdd);
        add(btnSkip);
        btnVyhledat.addActionListener(e->{
            String nazev = tfNazev.getText();
            String rok = tfRok.getText();
            nazev = nazev.toLowerCase();
            nazev = nazev.replace(" ","+");
            System.out.println(nazev);
            String searchTitle = tfNazev.getText().trim();
            if(!searchTitle.isEmpty()){
                movies = MovieParser.parseMovieSearch(searchTitle);
                setDataToUi();
            }
        });

        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!movies.isEmpty()){
                    addMovie(movies.get(0));
                }
                setDataToUi();
            }
        });


        btnSkip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!movies.isEmpty()){
                    skipMovie(movies.get(0));
                }
                setDataToUi();
            }
        });
    }

    private void setDataToUi(){
        if(!movies.isEmpty()){
            Movie m = movies.get(0);
            lblNazevFilmu.setText(m.getTitle());
            lblRokFilmu.setText(m.getYear());
            if(true){
                lblTyp.setText(m.getType().getType());
            }
            if(!m.getPoster().equals(null)){
                lblPoster.setIcon(new ImageIcon(m.getPoster()));
            }
            setVisibility(true, lblNazevFilmu, lblRokFilmu, lblTyp, lblPoster);
            moviesSubmenu.remove(genresMenu);
            moviesSubmenu.remove(yearsMenu);
            try{
                for(String s : genresMap.keySet()){
                    JMenuItem item = new JMenuItem(s);
                    item.addActionListener(this);
                    menuItems.add(new MenuItem(
                            String.format("Seznam filmů podle žánru: %s", genresMap.get(s)),
                            "genres",
                            genresMap.get(s),
                            item
                    ));
                    genresMenu.add(item);
                }

                for(String s : yearsMap.keySet()){
                    JMenuItem item = new JMenuItem(s);
                    item.addActionListener(this);
                    menuItems.add(new MenuItem(
                            String.format("Seznam filmů podle roku: %s", yearsMap.get(s)),
                            "years",
                            yearsMap.get(s),
                            item
                    ));
                    yearsMenu.add(item);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }


        } else {
            setVisibility(false, lblNazevFilmu, lblRokFilmu, lblTyp, lblPoster);
        }
    }

    private void addMovie(Movie m){
        System.out.println(m);
        try {
            Movie movie = MovieParser.parseMovieDetail(m.getMovieID());
            for(Genre g : movie.getGenreList()){
                if(genresMap.get(g.getName()) == null){
                    genresMap.put(g.getName(), m.getMovieID());
                }
                genresMap.put(g.getName(), String.format("%s;%s", genresMap.get(g.getName()), m.getMovieID()));
            }
            if (yearsMap.get(m.getYear()) == null){
                yearsMap.put(m.getYear(), m.getMovieID());
            } else {
                yearsMap.put(m.getYear(), String.format("%s;%s", yearsMap.get(m.getYear()), m.getMovieID()));

            }
            FileUtils.saveStringToFile(m.getMovieID(), FileUtils.TYPE_ALL);
            FileUtils.saveStringToFile(FileUtils.composeData(genresMap), FileUtils.TYPE_GENRES);
            FileUtils.saveStringToFile(FileUtils.composeData(yearsMap), FileUtils.TYPE_YEARS);


            System.out.println(FileUtils.readStringFromFile(FileUtils.TYPE_ALL));
        } catch (IOException e) {
            e.printStackTrace();
        }
        movies.remove(m);
    }

    private void skipMovie(Movie m){
        System.out.println(m);
        movies.remove(m);

    }
    private void setVisibility(boolean shouldBeVisible, JComponent... component){
        for (JComponent c : component){
            c.setVisible(shouldBeVisible);
        }
    }

    private void initGui(){
        setTitle("MovieW");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new Dimension(800,600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        lblRok.setVisible(false);
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
        pane.add(lblRok, g);
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
                lblRok.setVisible(true);
            } else {
                tfRok.setVisible(false);
                lblRok.setVisible(false);
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
                "1977",
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

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        for(MenuItem menuItem : menuItems){
            if(menuItem.getItem().equals(item)){
                try {
                    new MovieListFrame(menuItem);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
