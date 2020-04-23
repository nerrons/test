package cs3211.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IndexedUrlTree {
    private int storedPageNum;
    private HashSet<String> seenUrls = new HashSet<String>();
    private ArrayList<Page> allUrls = new ArrayList<Page>();

    public IndexedUrlTree(int storedPageNum) {
        this.storedPageNum = storedPageNum;
    }

    public synchronized boolean contains(String url) {
        boolean contains = seenUrls.contains(url);
        if (!contains) {
            seenUrls.add(url);
        }
        return contains;
    }

    public synchronized void addPages(List<Page> pages) {
        allUrls.addAll(pages);
    }

    public synchronized ArrayList<Page> getAllUrls() {
        return allUrls;
    }

    public synchronized int getNumUrls() {
        return allUrls.size();
    }

    public synchronized boolean hasEnoughPages() {
        return allUrls.size() >= storedPageNum;
    }
}
