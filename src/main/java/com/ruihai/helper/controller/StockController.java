package com.ruihai.helper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stock")
public class StockController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(String name){
        return "hello " + name+"!";
    }
}
