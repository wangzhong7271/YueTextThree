package bean;

/**
 * Created by 笔片 on 2017/11/23.
 */

public class MessageEvent {
    private String url;

    @Override
    public String toString() {
        return "MessageEvent{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MessageEvent(String url) {

        this.url = url;
    }
}
