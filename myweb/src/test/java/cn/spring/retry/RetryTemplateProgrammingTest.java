package cn.spring.retry;

import org.junit.Test;

import com.wy.spring.retry.RetryTemplateProgramming;

/**
 * Created by leslie on 2018/10/22.
 */
public class RetryTemplateProgrammingTest {

    @Test
    public void test1() {
        RetryTemplateProgramming retryTemplateProgramming = new RetryTemplateProgramming();
        retryTemplateProgramming.test();
    }
}
