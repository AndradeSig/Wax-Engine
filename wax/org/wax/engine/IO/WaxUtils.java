package org.wax.engine.IO;

import org.wax.engine.Wax;

import java.io.*;
import java.util.Objects;

public class WaxUtils {

    public static String readFile(String path)
    {
        InputStream is = Wax.class.getClassLoader().getResourceAsStream(path);
        StringBuilder result = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(is));
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Error to read the file! The File not Exists: " + path);
            e.printStackTrace();
        }
        return result.toString();
    }
}
