package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.Movie;
import cz.uhk.fim.pro2.moview.utils.FileUtils;
import cz.uhk.fim.pro2.moview.utils.MovieParser;
import cz.uhk.fim.pro2.moview.utils.TableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MovieListFrame extends JFrame {

    private MovieTableModel model;
    private String [] ids;
    private String parameter;

    public MovieListFrame(MenuItem menuItem) throws IOException {
        parameter = menuItem.getParameter();
        ids = FileUtils.readStringFromFile(FileUtils.TYPE_ALL).split(";");
        initFrame(menuItem);
        initUI(parameter);
    }

    private void initFrame(MenuItem parameter){
        setTitle(parameter.getType());
        setSize(800,600);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initUI(String values) throws IOException {


        HashMap<String, String> dataMap = FileUtils.decomposeData(FileUtils.readStringFromFile(FileUtils.TYPE_GENRES));

        System.out.println(FileUtils.composeData(dataMap));


        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(panel);
        model = new MovieTableModel();
        model.setMovies(moviesToList(ids));
        JTable table = new JTable();
        table.setModel(model);
        panel.add(table);
        TableUtils.setJTableColumnsWidth(table,getWidth(),35,5,10,10,10,10,10,10);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(table));
        add(tablePanel);
    }

   private List<Movie> moviesToList(String [] ids){
        List<Movie> movies = new ArrayList<>();
        for(String id : ids){
            movies.add(MovieParser.parseMovieDetail(id));
        }
        return movies;
   }
}
