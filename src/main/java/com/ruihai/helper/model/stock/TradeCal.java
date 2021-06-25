package com.ruihai.helper.model.stock;

import lombok.Data;

import javax.persistence.*;

/**
 * api:trade_cal
 * name:交易日历
 * desc:获取各大交易所交易日历数据,默认提取的是上交所
 */
@Entity
@Data
@Table(name = "trade_cal")
public class TradeCal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exchange" ,length = 20)
    private String exchange;//交易所 SSE上交所 SZSE深交所

    @Column(name = "cal_date",length = 20)
    private String calDate;//日历日期

    @Column(name = "is_open",length = 10)
    private int isOpen;//是否交易 0休市 1交易

    @Column(name = "pretrade_date",length = 20)
    private String pretradeDate;//上一个交易日
}
