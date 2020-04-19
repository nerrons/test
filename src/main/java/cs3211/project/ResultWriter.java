package cs3211.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

public class ResultWriter {
    private int numPagesWritten = 0;
    private int storedPageNum;
    private String outputFile;
    private ConcurrentHashMap<String, Page> result;

    public ResultWriter(String outputFile, int storedPageNum, ConcurrentHashMap<String, Page> result) {
        this.outputFile = outputFile;
        this.storedPageNum = storedPageNum;
        this.result = result;
    }

    public void write() {
        try {
            System.out.println("Writing to " + outputFile + "...");
            writeToFile();
        } catch (IOException e) {
            System.err.println("ResultWriter.writeToFile error: " + e.getMessage());
            writeOutStdout();
        }
        System.out.println("Crawled " + result.size() + " URLs");
    }

    private void writeToFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");
        for (Page page : result.values()) {
            String line = page.getParentUrl() + " --> " + page.getUrl() + " : " + writeContentAndGetStatus(page);
            writer.println(line);
        }
        writer.close();
    }

    private String writeContentAndGetStatus(Page page) {
        switch (page.getStatus()) {
            case Dead:
                return "dead-url";
            case Ignored:
                return "ignored";
            case Crawled:
                if (numPagesWritten >= storedPageNum)
                    return "ignored";
                String fileName = "page" + numPagesWritten + ".html";
                numPagesWritten++;
                return fileName;
            default:
                return "";
        }
    }

    private void writeOutStdout() {
        for (Page page : result.values()) {
            System.out.println(page.getParentUrl() + " --> " + page.getUrl());
        }
    }
}