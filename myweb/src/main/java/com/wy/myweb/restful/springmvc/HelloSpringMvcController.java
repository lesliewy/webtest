package com.wy.myweb.restful.springmvc;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.myweb.object.RestResult;

import cn.wy.biz.other.qrcode.QRCodeUtil;

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
     * 
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

    /**
     * <pre>
     *     根据 url 生成 普通二维码
     * </pre>
     * 
     * @return
     */
    @RequestMapping(value = "/commonqrcode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void createCommonQRcode(HttpServletResponse response, String url) throws IOException {
        logger.info("createCommonQRcode");
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            // 使用工具类生成二维码
            QRCodeUtil.encode(url, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * <pre>
     * 根据 url 生成 带有logo二维码
     * </pre>
     */
    @RequestMapping(value = "/logoqrcode")
    public void createLogoQRCode(HttpServletResponse response, String url) throws Exception {
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            String logoPath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "templates"
                              + File.separator + "logo.jpg";
            // 使用工具类生成二维码
            QRCodeUtil.encode(url, logoPath, stream, true);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
