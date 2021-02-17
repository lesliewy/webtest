package cn.jdk.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leslie on 2020/11/30.
 */
public class SimpleDateFormatThreadLocal {

    public static final ThreadLocal SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private SimpleDateFormatThreadLocal(){
    }

    public static Date parse(String target) {
        try {
            return ((DateFormat) SIMPLE_DATE_FORMAT.get()).parse(target);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(Date target) {
        return ((DateFormat) SIMPLE_DATE_FORMAT.get()).format(target);
    }
}
