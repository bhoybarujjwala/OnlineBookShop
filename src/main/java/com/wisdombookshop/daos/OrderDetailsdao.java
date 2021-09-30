package com.wisdombookshop.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wisdombookshop.entities.OrderDetails;

public interface OrderDetailsdao extends JpaRepository<OrderDetails, Integer>{

	List<OrderDetails> findByUserId(int id);

	List<OrderDetails> findByDelExeId(int exeid);

}

