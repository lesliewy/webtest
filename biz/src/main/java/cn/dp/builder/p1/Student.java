package cn.dp.builder.p1;

import lombok.ToString;

/**
 * Created by leslie on 2020/5/8.
 */
@ToString
public class Student {

    private String name;
    private int    age;
    private String sex;

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }

    public Student setSex(String sex) {
        this.sex = sex;
        return this;
    }
}
