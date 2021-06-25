package com.ruihai.helper.service;

import com.google.gson.Gson;
import com.ruihai.helper.config.Config;
import com.ruihai.helper.constant.ApiUrlConstant;
import com.ruihai.helper.dao.stock.StockBasicDao;
import com.ruihai.helper.model.stock.StockBasic;
import com.ruihai.helper.model.stock.StockBasicReq;
import com.ruihai.helper.util.stock.StockHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StockService {

    @Autowired
    String getStockBaseUrl;

    @Autowired
    String getStockToken;

    private Gson gson = new Gson();

    @Autowired
    private StockBasicDao stockBasicDao;

    public String getStockBasic(StockBasicReq stockBasicReq){
        Map params = stockBasicReq.toParams();
        String result = StockHttpUtil.postStockApi(getStockBaseUrl, ApiUrlConstant.STOCK_BASIC, getStockToken, params, String.valueOf(params.get("fields")));
        System.out.println(result);
        return result;
    }

}
