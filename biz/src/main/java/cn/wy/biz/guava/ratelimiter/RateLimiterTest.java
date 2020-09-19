package cn.wy.biz.guava.ratelimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by leslie on 2020/9/4.
 */
public class RateLimiterTest {

    public static void main(String[] arg) {
        test1();
    }

    /**
     * <pre>
     *     创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
     *   当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
     *   当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
     *
     *    RateLimiter支持预消费，比如在acquire(5)时，等待时间是3秒，是上一个获取令牌时预消费了3个令牌，固需要等待3*1秒，
     *    然后又预消费了5个令牌，以此类推
     * </pre>
     */
    public static void test1() {
        // 令牌器，每秒生成1个令牌.
        RateLimiter limiter = RateLimiter.create(1);

        for (int i = 1; i < 10; i = i + 2) {
            // 阻塞方式获取令牌.
            double waitTime = limiter.acquire(i);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
        }
    }
}
