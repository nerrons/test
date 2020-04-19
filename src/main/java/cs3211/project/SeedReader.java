package cs3211.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class SeedReader {
    private String seedFile;

    public SeedReader(String seedFile) {
        this.seedFile = seedFile;
    }

    public LinkedList<String> read() {
        LinkedList<String> baseUrls = new LinkedList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(seedFile));
            String line = br.readLine();
            while (line != null) {
                baseUrls.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("[ERROR] File " + seedFile + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseUrls;
    }
}