package com.wy.myweb.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by leslie on 2020/3/21.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/jsp/hello")
    public String sayHello(ModelMap modelMap) {
        modelMap.put("msg", "Hello!");
        return "hello";
    }

    @RequestMapping(value = "/jsp/hello2")
    public String sayHello2(Model model) {
        model.addAttribute("msg", "Hello!");
        return "hello";
    }
}
