package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.utils.FileUtils;

import javax.swing.*;
import java.io.IOException;

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
        setSize(640,480);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

   private void initUI(String parameter){
        JTable table = new JTable();
        JPanel panel = new JPanel();
        panel.add(table);
        model = new MovieTableModel();
        model.setMovies(parameter, ids);
        add(panel);

   }
}
