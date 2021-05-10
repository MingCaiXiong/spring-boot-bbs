package top.xiongmingcai.bbs.model.request;

import javax.validation.constraints.NotBlank;

public class PostReq {
    /**
     *
     */
    @NotBlank(message = "不能为空", groups = {Update.class, Add.class})
    private String title;

    /**
     *
     */
    @NotBlank(message = "不能为空", groups = {Add.class})
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


    /**
     * 更新的时候校验分组
     */
    public interface Update {
    }

    /**
     * 添加的时候校验分组
     */
    public interface Add {
    }
}
