package cs3211.project;

public final class Page {
    private String url;
    private String content;

    public Page(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }

    public String getContent() {
        return this.content;
    }
}
