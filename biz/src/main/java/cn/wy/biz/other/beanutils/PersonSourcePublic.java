package cn.wy.biz.other.beanutils;

/**
 * Created by leslie on 2021/2/12.
 */
public class PersonSourcePublic {

    private Integer id;
    private String  username;
    private String  password;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    PersonSourcePublic(int id, String username, String password, int age){
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }
}
