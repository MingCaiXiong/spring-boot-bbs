package top.xiongmingcai.bbs.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.xiongmingcai.bbs.common.Constant;
import top.xiongmingcai.bbs.model.pojo.User;
import top.xiongmingcai.bbs.model.request.UserReq;
import top.xiongmingcai.bbs.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public Object login(@Valid @RequestBody(required = false) UserReq userReq, HttpSession session) {

        User loginUser = userService.verifyLogin(userReq.getUsername(), userReq.getPassword());
        session.setAttribute(Constant.loginUser, loginUser);
        return success(loginUser);


    }


}
