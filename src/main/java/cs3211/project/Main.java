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

        Crawler crawler = new Crawler(baseUrls, storedPageNum);
        crawler.start();

        try {
            Thread.sleep(time.toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        crawler.shutdown();
        ResultWriter writer = new ResultWriter(output, storedPageNum, crawler.getAllUrlsCrawled());
        writer.write();
    }
}
