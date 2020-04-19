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
            if (!Crawler.IS_CRAWLING) {
                return;
            }
            String url = urlsToCrawl.pop();
            if (indexUrlTree.contains(url)) {
                continue;
            }
            visit(url);
        }
    }

    private void visit(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            addToBufferedUrlList(url, doc);
            extractLinks(doc);
        } catch (IOException e) {
            System.err.println("CrawlingThread.visit error: " + url);
            e.printStackTrace();
        }
    }

    private void addToBufferedUrlList(String url, Document doc) {
        synchronized (bufferedUrlList) {
            if (bufferedUrlList.isFull()) {
                try {
                    // notify all IBT to copy BUL to IUT and
                    // wait for the list to be emptied
                    bufferedUrlList.notifyAll();
                    bufferedUrlList.wait();
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
