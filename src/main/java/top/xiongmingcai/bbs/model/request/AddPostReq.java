package top.xiongmingcai.bbs.model.request;

import javax.validation.constraints.NotBlank;

public class AddPostReq {
    /**
     *
     */
    @NotBlank(message = "不能为空。")
    private String title;

    /**
     *
     */
    @NotBlank(message = "不能为空。")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
