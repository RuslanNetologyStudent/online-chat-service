package Setting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Setting {
    public static final String nameFile = "src/main/resources/settings.txt";

    public static int readPort() {
        File file = new File(nameFile);
        String line;
        int lineInt = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                lineInt = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineInt;
    }
}