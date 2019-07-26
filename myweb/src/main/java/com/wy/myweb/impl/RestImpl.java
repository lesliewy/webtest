package com.wy.myweb.impl;

import com.wy.myweb.api.Rest;
import com.wy.myweb.object.RestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by leslie on 2018/3/12.
 */
public class RestImpl implements Rest {

    private static final Logger logger = LogManager.getLogger(RestImpl.class);

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
