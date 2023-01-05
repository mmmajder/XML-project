package com.example.patentbackend.utils;

public class Utils {

    public static String formatNameOfRequestForPatent(String name, String extension) {
        StringBuilder returnSeparatedWithUnderscore= new StringBuilder();
        for (String word: name.split("/")) {
            if (returnSeparatedWithUnderscore.toString().equals("")) {
                returnSeparatedWithUnderscore = new StringBuilder(word);
            } else {
                returnSeparatedWithUnderscore.append("-").append(word);
            }
        }
        returnSeparatedWithUnderscore.append(extension);
        return returnSeparatedWithUnderscore.toString();
    }
}
