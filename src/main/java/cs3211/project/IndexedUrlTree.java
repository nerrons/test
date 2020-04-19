package cs3211.project;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class IndexedUrlTree {
    private int storedPageNum;
    private ConcurrentHashMap<String, Page> allUrls = new ConcurrentHashMap<String, Page>();

    public IndexedUrlTree(int storedPageNum) {
        this.storedPageNum = storedPageNum;
    }

    public synchronized boolean contains(String url) {
        return allUrls.containsKey(url);
    }

    public void addPages(List<Page> pages) {
        for (Page page : pages) {
            String url = page.getUrl();
            synchronized (this) {
                if (!allUrls.containsKey(url)) {
                    allUrls.put(url, page);
                }
            }
        }
    }

    public synchronized ConcurrentHashMap<String, Page> getAllUrls() {
        return allUrls;
    }

    public synchronized boolean hasEnoughPages() {
        return allUrls.size() >= storedPageNum;
    }
}
