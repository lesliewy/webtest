package cn.mybatis.pagehelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wy.dal.mapper.UserMapper;
import cn.wy.pojo.User;

/**
 * Created by leslie on 2021/2/8.
 */
public class PageHelper1 {

    private static final Logger           logger  = LoggerFactory.getLogger(PageHelper1.class);

    static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");

    @Test
    public void test1() {
        UserMapper mapper = context.getBean(UserMapper.class);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pageNum", 1);
        param.put("pageSize", 2);
        PageHelper.startPage((Integer) param.get("pageNum"), (Integer) param.get("pageSize"));
        List<User> list = mapper.findWithCond(param);
//        PageInfo<User> pageInfo = (PageInfo<User>) list;
        PageInfo<User> pageInfo = new PageInfo<>(list);
        logger.info("pageNum: {}, pageInfo: {}", param.get("pageNum"), pageInfo);
        logger.info("list in pageinfo: {}, size: {}", pageInfo.getList(), pageInfo.getList().size());
    }
}
