package top.xiongmingcai.bbs.model.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UserReq {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户密码不能为空")
    @Length(min = 5, max = 9, message = "长度不符合要求")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
