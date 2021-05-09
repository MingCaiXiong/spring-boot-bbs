package top.xiongmingcai.bbs.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.xiongmingcai.bbs.common.Constant;
import top.xiongmingcai.bbs.model.pojo.User;
import top.xiongmingcai.bbs.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Controller：
 *
 * @author xmc000
 * @date 2021-05-08 19:55:49
 */
@RestController
public class UserController extends ApiController {
    @Resource
    private UserService userService;


    @PostMapping("/login")
    public Object login(@RequestParam(name = "username", required = false, defaultValue = "") String usernaem,
                        @RequestParam(name = "password", required = false, defaultValue = "") String password,
                        HttpSession session) {
        User loginUser = userService.verifyLogin(usernaem, password);
        session.setAttribute(Constant.loginUser, loginUser);
        return success(loginUser);
    }


}
