package cn.wy.biz.netty.p2;

import java.util.Date;

/**
 * Created by leslie on 2020/3/10.
 */
public class UnixTime {

    private final long value;

    public UnixTime(){
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value){
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }
}
