package com.ruihai.helper.dao.stock;

import com.ruihai.helper.model.stock.StockCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockCompanyDao extends JpaRepository<StockCompany,Integer>  {
}
