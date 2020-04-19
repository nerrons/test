package cs3211.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names = { "-time" }, converter = TimeConverter.class)
    Duration time = Duration.ofMinutes(1);
    @Parameter(names = { "-input" })
    String input = "seed.txt";
    @Parameter(names = { "-output" })
    String output = "res.txt";
    @Parameter(names = { "-storedPageNum" })
    int storedPageNum = 1000;

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
        PrintWriter writer;
        try {
            writer = new PrintWriter(output, "UTF-8");
            System.out.println("Writing to " + output + "...");
            for (Page page : allUrls.values()) {
                writer.println(page.getParentUrl() + " --> " + page.getUrl());
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Main.presentResult error: " + e.getMessage());
            printResults(allUrls);
        }
    }

    private void printResults(ConcurrentHashMap<String, Page> allUrls) {
        for (Page page : allUrls.values()) {
            System.out.println(page.getParentUrl() + " --> " + page.getUrl());
        }
        System.out.println("Crawled " + allUrls.size() + " URLs in " + time.toSeconds() + " seconds");
    }
}
