package in.galaxyofandroid.spinerdialog;

/**
 * Created by yxh on 2018/11/1.
 */

public class ContentEntity {

    private boolean isSelecte = false;
    private String content;
    private String id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public boolean isSelecte() {
        return isSelecte;
    }

    public void setSelecte(boolean selecte) {
        isSelecte = selecte;
    }
}
