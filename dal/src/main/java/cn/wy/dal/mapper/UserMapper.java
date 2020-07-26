package cn.wy.dal.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.wy.pojo.User;

/**
 * Created by leslie on 2020/7/3.
 */
public interface UserMapper {

    /**
     * <pre>
     *     两种方式:
     *          &#64;Select, @Insert, @Delete ....
     *          xml中配置同名id.
     * </pre>
     * 
     * @param map
     * @return
     */
    @Select("select name, age from user limit #{start},#{size}")
    List<User> findByPage(Map map);

    User findById(int id);

    int insertOne(Map<String, Object> map);
}
