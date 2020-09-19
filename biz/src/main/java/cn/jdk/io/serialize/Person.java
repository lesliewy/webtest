package cn.jdk.io.serialize;

import java.io.Serializable;

/**
 * Created by leslie on 2020/9/7.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1234567891L;
    private int               id;
    private String            name;
//    private int               age = 3;

    public Person(int id, String name ){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person: " + id + " " + name ;
    }
}
