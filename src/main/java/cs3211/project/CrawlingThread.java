package cs3211.project;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlingThread implements Runnable {
    private LinkedList<Page> urlsToCrawl;
    private BufferedUrlList bufferedUrlList;
    private IndexedUrlTree indexUrlTree;

    public CrawlingThread(BufferedUrlList bufferedUrlList, IndexedUrlTree indexUrlTree, LinkedList<Page> urls) {
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
            Page page = urlsToCrawl.pop();
            if (indexUrlTree.contains(page.getUrl())) {
                continue;
            }
            visit(page);
        }
    }

    private void visit(Page page) {
        try {
            Document doc = Jsoup.connect(page.getUrl()).get();
            addToBufferedUrlList(page, doc);
            extractLinks(page.getUrl(), doc);
        } catch (IOException e) {
            System.err.println("CrawlingThread.visit error: " + page.getUrl());
            e.printStackTrace();
        }
    }

    private void addToBufferedUrlList(Page page, Document doc) {
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
            page.setContent(doc.toString());
            bufferedUrlList.addToList(page);
        }
    }

    private void extractLinks(String parentUrl, Document doc) {
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            Page page = new Page(parentUrl, link.attr("abs:href"));
            urlsToCrawl.add(page);
        }
    }
}
