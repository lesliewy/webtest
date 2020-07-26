package cn.jdk.concurrent.atomic.p1;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by leslie on 2020/6/29.
 */
public class AtomicReferenceTest2 {

    private static AtomicReference<User> reference = new AtomicReference<>();

    public static void main(String[] args) {
        User user1 = new User("a", 1);
        reference.set(user1);
        User user2 = new User("b", 2);
        User user = reference.getAndSet(user2);
        System.out.println(user);
        System.out.println(reference.get());
    }

    static class User {

        private String userName;
        private int    age;

        public User(String userName, int age){
            this.userName = userName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" + "userName='" + userName + '\'' + ", age=" + age + '}';
        }
    }
}
