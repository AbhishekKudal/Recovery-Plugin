package com.checkoutabandonment.recoveryplugin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

//Entity Class for Scheduler objects
@Entity
@Table(name = "scheduler")
@Getter
@Setter
public class Scheduler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cart_token")
    private String cartToken;

    @Column(name = "scheduled_time")
    private OffsetDateTime scheduledTime;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "current_status")
    private String currentStatus;


    public Scheduler() {

    }


    public Scheduler(String cartToken, OffsetDateTime scheduledTime, String email, String contactNumber, String currentStatus) {
        this.cartToken = cartToken;
        this.scheduledTime = scheduledTime;
        this.email = email;
        this.contactNumber = contactNumber;
        this.currentStatus = currentStatus;
    }
}
