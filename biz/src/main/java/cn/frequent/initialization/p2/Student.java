package cn.frequent.initialization.p2;

import lombok.Data;
import lombok.NonNull;

/**
 * <pre>
 *    注解分为两种：运行时注解，编译时注解。  lombok 使用的是编译时注解，所以会修改字节码，所以需要idea安装插件来识别.
 *    . @Data = @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
 *    . @RequiredArgsConstructor:  会生成构造方法（可能带参数也可能不带参数），如果带参数，这参数只能是以 final 修饰的未经初始化的字段或者是以 @NonNull 注解的未经初始化的字段. 一般和 @NonNull 联合使用.
 *
 *    参考:
 *      公众号: 占小狼的博客 - 20200413 - 听说隔壁在用 Lombok，六点就下班了？
 *
 *    Created by leslie on 2020/4/24.
 * </pre>
 */

// @ToString
// @Setter
// @Getter
// @RequiredArgsConstructor
// @EqualsAndHashCode

@Data
public class Student {

    @NonNull
    private String name;

    private String sex;

    private int    age;

    // public String getName() {
    // return name;
    // }
    //
    // public void setName(String name) {
    // this.name = name;
    // }
    //
    // public String getSex() {
    // return sex;
    // }
    //
    // public void setSex(String sex) {
    // this.sex = sex;
    // }
    //
    // public int getAge() {
    // return age;
    // }
    //
    // public void setAge(int age) {
    // this.age = age;
    // }

    // @Override
    // public boolean equals(Object s) {
    // return this.getName().equals(((Student) s).getName());
    // }

    public static void main(String[] args) {
        Student s1 = new Student("wy");
        s1.setAge(2);
        s1.setName("wy");
        System.out.println(s1);
        System.out.println("name: " + s1.getName());

        // 如果没有equals()，为false; equals()修改了对象相同的判断标准.
        Student s2 = new Student("wy");
        s2.setName("wy");
        System.out.println("equals: " + s1.equals(s2));
    }
}
