package cn.wy.biz.netty.p5;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leslie on 2020/3/11.
 */
public class IntegerFactory {

    private static class SingletonHolder {

        private static final AtomicInteger INSTANCE = new AtomicInteger();
    }

    private IntegerFactory(){
    }

    public static final AtomicInteger getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
