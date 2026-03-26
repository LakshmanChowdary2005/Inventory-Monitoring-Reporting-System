package com.infosys.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.inventory.entity.StockLog;

public interface StockLogRepository extends JpaRepository<StockLog, Long> {

}