package com.afflorezc.utils;

import java.util.Random;

public class Images {
    public static String[] propertiesImages = {"apartment-0", "house-0", "small-apt-0"};

    public static String randomImage() {
        Random rand = new Random();

        int imagePosition = rand.nextInt(propertiesImages.length);

        String nameImage = propertiesImages[imagePosition];

        int index;

        switch (nameImage) {
            case "apartment-0":
                index = rand.nextInt(1, 10);
                nameImage += index+".jpg";

                break;

            case "house-0":
                index = rand.nextInt(1, 11);
                nameImage += index+".jpg";
                break;
                
            case "small-apt-0":
                index = rand.nextInt(1, 8);
                nameImage += index+".jpg";
                break;    
        
            default:
                nameImage = "apartment-0";
                index = rand.nextInt(1, 10);
                nameImage += index+".jpg";

                break;
        }

        return nameImage;
    }

}
