package com.ruihai.helper.util.stock;

import com.google.gson.Gson;
import com.ruihai.helper.log.AgLog;
import com.ruihai.helper.util.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class StockHttpUtil {

    private final static String API_NAME = "api_name";
    private final static String TOKEN = "token";
    private final static String PARAMS = "params";
    private final static String FIELDS = "fields";


    public static String postStockApi(String url, String apiName, String token, Map params, String fields){
        Map body = new HashMap();
        body.put(API_NAME, apiName);
        body.put(TOKEN, token);
        body.put(PARAMS, params);
        body.put(FIELDS, "");
        System.out.println(body.toString());
        try {
            String result = HttpClientUtil.postRequestByJson(url, new Gson().toJson(body));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
