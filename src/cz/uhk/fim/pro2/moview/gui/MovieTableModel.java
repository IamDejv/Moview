package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.utils.MovieParser;

import javax.swing.table.AbstractTableModel;

public class MovieTableModel extends AbstractTableModel {

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    public void setMovies(String rok, String [] ids){
        for(String id : ids){
            if(rok.equals(MovieParser.parseMovieDetail(id).getYear())){
                System.out.println();
                System.out.println(MovieParser.parseMovieDetail(id));
            }
        }

    }
}
