package cs3211.project;

import java.util.ArrayList;
import java.util.List;

public class BufferedUrlList {
    private List<Page> pages = new ArrayList<Page>();
    public static final int LIST_LIMIT = 10;

    public List<Page> getPages() {
        return pages;
    }

    public synchronized int getListSize() {
        return pages.size();
    }

    public boolean isFull() {
        return pages.size() >= LIST_LIMIT;
    }

    public void clearList() {
        pages.clear();
    }

    public synchronized void addToList(Page page) {
        pages.add(page);
        System.out.println("BUL: addToList: " + pages.size());
    }
}
