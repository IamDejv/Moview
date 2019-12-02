package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.Movie;
import cz.uhk.fim.pro2.moview.utils.FileUtils;
import cz.uhk.fim.pro2.moview.utils.HttpHandler;
import cz.uhk.fim.pro2.moview.utils.MovieParser;
import cz.uhk.fim.pro2.moview.utils.TableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieListFrame extends JFrame {

    private MovieTableModel model;
    private String [] ids;

    public MovieListFrame(String parameter) throws IOException {
        ids = FileUtils.readStringFromFile("movies.txt").split(";");
        initFrame(parameter);
        initUI(parameter);
    }

    private void initFrame(String parameter){
        setTitle(parameter);
        setSize(800,600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initUI(String parameter){
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(panel);
        model = new MovieTableModel();
        model.setMovies(moviesToList(ids));
        JTable table = new JTable();
        table.setModel(model);
        table.setTableHeader(model.initHeader());
        panel.add(table);
        TableUtils.setJTableColumnsWidth(table,getWidth(),35,5,10,10,10,10,10,10);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        add(panel);
    }

   private List<Movie> moviesToList(String [] ids){
        List<Movie> movies = new ArrayList<>();
        for(String id : ids){
            movies.add(MovieParser.parseMovieDetail(id));
        }
        return movies;
   }
}
