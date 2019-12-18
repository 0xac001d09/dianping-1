package nju.agile.dianping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/index")
@RequestMapping("/index")
public class Test {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }



}
