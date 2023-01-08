package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLWriter {
    public static void write(String fileName, String text) {
        try {
            new File(fileName);
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
