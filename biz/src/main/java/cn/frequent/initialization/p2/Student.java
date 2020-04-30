package cn.frequent.initialization.p2;

import lombok.Data;
import lombok.ToString;

/**
 * Created by leslie on 2020/4/24.
 */
@ToString
public class Student {

    private String name;

    private String sex;

    private int    age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
