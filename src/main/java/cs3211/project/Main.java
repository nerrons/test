package cs3211.project;

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
    @Parameter(names = { "-numOfThreads" })
    int numOfThreads = 6; // default num of threads

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }

    public void run() {
        // TODO: update url to use input file
        LinkedList<String> baseUrls = new LinkedList<String>();
        baseUrls.add("https://www.google.com/");
        Crawler crawler = new Crawler(baseUrls);
        crawler.start();

        try {
            Thread.sleep(time.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // crawler.shutdown();
        ConcurrentHashMap<String, String> allUrls = crawler.getAllUrlsCrawled();

        for (String k : allUrls.keySet()) {
            System.out.println(k);
        }
    }
}
