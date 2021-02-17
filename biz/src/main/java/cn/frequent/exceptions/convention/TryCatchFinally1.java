package cn.frequent.exceptions.convention;

/**
 * <pre>
 *
 * Created by leslie on 2020/9/27.
 * </pre>
 */
public class TryCatchFinally1 {

    public static void main(String[] args) {
        System.out.println(TryCatchFinally1.test1());
        System.out.println(TryCatchFinally1.test2());
        System.out.println(TryCatchFinally1.test3());
        System.out.println(TryCatchFinally1.test4());
        // System.out.println(TryCatchFinally1.test5());
        System.out.println(TryCatchFinally1.test6());
        // System.out.println(TryCatchFinally1.test7());
        System.out.println(TryCatchFinally1.test8());
        System.out.println(TryCatchFinally1.test9());
    }

    /**
     * <pre>
     *   try语句有return语句，finally 中无return, 则返回当前try中变量此时对应的值，此后对变量做任何的修改，都不影响try中return的返回值
     *
     *   此时try{}中的t会值会先存起来 astore.  最后return时, 会aload出来.
     *   finally{}中的t并不是try{}中要return的值了.
     *
     *   javap -verbose TryCatchFinally
     *   or idea plugin: view -> show bytecode with jclasslib
     * </pre>
     *
     * @return
     */
    public static final String test1() {
        System.out.println("===test1===");
        String t = "";
        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
        }
    }

    /**
     * <pre>
     *     try、finally 中都有return:
     *     try语句中的return语句给忽略, 直接起作用的是finally中的return语句，
     * </pre>
     * 
     * @return
     */
    public static final String test2() {
        System.out.println("===test2===");
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            // result = "catch";
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    /**
     * <pre>
     *    try、catch 中都有return:   返回catch中的t值, finally 中修改的无效.
     * </pre>
     * 
     * @return
     */
    public static final String test3() {
        System.out.println("===test3===");
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            // System.out.println(t);
            // return t;
        }
    }

    /**
     * try、catch、finally 中都有return: 返回 finally 中的return. 先try代码，抛异常， 进入catch代码, return之前，执行finally代码, 有return,
     * 返回此return.
     *
     * @return
     */
    public static final String test4() {
        System.out.println("===test4===");
        String t = "";
        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    /**
     * try - catch 抛异常 - finally - 抛出异常
     * 
     * @return
     */
    public static final String test5() {
        System.out.println("===test5===");
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            return t;
        } finally {
            t = "finally";
            // return t;
        }
    }

    /**
     * <pre>
     *     try - catch 抛异常 - finally 中return    方法不会抛出异常.
     * </pre>
     * 
     * @return
     */
    public static final String test6() {
        System.out.println("===test6===");
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (Exception e) {
            t = "catch";
            Integer.parseInt(null);
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    /**
     * <pre>
     *    try抛出java.lang.NumberFormatException - finally - 抛出java.lang.NumberFormatException
     * </pre>
     * 
     * @return
     */
    public static final String test7() {
        System.out.println("===test7===");
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
        }
    }

    /**
     * <pre>
     *    try抛出java.lang.NumberFormatException - finally - finally 中return
     * </pre>
     * 
     * @return
     */
    public static final String test8() {
        System.out.println("===test8===");
        String t = "";

        try {
            t = "try";
            Integer.parseInt(null);
            return t;
        } catch (NullPointerException e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            return t;
        }
    }

    /**
     * <pre>
     *     try - finally - finally 抛出NPE
     * </pre>
     * 
     * @return
     */
    public static final String test9() {
        System.out.println("===test9===");
        String t = "";

        try {
            t = "try";
            return t;
        } catch (Exception e) {
            t = "catch";
            return t;
        } finally {
            t = "finally";
            String.valueOf(null);
            return t;
        }
    }
}
