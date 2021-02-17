package cn.wy.biz.other.beanutils;

//import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

//import org.apache.commons.beanutils.BeanUtils;

/**
 * Created by leslie on 2021/2/12.
 */
public class BeanUtilsTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        test1();
        BeanUtilsTest beanUtilsTest = new BeanUtilsTest();
        beanUtilsTest.test2();
    }

    /**
     * <pre>
     *     阿里巴巴Java开发规约:  避免用Apache Beanutils进行属性的copy.
     *     因为Apache下的BeanUtils性能较差，不建议使用, 同时apache的BeanUtils校验较多，条件比较苛刻.
     *
     *     建议使用Spring的BeanUtils或者其他拷贝框架.
     * </pre>
     * 
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void test1() throws InvocationTargetException, IllegalAccessException {
        System.out.println("======== this is test1 =========");
        PersonSourceStatic personSource = new PersonSourceStatic(1, "pjmike", "12345", 21);
        // apache 的beanutils, source 中提供getter, dest中提供setter, 还必须是public class, 才可以copy.
        // PersonDestStatic personDest1 = new PersonDestStatic();
        // org.apache.commons.beanutils.BeanUtils.copyProperties(personDest1, personSource);
        // System.out.println("personDest1: " + personDest1);

        PersonDestPublic personDestPublic = new PersonDestPublic();
        PersonSourcePublic personSourcePublic = new PersonSourcePublic(1, "pjmike", "12345", 21);
        org.apache.commons.beanutils.BeanUtils.copyProperties(personDestPublic, personSourcePublic);
        System.out.println("personDestPublic: " + personDestPublic);

        // springd的beanutils, source 中提供getter, dest 中提供setter即可copy.
        PersonDestStatic personDest2 = new PersonDestStatic();
        org.springframework.beans.BeanUtils.copyProperties(personSource, personDest2);
        System.out.println("persondest2: " + personDest2);
    }

    private void test2() throws InvocationTargetException, IllegalAccessException {
        System.out.println("======== this is test2 =========");
        PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
        PersonDest personDest1 = new PersonDest();
        org.apache.commons.beanutils.BeanUtils.copyProperties(personDest1, personSource);
        System.out.println("persondest1: " + personDest1);

        PersonDest personDest2 = new PersonDest();
        org.springframework.beans.BeanUtils.copyProperties(personSource, personDest2);
        System.out.println("persondest2: " + personDest2);

    }

    static class PersonSourceStatic {

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

        PersonSourceStatic(int id, String username, String password, int age){
            this.id = id;
            this.username = username;
            this.password = password;
            this.age = age;
        }

    }

    static class PersonDestStatic {

        private Integer id;
        private String  username;
        private Integer age;

        public Integer getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public Integer getAge() {
            return age;
        }

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

    class PersonSource {

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

        PersonSource(int id, String username, String password, int age){
            this.id = id;
            this.username = username;
            this.password = password;
            this.age = age;
        }
    }

    class PersonDest {

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
}
