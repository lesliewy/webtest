package cn.wy.dal.dao.intf;

import java.util.List;

import cn.wy.pojo.User;

/**
 * Created by leslie on 2018/5/6.
 */
public interface UserDao {

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(User user);

    User findUserByName(String name);

    List<User> findAllUser();

}
