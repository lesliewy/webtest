package cn.dp.builder.p1;

/**
 * <pre>
 *    静态类的builder.
 *    1, 不能返回this, 但可以返回static instance.
 *    2, 直接返回null, 但比较诡异.
 *       invokestatic 可以直接找到方法的索引，不依赖实例.
 *
 *    Created by leslie on 2020/5/8.
 * </pre>
 */
public class MessageDialog {

    private static final MessageDialog instance = new MessageDialog();

    public static MessageDialog doSth1() {
        System.out.println("this is doSth1.");
        return instance;
    }

    public static MessageDialog doSth2() {
        System.out.println("this is doSth2.");
        return instance;
    }

    public static MessageDialog doSth3() {
        System.out.println("this is doSth3.");
        return null;
    }

    public static MessageDialog doSth4() {
        System.out.println("this is doSth4.");
        return null;
    }
}
