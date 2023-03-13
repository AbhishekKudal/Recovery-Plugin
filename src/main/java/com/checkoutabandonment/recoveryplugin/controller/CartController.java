package com.checkoutabandonment.recoveryplugin.controller;

import com.checkoutabandonment.recoveryplugin.dao.AbandonedCartRepository;
import com.checkoutabandonment.recoveryplugin.dao.OrderRepository;
import com.checkoutabandonment.recoveryplugin.dao.SchedulerRepository;
import com.checkoutabandonment.recoveryplugin.entity.AbandonedCart;
import com.checkoutabandonment.recoveryplugin.entity.Order;
import com.checkoutabandonment.recoveryplugin.entity.Scheduler;
import com.checkoutabandonment.recoveryplugin.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//Controller class for Processing the AbandonedCart data coming in and creating the Scheduler events
@RestController
public class CartController {

    @Autowired
    AbandonedCartRepository abandonedCartRepository;

    @Autowired
    SchedulerRepository schedulerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SchedulerService schedulerService;

    @PostMapping("/abandoned-carts")
    public ResponseEntity<Object> createCart(@RequestBody AbandonedCart cart) {

        try{
            // Save the current cart object to the abandoned_cart table
            AbandonedCart currentCart = abandonedCartRepository.save(cart);

            if (currentCart.getEmailMarketing() && currentCart.getSmsMarketing()) {
                // Create scheduler entries
                List<Scheduler> schedulerList = new ArrayList<>();
                OffsetDateTime createdDate = currentCart.getCreatedAt();

                //creating the scheduler entries as per the defined schedule
                schedulerList.add(schedulingAssistant(currentCart, createdDate.plusMinutes(30)));
                schedulerList.add(schedulingAssistant(currentCart, createdDate.plus(1, ChronoUnit.DAYS)));
                schedulerList.add(schedulingAssistant(currentCart, createdDate.plus(3, ChronoUnit.DAYS)));


                // Save the scheduler entries to the scheduler table
                schedulerRepository.saveAll(schedulerList);
            }
            // Return a 201 Created response with the saved abandoned cart
            return ResponseEntity.status(HttpStatus.CREATED).body(currentCart);

        } catch (IllegalArgumentException e){
            // Return a 400 Bad Request response indicating that the request was invalid
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: " + e.getMessage());

        } catch (Exception e){

            // Return a 500 Internal Server Error response indicating that an error occurred
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request: " + e.getMessage());
        }
    }

    //helper method
    public Scheduler schedulingAssistant(AbandonedCart currentCart, OffsetDateTime scheduledTime) {

        return new Scheduler(currentCart.getCartToken(), scheduledTime, currentCart.getEmail(), currentCart.getContactNumber(), "Planned");

    }

    //If order is placed update it in the orders table and discard it from the scheduled table
    //Also update the AbandonedCartDetails table
    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestBody Order order) {

        try{
            //save the order to the orders database
            Order savedOrder = orderRepository.save(order);

            //update the status of the corresponding scheduler entity
            schedulerService.updateSchedulerEventStatus(order.getCartToken());

            // Return the saved Order with HTTP status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);

        } catch (IllegalArgumentException e){

            // Return a 400 Bad Request response indicating that the request was invalid
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: " + e.getMessage());

        } catch (Exception e){

            // Return a 500 Internal Server Error response indicating that an error occurred
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request: " + e.getMessage());
        }
    }
}
