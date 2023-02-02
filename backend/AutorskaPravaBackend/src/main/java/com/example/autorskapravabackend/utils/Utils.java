package com.example.autorskapravabackend.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public static String formatDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy.");
        return formatter.format(date);
    }

    public static XMLGregorianCalendar stringToXMLGregorian(String stringDate) throws DatatypeConfigurationException, ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(stringDate);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }

    public static XMLGregorianCalendar dateToXMLGregorian(Date date) throws DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }
}
