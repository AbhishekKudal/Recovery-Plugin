package com.checkoutabandonment.recoveryplugin.service;

import com.checkoutabandonment.recoveryplugin.dao.AbandonedCartRepository;
import com.checkoutabandonment.recoveryplugin.dao.SchedulerRepository;
import com.checkoutabandonment.recoveryplugin.entity.AbandonedCart;
import com.checkoutabandonment.recoveryplugin.entity.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Component
public class SchedulerService {

    @Autowired
    private SchedulerRepository schedulerRepository;

    @Autowired
    private AbandonedCartRepository abandonedCartRepository;

    @Scheduled(fixedRate = 60000)                       //fixed time after which the method will run again (time in milliseconds)
    public void processPlannedEvents(){

        //Get the events which are Planned
        List<Scheduler> scheduledEvents = schedulerRepository.findByCurrentStatus("Planned");

        //Get the current system time
        OffsetDateTime currentDateTime = OffsetDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        System.out.println("Scheduler run on: " + currentDateTime.toString());

        for (Scheduler event: scheduledEvents) {
            OffsetDateTime scheduledTime = event.getScheduledTime().truncatedTo(ChronoUnit.MINUTES);

            //Check if the scheduled time and current system time is same
            if(scheduledTime.isEqual(currentDateTime)){
                String email = event.getEmail();
                String contactNumber = event.getContactNumber();
                String cartToken = event.getCartToken();

                // send email and SMS message
                sendEmail(email, cartToken);
                sendSMS(contactNumber, cartToken);

                // update the cart status
                event.setCurrentStatus("Sent");
                schedulerRepository.save(event);
            }
        }
    }

    private void sendEmail(String email, String cartToken) {
        // implementation for sending email
        System.out.println("Mail sent for " + cartToken + "Recipient email: " + email);
    }

    private void sendSMS(String contactNumber, String cartToken) {
        // implementation for sending SMS message
        System.out.println("Mail sent for " + cartToken + "Recipient contact-number: " + contactNumber);
    }


    //Update method to update the Scheduler events and AbandonedCart in case user places an order
    @Transactional
    public void updateSchedulerEventStatus(String cartToken) {
        List<Scheduler> scheduledEvents = schedulerRepository.findAll();
        List<AbandonedCart> abandonedCarts = abandonedCartRepository.findAll();

        for (Scheduler event: scheduledEvents) {
            if(event.getCartToken().equals(cartToken)){
                if(event.getCurrentStatus().equals("Planned")){
                    event.setCurrentStatus("Discarded");
                    schedulerRepository.save(event);
                }
            }
        }

        for(AbandonedCart cart: abandonedCarts){
            if(cart.getCartToken().equals(cartToken)){
                cart.setCurrentStatus(true);
                abandonedCartRepository.save(cart);
            }
        }
    }
}
