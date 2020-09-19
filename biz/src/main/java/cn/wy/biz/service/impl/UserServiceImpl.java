package cn.wy.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.wy.biz.service.intf.UserInfoService;
import cn.wy.biz.service.intf.UserService;
import cn.wy.dal.dao.intf.UserDao;
import cn.wy.pojo.User;
import cn.wy.pojo.UserInfo;

/**
 * Created by leslie on 2018/5/13.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

    @Autowired // by type: cn.wy.dal.dao.intf.UserDao
    private UserDao         userDao;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int addUserAndUserInfo(User user, UserInfo userInfo) {
        userDao.addUser(user);
        userInfoService.addUserInfo(userInfo);
        return 0;
    }

    @Override
    public int addBothCalleeRuntimeException(User user, UserInfo userInfo) {
        userDao.addUser(user);
        try {
            userInfoService.addUserInfoWithRuntimeException(userInfo);
        }catch(Exception e){

        }
        return 0;
    }

    @Override
    public int addBothCallerRuntimeException(User user, UserInfo userInfo) {
        userDao.addUser(user);
        userInfoService.addUserInfo(userInfo);
        throw new RuntimeException();
    }

}
