package cs3211.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names = { "-time" }, converter = TimeConverter.class)
    Duration time;
    @Parameter(names = { "-input" })
    String input;
    @Parameter(names = { "-output" })
    String output;
    @Parameter(names = { "-storedPageNum" })
    int storedPageNum;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }

    public void run() {
        LinkedList<String> baseUrls = getBaseUrlsFromFile();
        Crawler crawler = new Crawler(baseUrls);
        crawler.start();

        try {
            Thread.sleep(time.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        crawler.shutdown();
        ConcurrentHashMap<String, Page> allUrls = crawler.getAllUrlsCrawled();
        presentResults(allUrls);
    }

    private LinkedList<String> getBaseUrlsFromFile() {
        if (input == null) {
            input = "seed.txt"; // default value
        }
        LinkedList<String> baseUrls = new LinkedList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            String line = br.readLine();
            while (line != null) {
                baseUrls.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File " + input + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseUrls;
    }

    private void presentResults(ConcurrentHashMap<String, Page> allUrls) {
        // TODO: output result to file
        for (Page page : allUrls.values()) {
            System.out.println(page.getParentUrl() + " --> " + page.getUrl());
        }
        System.out.println("Crawled " + allUrls.size() + " URLs in " + time.toSeconds() + " seconds");
    }
}
