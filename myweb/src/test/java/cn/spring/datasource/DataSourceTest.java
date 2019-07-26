package cn.spring.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import cn.spring.dao.DaoTest;

/**
 * Created by wy on 2018/5/6.
 */
public class DataSourceTest extends DaoTest {

    private DataSource dataSource = null;

    @Before
    public void before() {
        Assert.notNull(ctx);
        dataSource = ctx.getBean("dataSource", DataSource.class);
    }

    @Test
    public void test1() throws SQLException {
        Assert.notNull(dataSource);
        String sql = "select * from t1";
        Connection connection = dataSource.getConnection();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
            System.out.println("用户名为:" + rs.getString(1));
        }
    }
}
