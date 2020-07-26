package cn.mybatis.cache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.wy.pojo.User;
import org.springframework.util.Log4jConfigurer;

/**
 * Created by leslie on 2020/7/7.
 */
public class FirstCache {

    private static final Logger logger = LoggerFactory.getLogger(FirstCache.class);

    @Test
    public void test1() throws IOException {
        // @todo  test 中日志不生效？？？
        logger.info("aaa");
        String resource = "mybatis/pureMybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // 从SqlSessionFactory对象中获取 SqlSession对象
        SqlSession sqlSession = factory.openSession();
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
}
