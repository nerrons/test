package cs3211.project;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class IndexedUrlTree {
    private int storedPageNum;
    private HashSet<String> urlsInBUL = new HashSet<String>();
    private ConcurrentHashMap<String, Page> allUrls = new ConcurrentHashMap<String, Page>();

    public IndexedUrlTree(int storedPageNum) {
        this.storedPageNum = storedPageNum;
    }

    public synchronized boolean contains(String url) {
        boolean contains = allUrls.containsKey(url) || urlsInBUL.contains(url);
        if (!contains) {
            urlsInBUL.add(url);
        }
        return contains;
    }

    public void addPages(List<Page> pages) {
        for (Page page : pages) {
            String url = page.getUrl();
            synchronized (this) {
                if (!allUrls.containsKey(url)) {
                    allUrls.put(url, page);
                    urlsInBUL.remove(url);
                }
            }
        }
    }

    public synchronized ConcurrentHashMap<String, Page> getAllUrls() {
        return allUrls;
    }

    public synchronized int getNumUrls() {
        return allUrls.size();
    }

    public synchronized boolean hasEnoughPages() {
        return allUrls.size() >= storedPageNum;
    }
}
