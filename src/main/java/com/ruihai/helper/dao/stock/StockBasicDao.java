package com.ruihai.helper.dao.stock;

import com.ruihai.helper.model.stock.StockBasic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockBasicDao extends JpaRepository<StockBasic,Integer> {
}
