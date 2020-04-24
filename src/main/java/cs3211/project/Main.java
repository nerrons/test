package cs3211.project;

import java.time.Duration;
import java.util.LinkedList;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names = { "-time" }, converter = TimeConverter.class)
    private Duration time = Duration.ofMinutes(1);
    @Parameter(names = { "-input" })
    private String input = "seed.txt";
    @Parameter(names = { "-output" })
    private String output = "res.txt";
    @Parameter(names = { "-storedPageNum" })
    private int storedPageNum = 100;
    @Parameter(names = { "-debug" })
    public static boolean debug = false;

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        main.run();
    }

    public void run() {
        SeedReader reader = new SeedReader(input);
        LinkedList<String> baseUrls = reader.read();
        if (baseUrls.isEmpty()) {
            System.out.println("No seed url provided");
            return;
        }

        ResultWriter writer = new ResultWriter(output, storedPageNum);

        Crawler crawler = new Crawler(baseUrls, storedPageNum);
        crawler.start();

        long start = System.currentTimeMillis();
        long end = start + time.toMillis();
        long oneHourInMillis = 60 * 60 * 1000;
        long timeToWriteUrls = start + oneHourInMillis;
        while (System.currentTimeMillis() < end) {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() > timeToWriteUrls) {
                // write urls to file every hour
                writer.write(crawler.getNewUrlsCrawled());
                timeToWriteUrls = System.currentTimeMillis() + oneHourInMillis;
            }
            // print number of URLs crawled every minute
            System.out.println("[INFO] number of URLs crawled: " + crawler.getNumUrlsCrawled());
        }

        crawler.shutdown();

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writer.write(crawler.getNewUrlsCrawled());
        System.out.println("[INFO] total number of URLs crawled: " + crawler.getNumUrlsCrawled());
    }
}
