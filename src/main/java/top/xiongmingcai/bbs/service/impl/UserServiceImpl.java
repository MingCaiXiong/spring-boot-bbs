package top.xiongmingcai.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.xiongmingcai.bbs.model.dao.UserMapper;
import top.xiongmingcai.bbs.model.pojo.User;
import top.xiongmingcai.bbs.service.UserService;

import javax.annotation.Resource;


/**
 * Service：
 *
 * @author xmc000
 * @date 2021-05-08 19:55:49
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void verifyLogin(String usernaem, String password) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", usernaem);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("user = " + user);
        if (user == null) {
            throw new RuntimeException("用户名不存在!");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码输入有误!");
        }
    }
}