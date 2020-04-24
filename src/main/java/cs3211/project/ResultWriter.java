package cs3211.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ResultWriter {
    private static String htmlDirName = "html";
    private int numPagesWritten = 0;
    private int storedPageNum;
    private String outputFile;

    public ResultWriter(String outputFile, int storedPageNum) {
        this.outputFile = outputFile;
        this.storedPageNum = storedPageNum;
        try {
            new File(htmlDirName).mkdirs();
        } catch (Exception e) {
            System.err.println("[ERROR] ResultWriter(): " + e.getMessage());
        }
    }

    public void write(ArrayList<Page> result) {
        try {
            System.out.println("Writing to " + outputFile + "...");
            writeToFile(result);
        } catch (IOException e) {
            System.err.println("ResultWriter.writeToFile error: " + e.getMessage());
            writeOutStdout(result);
        }
        System.out.println("Written " + result.size() + " new URLs to " + outputFile);
    }

    private void writeToFile(ArrayList<Page> result) throws IOException {
        FileWriter writer = new FileWriter(outputFile, true);
        for (Page page : result) {
            String line = page.getParentUrl() + " --> " + page.getUrl() + " : " + writeContentAndGetStatus(page) + '\n';
            writer.write(line);
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
            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("[ERROR] ResultWriter.writeContentToFile: " + fileName + ": " + e.getMessage());
        }
    }

    private void writeOutStdout(ArrayList<Page> result) {
        for (Page page : result) {
            System.out.println(page.getParentUrl() + " --> " + page.getUrl());
        }
    }
}