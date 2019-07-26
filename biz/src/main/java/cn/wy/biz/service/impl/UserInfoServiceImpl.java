package cn.wy.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.wy.biz.service.intf.UserInfoService;
import cn.wy.dal.dao.intf.UserInfoDao;
import cn.wy.pojo.UserInfo;

/**
 * Created by leslie on 2018/5/13.
 */
@Component("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired // by type: cn.wy.dal.dao.intf.UserInfoDao
    private UserInfoDao userInfoDao;

    public int addUserInfo(UserInfo info) {
        userInfoDao.insert(info);
        return 0;
    }

    @Override
    public int addUserInfoWithRuntimeException(UserInfo info) {
        userInfoDao.insert(info);
        throw new RuntimeException();
    }
}
