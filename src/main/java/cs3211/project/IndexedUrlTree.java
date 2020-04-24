package cs3211.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IndexedUrlTree {
    private int storedPageNum;
    private HashSet<String> allSeenUrls = new HashSet<String>();
    private ArrayList<Page> newUrls = new ArrayList<Page>();

    public IndexedUrlTree(int storedPageNum) {
        this.storedPageNum = storedPageNum;
    }

    public synchronized boolean contains(String url) {
        boolean contains = allSeenUrls.contains(url);
        if (!contains) {
            allSeenUrls.add(url);
        }
        return contains;
    }

    public synchronized void addPages(List<Page> pages) {
        newUrls.addAll(pages);
    }

    public synchronized ArrayList<Page> getAndClearNewUrls() {
        ArrayList<Page> result = new ArrayList<Page>(newUrls);
        newUrls.clear();
        return result;
    }

    public synchronized int getNumUrls() {
        return allSeenUrls.size();
    }

    public synchronized boolean hasEnoughPages() {
        return allSeenUrls.size() >= storedPageNum * 1.1;
    }
}
