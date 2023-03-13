package com.checkoutabandonment.recoveryplugin.dao;

import com.checkoutabandonment.recoveryplugin.entity.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Data access object for Scheduler Class
//Spring will help with the pre-defined methods using objects instance variables
public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

    List<Scheduler> findByCurrentStatus(String currentStatus);
}
