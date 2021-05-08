package top.xiongmingcai.bbs.service;

import top.xiongmingcai.bbs.model.pojo.User;

/**
 * Service：
 *
 * @author xmc000
 * @date 2021-05-08 19:55:49
 */

public interface UserService {


    User verifyLogin(String usernaem, String password);
}