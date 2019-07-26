package cn.wy.biz.service.intf;

import cn.wy.pojo.User;
import cn.wy.pojo.UserInfo;

/**
 * Created by leslie on 2018/5/13.
 */
public interface UserService {

    int addUser(User user);

    int addUserAndUserInfo(User user, UserInfo userInfo);

    int addBothCalleeRuntimeException(User user, UserInfo userInfo);

    int addBothCallerRuntimeException(User user, UserInfo userInfo);
}
