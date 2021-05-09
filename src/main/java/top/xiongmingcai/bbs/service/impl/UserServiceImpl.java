package top.xiongmingcai.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.xiongmingcai.bbs.exception.BussinessException;
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
    public User verifyLogin(String usernaem, String password) {
        if (usernaem.equals("")) {
            throw new BussinessException("用户名不能为空!");
        }
        if (password.equals("")) {
            throw new BussinessException("密码不能为空!");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", usernaem);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println("user = " + user);
        if (user == null) {
            throw new BussinessException("用户名不存在!");
        }

        if (!user.getPassword().equals(password)) {
            throw new BussinessException("密码输入有误!");
        }
        user.setPassword(null);
        return user;
    }
}