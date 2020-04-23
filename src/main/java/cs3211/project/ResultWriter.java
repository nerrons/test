package cs3211.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ResultWriter {
    private static String htmlDirName = "html";
    private int numPagesWritten = 0;
    private int storedPageNum;
    private String outputFile;
    private ArrayList<Page> result;

    public ResultWriter(String outputFile, int storedPageNum, ArrayList<Page> result) {
        this.outputFile = outputFile;
        this.storedPageNum = storedPageNum;
        this.result = result;
    }

    public void write() {
        try {
            new File(htmlDirName).mkdirs();
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
        for (Page page : result) {
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
                String fileName = htmlDirName + File.separator + "page" + numPagesWritten + ".html";
                writeContentToFile(fileName, page.getContent());
                numPagesWritten++;
                return fileName;
            default:
                return "";
        }
    }

    private void writeContentToFile(String fileName, String content) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("[ERROR] ResultWriter.writeContentToFile: " + fileName + ": " + e.getMessage());
        }
    }

    private void writeOutStdout() {
        for (Page page : result) {
            System.out.println(page.getParentUrl() + " --> " + page.getUrl());
        }
    }
}