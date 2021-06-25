package com.ruihai.helper.model.stock;

public class TradeCalReq {


    private String exchange;//交易所 SSE上交所,SZSE深交所,CFFEX 中金所,SHFE 上期所,CZCE 郑商所,DCE 大商所,INE 上能源,IB 银行间,XHKG 港交所
    private String start_date;//开始日期 （格式：YYYYMMDD 下同）
    private String end_date;//结束日期
    private String is_open;//是否交易 '0'休市 '1'交易
}
