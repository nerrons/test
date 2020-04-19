package cs3211.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

public class ResultWriter {
    private String outputFile;
    private ConcurrentHashMap<String, Page> result;

    public ResultWriter(String outputFile, ConcurrentHashMap<String, Page> result) {
        this.outputFile = outputFile;
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
            writer.println(page.getParentUrl() + " --> " + page.getUrl());
        }
        writer.close();
    }

    private void writeOutStdout() {
        for (Page page : result.values()) {
            System.out.println(page.getParentUrl() + " --> " + page.getUrl());
        }
    }
}