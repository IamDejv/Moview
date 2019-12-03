package cz.uhk.fim.pro2.moview.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileUtils {
    private static final String FAVORITE_MOVIES = "movies.txt";
    private static final String GENRES = "genres.txt";
    private static final String YEARS = "years.txt";

    public static final int TYPE_ALL = 0;
    public static final int TYPE_GENRES = 1;
    public static final int TYPE_YEARS = 2;

    public static String composeData(HashMap<String, String> dataMap){
        return "";
    }

    public static HashMap<String, String> decomposeData(String data){
        HashMap<String, String> dataMap = new HashMap<>();
        String [] withoutN = data.split("\n");
        String [] raw = new String[withoutN.length];
        for (int i = 0 ; i < withoutN.length; i++) {
            raw[i] = withoutN[i];
        }
        //Skoncil jsem u raw data kde jsou data v poli a staci je rozlousknout na pul a jaistit dostani do mapy

        String [] genres = data.split("\\:");






        return dataMap;
    }

    public static  String [] decomposeCategory(String data){
        return data.split(";");
    }

    public static void saveStringToFile(String data, int type) throws IOException {
        File file;
        switch (type){
            case TYPE_GENRES: file = new File(GENRES);
            break;
            case TYPE_YEARS: file = new File(YEARS);
            break;
            default: file = new File(FAVORITE_MOVIES);
        }
        FileWriter writer = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(String.format("%s;", data));
        bufferedWriter.close();
        writer.close();
    }

    private static String readStringFromFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    public static String readStringFromFile(int type) throws IOException {
        switch (type){
            case TYPE_GENRES: return readStringFromFile(GENRES);
            case TYPE_YEARS: return readStringFromFile(YEARS);
            default: return readStringFromFile(FAVORITE_MOVIES);
        }
    }
}
