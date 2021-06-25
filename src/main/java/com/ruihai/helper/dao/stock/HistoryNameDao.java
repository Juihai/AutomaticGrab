package com.ruihai.helper.dao.stock;

import com.ruihai.helper.model.stock.HistoryName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryNameDao extends JpaRepository<HistoryName,Integer> {
}
