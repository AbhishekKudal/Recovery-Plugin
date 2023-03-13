package com.checkoutabandonment.recoveryplugin.controller;


import com.checkoutabandonment.recoveryplugin.dao.AbandonedCartRepository;
import com.checkoutabandonment.recoveryplugin.entity.AbandonedCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AbandonedCartController {

    //Injecting the dependency
    @Autowired
    private AbandonedCartRepository abandonedCartRepository;

    //Get all the AbandonedCart objects and return them in a model to the html view page "cart-details"
    @GetMapping("/cart-details")
    public String showCartDetails(Model model) {
        List<AbandonedCart> abandonedCartList = abandonedCartRepository.findAll();
        model.addAttribute("abandonedCartList", abandonedCartList);
        return "cart-details";
    }
}
