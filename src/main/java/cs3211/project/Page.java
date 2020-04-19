package cs3211.project;

public final class Page {
    private String parentUrl;
    private String url;
    private String content = "";

    public Page(String parentUrl, String url) {
        this.parentUrl = parentUrl;
        this.url = url;
    }

    public String getParentUrl() {
        return this.parentUrl;
    }

    public String getUrl() {
        return this.url;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
