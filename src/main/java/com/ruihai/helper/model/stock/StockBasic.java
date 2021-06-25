package com.ruihai.helper.model.stock;

import lombok.Data;

import javax.persistence.*;

/**
 * api:stock_basic
 * name:基础信息
 * desc:获取基础信息数据，包括股票代码、名称、上市日期、退市日期等
 */
@Entity
@Data
@Table(name = "stock_basic")
public class StockBasic {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Long id;

    @Column(name = "ts_code",length = 20)
    private String tsCode;//TS代码

    @Column(name = "symbol",length = 20)
    private String symbol;//股票代码

    @Column(name = "name",length = 30)
    private String name;//股票名称

    @Column(name = "area",length = 50)
    private String area;//地域

    @Column(name = "industry",length = 30)
    private String industry;//所属行业

    @Column(name = "fullname",length = 50)
    private String fullname;//股票全称

    @Column(name = "enname",length = 30)
    private String enname;//英文全称

    @Column(name = "cnspell",length = 20)
    private String cnspell;//拼音缩写

    @Column(name = "market",length = 10)
    private String market;//市场类型（主板/创业板/科创板/CDR）

    @Column(name = "exchange",length = 20)
    private String exchange;//交易所代码

    @Column(name = "curr_type",length = 10)
    private String currType;//交易货币

    @Column(name = "list_status",length = 10)
    private String listStatus;//上市状态 L上市 D退市 P暂停上市

    @Column(name = "list_date",length = 20)
    private String listDate;//上市日期

    @Column(name = "delist_date",length = 20)
    private String delistDate;//退市日期

    @Column(name = "is_hs",length = 10)
    private String isHs;//是否沪深港通标的，N否 H沪股通 S深股通

}
