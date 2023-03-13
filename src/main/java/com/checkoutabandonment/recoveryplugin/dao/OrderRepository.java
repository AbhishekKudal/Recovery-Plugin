package com.checkoutabandonment.recoveryplugin.dao;

import com.checkoutabandonment.recoveryplugin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

//Data access object for Order Class
//Spring will help with the pre-defined methods using objects instance variables
public interface OrderRepository extends JpaRepository<Order, Long> {

}
