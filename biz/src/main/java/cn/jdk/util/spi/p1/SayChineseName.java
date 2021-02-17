package cn.jdk.util.spi.p1;

/**
 * Created by leslie on 2020/12/16.
 */
public class SayChineseName implements ISayName {

    @Override
    public void say() {
        System.out.println("肥朝");
    }
}
