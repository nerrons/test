package cs3211.project;

public class IndexBuildingThread implements Runnable {
    private BufferedUrlList bufferedUrlList;
    private IndexedUrlTree indexUrlTree;

    public IndexBuildingThread(BufferedUrlList bufferedUrlList, IndexedUrlTree indexUrlTree) {
        this.bufferedUrlList = bufferedUrlList;
        this.indexUrlTree = indexUrlTree;
    }

    @Override
    public void run() {
        while (true) {
            if (!Crawler.IS_CRAWLING) {
                return;
            }
            synchronized (bufferedUrlList) {
                if (!bufferedUrlList.isFull()) {
                    try {
                        // wait for the list to be full
                        bufferedUrlList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                indexUrlTree.addPages(bufferedUrlList.getPages());
                bufferedUrlList.clearList();
                bufferedUrlList.notifyAll();
            }
        }
    }
}
