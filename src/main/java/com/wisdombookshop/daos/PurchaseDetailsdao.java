package com.wisdombookshop.daos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wisdombookshop.entities.OrderDetails;
import com.wisdombookshop.entities.PurchaseDetails;

public interface PurchaseDetailsdao extends JpaRepository<PurchaseDetails, Integer>{

}

