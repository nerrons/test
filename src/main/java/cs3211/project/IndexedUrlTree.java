package cs3211.project;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class IndexedUrlTree {
    private ConcurrentHashMap<String, String> allUrls = new ConcurrentHashMap<String, String>();

    public synchronized boolean contains(String url) {
        boolean contains = allUrls.containsKey(url);
        if (!contains) {
            // put "" into the hash table so other threads will not crawl this url
            allUrls.put(url, "");
        }
        return contains;
    }

    public void addPages(List<Page> pages) {
        for (Page page : pages) {
            String url = page.getUrl();
            String content = page.getContent();
            synchronized (this) {
                // sanity check, should NEVER happen
                if (allUrls.get(url) != null && allUrls.get(url) != "") {
                    System.out.println("URL " + url + " already exists in IUT!");
                }
                allUrls.put(url, content);
            }
        }
    }

    public ConcurrentHashMap<String, String> getAllUrls() {
        return allUrls;
    }
}
