package cn.jdk.reflect.jdkproxy.p1;

/**
 * Created by leslie on 2020/7/7.
 */
public class Student implements People {

    @Override
    public People work(String workName) {
        System.out.println("工作内容是" + workName);
        return this;
    }

    @Override
    public String time() {
        return "2018-06-12";
    }
}
