package cn.wy.dal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.wy.dal.dao.intf.UserInfoDao;
import cn.wy.pojo.UserInfo;
import org.springframework.stereotype.Component;

/**
 * Created by leslie on 2018/5/13.
 */
@Component("userInfoDaoTx")
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    @Override
    public int insert(UserInfo info) {
        String sql = "insert into user_info(name,address, phone_no)values(?,?,?)";
        Object[] obj = new Object[] { info.getName(), info.getAddress(), info.getPhoneNo() };
        return this.jdbcTemplate.update(sql, obj);
    }

}
