package cs3211.project;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class Crawler {
    private static final int BUL_SIZE = 1; // 3;
    private IndexedUrlTree indexedUrlTree = new IndexedUrlTree();
    private BufferedUrlList[] bufferedUrlLists = new BufferedUrlList[BUL_SIZE];
    private List<Thread> crawlingThreads = new ArrayList<Thread>();
    private List<Thread> indexBuildingThreads = new ArrayList<Thread>();

    public Crawler(LinkedList<String> urls) {
        for (int i = 0; i < bufferedUrlLists.length; i++) {
            bufferedUrlLists[i] = new BufferedUrlList();
            crawlingThreads.add(new Thread(new CrawlingThread(bufferedUrlLists[i], indexedUrlTree, urls)));
            indexBuildingThreads.add(new Thread(new IndexBuildingThread(bufferedUrlLists[i], indexedUrlTree)));
        }
    }

    public void start() {
        for (Thread crawlingThread : crawlingThreads) {
            crawlingThread.start();
        }
        for (Thread indexBuildingThread : indexBuildingThreads) {
            indexBuildingThread.start();
        }
    }

    public ConcurrentHashMap<String, String> getAllUrlsCrawled() {
        return indexedUrlTree.getAllUrls();
    }

    public void shutdown() {
        for (Thread crawlingThread : crawlingThreads) {
            crawlingThread.interrupt();
        }
        for (Thread indexBuildingThread : indexBuildingThreads) {
            indexBuildingThread.interrupt();
        }
    }
}
