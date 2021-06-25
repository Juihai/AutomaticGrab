package com.ruihai.helper.model.stock;

import lombok.Data;

import javax.persistence.*;

/**
 * api:stock_company
 * name:上市公司基本信息
 * desc:获取上市公司基础信息，单次提取4500条，可以根据交易所分批提取
 */
@Entity
@Data
@Table(name = "stock_company")
public class StockCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ts_code",length = 20)
    private String tsCode;//股票代码

    @Column(name = "exchange",length = 20)
    private String exchange;//交易所代码 ，SSE上交所 SZSE深交所

    @Column(name = "chairman",length = 20)
    private String chairman;//法人代表

    @Column(name = "manager",length = 20)
    private String manager;//总经理

    @Column(name = "secretary",length = 20)
    private String secretary;//董秘

    @Column(name = "reg_capital",length = 20)
    private float regCapital;//注册资本

    @Column(name = "setup_date",length = 20)
    private String setupDate;//注册日期

    @Column(name = "province",length = 20)
    private String province;//所在省份

    @Column(name = "city",length = 20)
    private String city;//所在城市

    @Column(name = "introduction",length = 400)
    private String introduction;//公司介绍

    @Column(name = "website",length = 200)
    private String website;//公司主页

    @Column(name = "email",length = 20)
    private String email;//电子邮件

    @Column(name = "office",length = 30)
    private String office;//办公室

    @Column(name = "employees",length = 20)
    private Integer employees;//员工人数

    @Column(name = "main_business",length = 400)
    private String mainBusiness;//主要业务及产品

    @Column(name = "business_scope",length = 400)
    private String businessScope;//经营范围
}
