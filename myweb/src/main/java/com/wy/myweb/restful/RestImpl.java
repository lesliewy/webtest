package com.wy.myweb.restful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wy.myweb.object.RestResult;

/**
 * Created by leslie on 2018/3/12.
 */
public class RestImpl implements Rest {

    // private static final Logger logger = LogManager.getLogger(RestImpl.class);
    private static final Logger logger = LoggerFactory.getLogger(RestImpl.class);

    @Override
    public RestResult query() {
        logger.info("query");
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestResult restResult = new RestResult();
        restResult.setName("aaa");
        return restResult;
    }
}
