package com.checkoutabandonment.recoveryplugin.dao;

import com.checkoutabandonment.recoveryplugin.entity.AbandonedCart;
import org.springframework.data.jpa.repository.JpaRepository;

//Data access object for AbandonedCart Class
//Spring will help with the pre-defined methods using objects instance variables
public interface AbandonedCartRepository extends JpaRepository<AbandonedCart, Long> {

}
