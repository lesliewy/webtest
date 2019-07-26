package cn.wy.biz.service.intf;

import cn.wy.pojo.UserInfo;

/**
 * Created by leslie on 2018/5/13.
 */
public interface UserInfoService {

    int addUserInfo(UserInfo info);

    int addUserInfoWithRuntimeException(UserInfo info);

}
