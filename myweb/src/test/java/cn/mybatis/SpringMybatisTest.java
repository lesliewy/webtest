package cn.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.wy.dal.mapper.UserMapper;
import cn.wy.pojo.User;

/**
 * Created by leslie on 2020/7/3.
 */
public class SpringMybatisTest {

    /*
     * 1. 创建spring容器 根据xml文件应用程序Context容器(上下文) classpath指配置文件的位置, 起点有java, resources. 写路径时相对这个起点去写
     */
    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");

    @Test
    public void test1() {

        /* 得到 SqlSession 对象 */
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", 0);
        map.put("size", 5);

        // 调用 Mapper映射文件里的方法
        List<User> list = sqlSession.selectList("cn.wy.mapper.UserMapper.selectPage", map);

        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        // 利用反射，得到spring自动配置的 ProductMapper 的 <bean>
        UserMapper mapper = context.getBean(UserMapper.class);
        User p = new User();
        p.setName("kobe");
        p.setAge(11);
        Map<String, Integer> map = new HashMap<>();
        map.put("start", 0);
        map.put("size", 5);
        List<User> users = mapper.findByPage(map);
        for (User user : users) {
            System.out.println(user);
        }

        User user = mapper.findById(2);
        System.out.println("findById: " + user);
    }
}
