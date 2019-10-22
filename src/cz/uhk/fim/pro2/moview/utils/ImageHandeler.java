package cz.uhk.fim.pro2.moview.utils;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageHandeler {

    public static Image getImageFromUrl(String imageUrl){
        Image image = null;
        try {
            URL url = new URL(imageUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return image;

    }
}
