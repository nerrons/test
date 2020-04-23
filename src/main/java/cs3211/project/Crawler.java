package cs3211.project;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class Crawler {
    public static volatile boolean IS_CRAWLING = false;
    private static final int BUL_SIZE = 5;
    private static final int CT_PER_BUL = 4;
    private IndexedUrlTree indexedUrlTree;
    private BufferedUrlList[] bufferedUrlLists = new BufferedUrlList[BUL_SIZE];
    private List<Thread> crawlingThreads = new ArrayList<Thread>();
    private List<Thread> indexBuildingThreads = new ArrayList<Thread>();

    public Crawler(LinkedList<String> urls, int storedPageNum) {
        this.indexedUrlTree = new IndexedUrlTree(storedPageNum);
        createThreads(urls);
    }

    private void createThreads(LinkedList<String> urls) {
        List<LinkedList<Page>> sublists = getUrlSublists(urls);
        for (int i = 0; i < bufferedUrlLists.length; i++) {
            bufferedUrlLists[i] = new BufferedUrlList();
            for (int j = 0; j < CT_PER_BUL; j++) {
                LinkedList<Page> url = sublists.get(i * CT_PER_BUL + j);
                crawlingThreads.add(new Thread(new CrawlingThread(bufferedUrlLists[i], indexedUrlTree, url)));
            }
            indexBuildingThreads.add(new Thread(new IndexBuildingThread(bufferedUrlLists[i], indexedUrlTree)));
        }
    }

    private List<LinkedList<Page>> getUrlSublists(LinkedList<String> urls) {
        int numSublists = BUL_SIZE * CT_PER_BUL;
        int sublistSize = urls.size() / numSublists;
        List<LinkedList<Page>> sublists = new LinkedList<LinkedList<Page>>();
        int i = 0;
        sublists.add(new LinkedList<Page>());
        for (String url : urls) {
            if (sublists.get(i).size() == sublistSize) {
                sublists.add(new LinkedList<Page>());
                i++;
            }
            Page page = new Page("seed", url);
            sublists.get(i).add(page);
        }
        return sublists;
    }

    public void start() {
        Crawler.IS_CRAWLING = true;
        for (Thread crawlingThread : crawlingThreads) {
            crawlingThread.start();
        }
        for (Thread indexBuildingThread : indexBuildingThreads) {
            indexBuildingThread.start();
        }
    }

    public ConcurrentHashMap<String, Page> getAllUrlsCrawled() {
        return indexedUrlTree.getAllUrls();
    }

    public int getNumUrlsCrawled() {
        return indexedUrlTree.getNumUrls();
    }

    public void shutdown() {
        Crawler.IS_CRAWLING = false;
        for (BufferedUrlList bufferedUrlList : bufferedUrlLists) {
            synchronized (bufferedUrlList) {
                bufferedUrlList.notifyAll();
            }
        }
    }
}
