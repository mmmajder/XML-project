package com.example.autorskapravabackend.utils;

public class Utils {

    public static String formatNameOfRequest(String name, String extension) {
        StringBuilder returnSeparatedWithUnderscore = new StringBuilder();
        for (String word : name.split("/")) {
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
