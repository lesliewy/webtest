package cn.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.wy.myweb.restful.springmvc.HelloSpringMvcController;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.wy.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by leslie on 2020/7/6.
 */
public class PureMybatisTest {

    private static final Logger logger = LoggerFactory.getLogger(PureMybatisTest.class);

    private static SqlSession sqlSession;

    @BeforeClass
    public static void beforeClass() throws IOException {
        // 加载配置文件 并构建SqlSessionFactory对象. classpath 上的文件.
        String resource = "mybatis/pureMybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // 从SqlSessionFactory对象中获取 SqlSession对象
        sqlSession = factory.openSession();
    }

    @AfterClass
    public static void afterClass() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    /**
     * <pre>
     *
     create database my;
     use my;
     create table user
     (id bigint unsigned auto_increment primary key,
     name varchar(10),
     age int
     );
     insert into user(name, age) values('wy', 5),('wy2', 13), ('wang', 18), ('zhang', 22), ('zhang', 23), ('zhang', 24), ('li', 12), ('li', 13);

     * </pre>
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        // 执行操作
        Map<String, Integer> params = new HashMap<>();
        params.put("id", 2);
        // User u = sqlSession.selectOne("findById", params);
        User u = sqlSession.selectOne("cn.wy.dal.mapper.UserMapper.findById", 2);
        logger.info("user: {}", u);

        System.out.println(u);
        // 提交操作
        sqlSession.commit();
        // 关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void test2() {
        User u = new User();
        u.setName("d");
        u.setAge(12);
        sqlSession.update("cn.wy.dal.mapper.UserMapper.insertOne", u);
        sqlSession.commit();
    }
}
