package cn.wy.biz.other.beanutils;

/**
 * Created by leslie on 2021/2/12.
 */
public class PersonDestPublic {
    private Integer id;
    private String  username;
    private Integer age;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id: " + id + "  username: " + username + "  age: " + age;
    }
}
