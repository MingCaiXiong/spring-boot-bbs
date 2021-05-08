package top.xiongmingcai.bbs.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.xiongmingcai.bbs.service.UserService;

import javax.annotation.Resource;

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
                        @RequestParam(name = "password", required = false, defaultValue = "") String password) {
        if (usernaem.equals("")) {
            return failed("用户名不能为空");
        }
        if (password.equals("")) {
            return failed("密码不能为空");
        }

        userService.verifyLogin(usernaem, password);
        return success(null);
    }


}
