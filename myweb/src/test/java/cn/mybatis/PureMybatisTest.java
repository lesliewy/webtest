package cn.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.wy.pojo.User;

/**
 * Created by leslie on 2020/7/6.
 */
public class PureMybatisTest {

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

    @Test
    public void test1() throws IOException {
        // 执行操作
        Map<String, Integer> params = new HashMap<>();
        params.put("id", 2);
        // User u = sqlSession.selectOne("findById", params);
        User u = sqlSession.selectOne("cn.wy.dal.mapper.UserMapper.findById", 2);
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
