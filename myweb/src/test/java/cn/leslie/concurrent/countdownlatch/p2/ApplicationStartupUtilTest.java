package cn.leslie.concurrent.countdownlatch.p2;

/**
 * Created by leslie on 2019/11/17.
 */
public class ApplicationStartupUtilTest {

    public static void main(String[] args) {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: " + result);
    }
}
