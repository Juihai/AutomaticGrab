package com.ruihai.helper.model.stock;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StockBasicReq {

    private String is_hs;       //是否沪深港通标的，N否 H沪股通 S深股通
    private String list_status; //上市状态 L上市 D退市 P暂停上市，默认是L
    private String exchange;    //交易所 SSE上交所 SZSE深交所
    private String ts_code;     //TS股票代码
    private String market;      //市场类别
    private Integer limit;
    private Integer offset;
    private String name;        //名称

    public Map toParams(){
        Map params = new HashMap();
        StringBuilder fields = new StringBuilder();
        if(StringUtils.isNoneBlank(this.is_hs)){
            params.put("is_hs", this.is_hs);
        }
        fields.append("is_hs").append(",");
        if(StringUtils.isNoneBlank(this.list_status)){
            params.put("list_status", this.list_status);
        }
        fields.append("list_status").append(",");
        if(StringUtils.isNoneBlank(this.exchange)){
            params.put("exchange", this.exchange);
        }
        fields.append("exchange").append(",");
        if(StringUtils.isNoneBlank(this.ts_code)){
            params.put("ts_code", this.ts_code);
        }
        fields.append("ts_code").append(",");
        if(StringUtils.isNoneBlank(this.market)){
            params.put("market", this.market);
        }
        fields.append("market").append(",");
        if(StringUtils.isNoneBlank(this.name)){
            params.put("name", this.name);
        }
        fields.append("name").append(",");
        if(this.limit != null){
            params.put("limit", this.limit);
        }
        if(this.offset != null){
            params.put("offset", this.offset);
        }
        params.put("fields", fields.substring(0, fields.length()-1));

        return params;
    }

    public String getIs_hs() {
        return is_hs;
    }

    public void setIs_hs(String is_hs) {
        this.is_hs = is_hs;
    }

    public String getList_status() {
        return list_status;
    }

    public void setList_status(String list_status) {
        this.list_status = list_status;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getTs_code() {
        return ts_code;
    }

    public void setTs_code(String ts_code) {
        this.ts_code = ts_code;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
