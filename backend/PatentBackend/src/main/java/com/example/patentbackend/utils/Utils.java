package com.example.patentbackend.utils;

public class Utils {

    public static String formatNameOfRequestForPatent(String name) {
        StringBuilder returnSeparatedWithUnderscore= new StringBuilder();
        for (String word: name.split("/")) {
            if (returnSeparatedWithUnderscore.toString().equals("")) {
                returnSeparatedWithUnderscore = new StringBuilder(word);
            } else {
                returnSeparatedWithUnderscore.append("-").append(word);
            }
        }
        returnSeparatedWithUnderscore.append(".xml");
        return returnSeparatedWithUnderscore.toString();
    }
}
