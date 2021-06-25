package com.ruihai.helper.model.stock;

import lombok.Data;

import javax.persistence.*;

/**
 * api:namechange
 * name:股票曾用名
 * desc:历史名称变更记录
 */
@Data
@Entity
@Table(name = "history_name")
public class HistoryName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ts_code",length = 20)
    private String tsCode;//TS代码

    @Column(name = "name",length = 30)
    private String name;//证券名称

    @Column(name = "start_date",length = 20)
    private String startDate;//开始日期

    @Column(name = "end_date",length = 20)
    private String endDate;//结束日期

    @Column(name = "ann_date",length = 20)
    private String annDate;//公告日期

    @Column(name = "change_reason",length = 200)
    private String changeReason;//变更原因
}
