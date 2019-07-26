package cn.spring.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import cn.wy.dal.dao.intf.UserDao;
import cn.wy.pojo.User;

/**
 * Created by leslie on 2018/5/6.
 */
public class UserDaoTest extends DaoTest {

    private UserDao userDao;

    @Before
    public void before() {
        Assert.notNull(ctx);
        userDao = ctx.getBean("userDaoTx", UserDao.class);
    }

    @Test
    public void test1() {
        Assert.notNull(userDao);
        User user = new User();
        user.setName("wy2");
        user.setAge(6);
        userDao.addUser(user);
    }

    @Test
    public void test2() {
        Assert.notNull(userDao);
        String name = "wy1";
        User user = userDao.findUserByName(name);
        if (user == null) {
            System.out.println("没找到name = " + name + " 的记录.");
        } else {
            System.out.println(user);
        }
    }

    @Test
    public void test3() {
        Assert.notNull(userDao);
        List<User> all = userDao.findAllUser();
        if (CollectionUtils.isEmpty(all)) {
            System.out.println("no user found.");
        } else {
            for (User user : all) {
                System.out.println(user);
            }
        }
    }
}
