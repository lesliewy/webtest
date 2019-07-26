package cn.wy.pojo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by leslie on 2018/5/6.
 */
public class User {

    private String  name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
