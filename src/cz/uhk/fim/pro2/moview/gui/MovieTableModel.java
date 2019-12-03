package cz.uhk.fim.pro2.moview.gui;

import cz.uhk.fim.pro2.moview.model.Movie;
import cz.uhk.fim.pro2.moview.model.Rating;
import cz.uhk.fim.pro2.moview.utils.DataHandler;

import javax.swing.table.*;
import java.util.List;

public class MovieTableModel extends AbstractTableModel {

    private List<Movie> movies;

    public JTableHeader initHeader(){
        JTableHeader header = new JTableHeader();
        TableColumnModel columnModel = new DefaultTableColumnModel();
        for(String s : getColumnNames()){
            TableColumn column = new TableColumn();
            column.setHeaderValue(s);
            columnModel.addColumn(column);
        }
        header.setColumnModel(columnModel);
        return header;
    }

    public void setMovies(List<Movie> movies ) {
        this.movies = movies;
        fireTableDataChanged(); // Vola se pokud je nejaka zmena v tabulce---Propagace zmeny
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Movie movie = movies.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return movie.getTitle();
            case 1:
                return movie.getYear();
            case 2:
                return DataHandler.getStringFromDate(movie.getReleaseDate());
            case 3:
                return movie.getRuntime();
            case 4:
                return movie.getType();
            case 5:
                return movie.getRatings().size() > 0 ? movie.getRatings().get(0).getValue(): "";
            case 6:
                return movie.getRatings().size() > 1 ? movie.getRatings().get(1).getValue(): "";
            case 7:
                return movie.getRatings().size() > 2 ? movie.getRatings().get(2).getValue(): "";
        }
        return null;
    }

    public String [] getColumnNames(){
        String [] columns = new String[getColumnCount()];
        for(int i = 0; i < columns.length; i++){
            columns[i] = getColumnName(i);
        }
        return columns;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Název";
            case 1:
                return "Rok";
            case 2:
                return "Datum vydání";
            case 3:
                return "Doba trvání";
            case 4:
                return "Typ";
            case 5:
                return "Hodnocení 1";
            case 6:
                return "Hodnocení 2";
            case 7:
                return "Hodnocení 3";

            default:
                return "?";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return String.class;
            default:
                return Object.class;

        }
    }
}
