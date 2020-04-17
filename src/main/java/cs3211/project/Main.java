package cs3211.project;

import java.time.Duration;

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
    @Parameter(names = { "-numOfThreads" })
    int numOfThreads = 6; // default num of threads

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }

    public void run() {
        String baseUrl = "https://www.google.com/"; // TODO: update url to use input file
        Crawler crawler = new Crawler(baseUrl, numOfThreads);
        long start = System.currentTimeMillis();
        crawler.start();
        crawler.join();
        long totalTimeInMS = System.currentTimeMillis() - start;
        crawler.shutdown();

        System.out.println(crawler.getSeenLinks() + " links processed in " + (double) totalTimeInMS / 1000 + "s");
    }

}
