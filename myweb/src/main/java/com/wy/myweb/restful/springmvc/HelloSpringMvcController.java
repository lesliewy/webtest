package com.wy.myweb.restful.springmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.myweb.object.RestResult;

/**
 * Created by leslie on 2020/5/9.
 */
@Controller
@RequestMapping("/springmvc")
public class HelloSpringMvcController {

    private static final Logger logger = LoggerFactory.getLogger(HelloSpringMvcController.class);

    /**
     * <pre>
     *     ResponseBody 返回json格式数据.
     * </pre>
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RestResult query() {
        logger.info("helloController.query");
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RestResult restResult = new RestResult();
        restResult.setName("a2");
        return restResult;
    }
}
