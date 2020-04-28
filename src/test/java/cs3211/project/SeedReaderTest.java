package cs3211.project;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class SeedReaderTest {

    @Test
    public void read() {

        String seedFile = "seed.txt";
        LinkedList<String> baseUrls = new LinkedList<String>();

        SeedReader seedReader=new SeedReader(seedFile);

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

        LinkedList<String> baseUrlsReal= seedReader.read();
        assertEquals(baseUrls,baseUrlsReal);;
    }
}
