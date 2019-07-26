package com.wy.spring.retry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/**
 * Created by leslie on 2018/10/18.
 */
public class RetryService {

    private static final Logger logger = LogManager.getLogger(RetryService.class);

    @Retryable(value = { RemoteAccessException.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public void retryTest() throws Exception {
        logger.info("do something...");
        throw new RemoteAccessException("RemoteAccessException....");
    }

    @Recover
    public void recover(RemoteAccessException e) {
        logger.info(e.getMessage());
        logger.info("recover....");
    }
}
