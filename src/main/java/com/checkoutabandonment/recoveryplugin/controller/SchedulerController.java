package com.checkoutabandonment.recoveryplugin.controller;

import com.checkoutabandonment.recoveryplugin.dao.SchedulerRepository;
import com.checkoutabandonment.recoveryplugin.entity.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SchedulerController {

    @Autowired
    private SchedulerRepository schedulerRepository;

    //Get all the Scheduler objects and return them in a model to the html view page "scheduler"
    @GetMapping("/scheduler")
    public String showSchedulerTable(Model model) {
        List<Scheduler> scheduledEvents = schedulerRepository.findAll();
        model.addAttribute("scheduledEvents", scheduledEvents);
        return "scheduler";
    }

}
