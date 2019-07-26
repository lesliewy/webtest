package cn.spring.transaction;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import cn.spring.dao.DaoTest;
import cn.wy.biz.service.intf.UserInfoService;
import cn.wy.biz.service.intf.UserService;
import cn.wy.pojo.User;
import cn.wy.pojo.UserInfo;

/**
 * Created by leslie on 2018/5/13.
 */
public class TransactonTest extends DaoTest {

    private UserService     userService;

    private UserInfoService userInfoService;

    @Before
    public void before() {
        Assert.notNull(ctx);
        userService = ctx.getBean("userService", UserService.class);
        userInfoService = ctx.getBean("userInfoService", UserInfoService.class);
    }

    @Test
    public void test1() {
        userService.addUser(createUser("wy3", 1));
        userInfoService.addUserInfo(createUserInfo("wy3", "aa", "123"));
    }

    @Test
    public void test2() {
        userService.addUserAndUserInfo(createUser("wy4", 1), createUserInfo("wy4", "aaa", "1234"));
    }

    /**
     *
     * 如果不配置，默认是非事务的执行;
     *
     * 事务传播行为
     * PROPAGATION_REQUIRED: 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
     *      addUserInfoWithRuntimeException() 被事务管理, 但是外层调用者没有被管理:
     *          外层调用者的事务仍然会提交.
     *      反过来，addBothCalleeRuntimeException() 被事务管理，但是不管理被调的方法:
     *          如果被调用者抛出异常: 被调用者和调用者都不会提交事务;
     *          如果调用者抛出异常:   被调用者和调用者都不会提交事务;
     * 也就是说调用者被事务管理，其以下的所有被调用者都被管理.
     *
     * PROPAGATION_SUPPORTS: 支持当前事务，如果当前没有事务，就以非事务方式执行。
     *      显然，最外层最好不要使用这种方式, 否则其以下的所有方法调用都非事务的执行.
     *
     * PROPAGATION_MANDATORY: 使用当前的事务，如果当前没有事务，就抛出异常
     *
     * PROPAGATION_REQUIRES_NEW: 新建事务; 如果当前存在事务，把当前事务挂起
     *
     * PROPAGATION_NOT_SUPPORTED： 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
     *
     * PROPAGATION_NEVER： 以非事务方式执行，如果当前存在事务，则抛出异常;
     *
     * PROPAGATION_NESTED: 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作
     *      嵌套事务是一个外部事务的一个子事务，是一个外部事务的一个组成部分，当嵌套事务发生异常，而回滚，则会回复到嵌套事务的执行前的状态，相当于嵌套事务未执行。
            如果外部事务回滚，则嵌套事务也会回滚！！！外部事务提交的时候，它才会被提交
     *
     * 非事务的执行，表示sql依然会立即提交, 即使后来抛出异常.
     * 事务的传播行为是动态的，和执行线程有关系，同一个方法可能这次调用被事务管理，下次没有;
     */
    @Test
    public void test3(){
        userService.addBothCalleeRuntimeException(createUser("wy5", 1), createUserInfo("wy5", "aa", "1234"));
    }

    @Test
    public void test4(){
        userService.addBothCallerRuntimeException(createUser("wy5", 1), createUserInfo("wy5", "aa", "1234"));
    }

    private User createUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    private UserInfo createUserInfo(String name, String addr, String phoneNo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAddress(addr);
        userInfo.setPhoneNo(phoneNo);
        return userInfo;
    }
}
