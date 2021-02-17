package cn.jdk.reflect.principles;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by leslie on 2020/12/11.
 */
public class Reflection1 {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        try {
            Class<?> klass = Class.forName("cn.jdk.reflect.principles.Reflection1");
            Method method = klass.getMethod("target", int.class);
            method.invoke(null, 0);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 观察inflation
     * </pre>
     */
    private static void test2() {
        try {
            Class<?> klass = Class.forName("cn.jdk.reflect.principles.Reflection1");
            Method method = klass.getMethod("target", int.class);
            for (int i = 0; i < 20; i++) {
                method.invoke(null, i);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }
}
