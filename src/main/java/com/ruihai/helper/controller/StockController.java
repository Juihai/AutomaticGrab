package com.ruihai.helper.controller;

import com.ruihai.helper.model.stock.StockBasicReq;
import com.ruihai.helper.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/index")
    @ResponseBody
    public String index(String name){
        return "hello " + name+"!";
    }

    @RequestMapping("/getStockBasic")
    @ResponseBody
    public String getStockBasic(String ts_code){
        StockBasicReq stockBasicReq = new StockBasicReq();
        stockBasicReq.setTs_code(ts_code);
        return stockService.getStockBasic(stockBasicReq);
    }
}
