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
     * <pre>
     *   * 如果不配置，默认是非事务的执行;
     *         只要sql那句代码没有问题，就不会回滚，autocommit为true情况下，就会成功执行。 不管是调用方法内部抛异常，还是调用者本身抛异常。
     *
     *   * 事务传播行为:
     *      ***** 异常：只有当方法抛出异常时，才会被拦截。 如果抛出的异常在方法内已经被catch, 不会触发AOP进行事务操作。*****
     *
     * PROPAGATION_REQUIRED: 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
     *     情况一: 被调用者 addUserInfoWithRuntimeException() 被事务管理, 但是外层调用者没有被管理(即不配置事务,使用数据库的默认配置)
     *          外层调用者的事务仍然会提交.
     *
     *     情况二: 调用者addBothCalleeRuntimeException() 被事务管理，但是被调的方法不被理(即没有配置PROPAGATION).
     *          如果被调用者抛出异常且调用者addBothCalleeRuntimeException()没有处理异常，继续抛出: 调用者被拦截，会回滚事务，事务只有一个，其被调用者执行的sql也会被回滚。
     *          如果被调用者抛出异常且调用者addBothCalleeRuntimeException()捕获异常，不再抛出:   调用者不会被拦截，会提交事务。
     *
     *     情况三: 被调用者 addUserInfoWithRuntimeException() 是PROPAGATION_REQUIRED, 其外层调用者addBothCalleeRuntimeException() 也是PROPAGATION_REQUIRED.
     *          如果被调用者抛出异常且调用者addBothCalleeRuntimeException()没有处理异常，继续抛出: 被调用者、调用者都会回滚，没有冲突。
     *          如果被调用者抛出异常且调用者addBothCalleeRuntimeException()捕获异常，不再抛出: 被调用者回滚，调用者提交，冲突，抛出异常: org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
     *          ***** 只要有一个方法抛出异常，就会触发AOP进行事务回滚。即使调用者catch了异常。*****
     *
     *      ***** 看起来规则： 只有显式的被interceptor中正则匹配的方法才会被AOP拦截进行事务管理。 *****
     *      ***** 被标记为 PROPAGATION_REQUIRED 的方法，其内部子方法虽然不会被AOP拦截，但是底层事务只有一个. *****
     *      xxxx 也就是说调用者被事务管理，其以下的所有被调用者都被管理.  这个是错误的.
     *
     * PROPAGATION_SUPPORTS: 支持当前事务，如果当前没有事务，就以非事务方式执行。
     *      显然，最外层最好不要使用这种方式, 否则其以下的所有方法调用都非事务的执行.
     *
     * PROPAGATION_MANDATORY: 使用当前的事务，如果当前没有事务，就抛出异常
     *
     * PROPAGATION_REQUIRES_NEW: 新建事务; 如果当前存在事务，把当前事务挂起
     *     情况一: 被调用者addUserInfoWithRuntimeException() 是 PROPAGATION_REQUIRES_NEW, 调用者addBothCalleeRuntimeException() 是PROPAGATION_REQUIRED.
     *         底层是完全的两个事务. 被调用者抛出异常，事务回滚；
     *         调用者可以拦截到异常，看怎么处理，如果也抛出异常，则调用者也会回滚，不抛出则不会。
     *         因为是两个事务，要注意锁的情况.
     *
     * PROPAGATION_NOT_SUPPORTED： 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
     *
     * PROPAGATION_NEVER： 以非事务方式执行，如果当前存在事务，则抛出异常;
     *
     * PROPAGATION_NESTED: 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作
     *      嵌套事务是一个外部事务的一个子事务，是一个外部事务的一个组成部分，当嵌套事务发生异常，而回滚，则会回复到嵌套事务的执行前的状态，相当于嵌套事务未执行。
     *       如果外部事务回滚，则嵌套事务也会回滚！！！外部事务提交的时候，它才会被提交
     *       只对 DataSourceTransactionManager 生效.
     *       情况一: 被调用者 addUserInfoWithRuntimeException() 是PROPAGATION_NESTED, 调用者是 PROPAGATION_REQUIRED.
     *           与PROPAGATION_REQUIRES_NEW 中情况一一样.

     *       情况二: 只设置了调用者是 addBothCalleeRuntimeException() 是 PROPAGATION_NESTED.
     *          被调用者即使抛出了异常，已会执行事务。

     *
     * 非事务的执行，表示sql依然会立即提交, 即使后来抛出异常.
     * 事务的传播行为是动态的，和执行线程有关系，同一个方法可能这次调用被事务管理，下次没有;
     * </pre>
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
