package cs3211.project;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlingThread implements Runnable {
    private LinkedList<String> urlsToCrawl;
    private BufferedUrlList bufferedUrlList;
    private IndexedUrlTree indexUrlTree;

    public CrawlingThread(BufferedUrlList bufferedUrlList, IndexedUrlTree indexUrlTree, LinkedList<String> urls) {
        this.urlsToCrawl = urls;
        this.bufferedUrlList = bufferedUrlList;
        this.indexUrlTree = indexUrlTree;
    }

    @Override
    public void run() {
        while (!urlsToCrawl.isEmpty()) {
            String url = urlsToCrawl.pop();
            if (indexUrlTree.contains(url)) {
                continue;
            }
            visit(url);
        }
    }

    private void visit(String url) {
        System.out.println("CT: visit: " + url);
        try {
            Document doc = Jsoup.connect(url).get();
            addToBufferedUrlList(url, doc);
            extractLinks(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addToBufferedUrlList(String url, Document doc) {
        synchronized (bufferedUrlList) {
            if (bufferedUrlList.isFull()) {
                System.out.println("CT: addToBufferedUrlList: " + url + " BUL full");
                try {
                    // wait for the list to be emptied
                    bufferedUrlList.wait();
                    bufferedUrlList.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Page page = new Page(url, doc.toString());
            bufferedUrlList.addToList(page);
        }
    }

    private void extractLinks(Document doc) {
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            urlsToCrawl.add(link.attr("abs:href"));
        }
    }
}
