package cn.wy.biz.bytecode.asm.p2;

/**
 * Created by leslie on 2020/4/29.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        C c = new C();
        c.m();
        Class cc = c.getClass();
        System.out.println(cc.getField("timer").get(c));
    }
}
