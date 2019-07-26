package cn.wy.dal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cn.wy.dal.dao.intf.UserDao;
import cn.wy.pojo.User;

/**
 * Created by leslie on 2018/5/6.
 */
// spring/transaction/srping-tx-proxy-5.xml 中使用注解设置事务代理.

// @Transactional
@Component("userDaoTx")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into user(name,age)values(?,?)";
        Object[] obj = new Object[] { user.getName(), user.getAge() };
        return this.execute(sql, obj);
    }

    @Override
    public int updateUser(User user) {
        String sql = "update user set name=?,age=? where name=?";
        Object[] obj = new Object[] { user.getName(), user.getAge(), user.getName() };
        return this.execute(sql, obj);
    }

    @Override
    public int deleteUser(User user) {
        String sql = "delete from user where name=?";
        Object[] obj = new Object[] { user.getName() };
        return this.execute(sql, obj);
    }

    @Override
    public User findUserByName(String name) {
        String sql = "select * from user where name=?";
        // 将结果集通过Java的反射机制映射到Java对象中
        RowMapper<User> rowMapper = new BeanPropertyRowMapper(User.class);
        return this.jdbcTemplate.queryForObject(sql, rowMapper, name);
    }

    @Override
    public List<User> findAllUser() {
        String sql = "select * from user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper(User.class);
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    // 负责SQL语句的执行
    private int execute(String sql, Object[] obj) {
        return this.jdbcTemplate.update(sql, obj);
    }
}
