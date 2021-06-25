package com.ruihai.helper.model.stock;

import lombok.Data;

import javax.persistence.*;

/**
 * api:hs_const
 * name:沪深股通成份股
 * desc:获取沪股通、深股通成分数据
 */
@Data
@Entity
@Table(name = "hs_const")
public class HSConst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tsCode",length = 20)
    private String tsCode;//TS代码

    @Column(name = "hs_type",length = 10)
    private String hsType;//沪深港通类型SH沪SZ深

    @Column(name = "in_date",length = 20)
    private String inDate;//纳入日期

    @Column(name = "out_date",length = 20)
    private String outDate;//剔除日期

    @Column(name = "is_new",length = 2)
    private int isNew;//是否最新 1是 0否
}
