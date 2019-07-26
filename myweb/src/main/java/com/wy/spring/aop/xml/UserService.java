package com.wy.spring.aop.xml;

import com.wy.spring.retry.RetryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by leslie on 2018/10/19.
 */
@Service("userService")
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public void showUserInfo(){
        logger.info("this is showUserInfo()...");
    }

    public void showUserInfo(int id){
        logger.info("this is showUserInfo(id)");
    }
}
