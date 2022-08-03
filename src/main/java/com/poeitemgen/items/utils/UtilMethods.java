package com.poeitemgen.items.utils;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class UtilMethods {

    public static String readFileAsString(String file) throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            return 0;
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int parseRandomValueInRange(String stringValue) {

        String[] valuesAsStrings = stringValue.split("-");

        return getRandomNumberInRange(Integer.parseInt(valuesAsStrings[0]), Integer.parseInt(valuesAsStrings[1]));
    }
}
