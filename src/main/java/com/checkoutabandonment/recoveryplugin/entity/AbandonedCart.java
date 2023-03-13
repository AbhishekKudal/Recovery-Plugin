package com.checkoutabandonment.recoveryplugin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

//Entity Class for AbandonedCart objects
@Entity
@Table(name = "abandoned_cart_details")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbandonedCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "cart_token")
    private String cartToken;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email_marketing")
    private Boolean emailMarketing;

    @Column(name = "sms_marketing")
    private Boolean smsMarketing;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "current_status")
    private Boolean currentStatus;


    public AbandonedCart() {
        // Setting the currentStatus = false as the abandonedCart object is passed for the first time
        this.currentStatus = false;
    }

    // Setter method for customerId
    public void setCustomerId(Long customerId) {
        // Validate customerId
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("Invalid customer ID");
        }

        this.customerId = customerId;
    }

    // Setter method for cartToken
    public void setCartToken(String cartToken) {
        // Validate cartToken
        if (cartToken == null || cartToken.isEmpty()) {
            throw new IllegalArgumentException("Invalid cart token");
        }

        this.cartToken = cartToken;
    }

    // Setter method for email
    public void setEmail(String email) {
        // Validate email
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email address");
        }

        this.email = email;
    }

    // Setter method for contactNumber
    public void setContactNumber(String contactNumber) {
        // Validate contactNumber
        if (contactNumber == null || !contactNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Invalid contact number");
        }

        this.contactNumber = contactNumber;
    }

    // Setter method for acceptsEmailMarketing
    public void setEmailMarketing(Boolean emailMarketing) {
        // Validate acceptsEmailMarketing
        if (emailMarketing == null) {
            throw new IllegalArgumentException("Email marketing preference cannot be null");
        } else if (!emailMarketing) {
            this.emailMarketing = false;
        } else if (emailMarketing) {
            this.emailMarketing = true;
        } else {
            throw new IllegalArgumentException("Invalid value for Email marketing preference: " + emailMarketing);
        }
    }

    // Setter method for acceptsSmsMarketing
    public void setSmsMarketing(Boolean smsMarketing) {
        // Validate acceptsEmailMarketing
        if (smsMarketing == null) {
            throw new IllegalArgumentException("Email marketing preference cannot be null");
        } else if (!smsMarketing) {
            this.smsMarketing = false;
        } else if (smsMarketing) {
            this.smsMarketing = true;
        } else {
            throw new IllegalArgumentException("Invalid value for Email marketing preference: " + smsMarketing);
        }
    }

    // Setter method for createdAt
    public void setCreatedAt(OffsetDateTime createdAt) {
        // Validate createdAt
        if (createdAt == null) {
            throw new IllegalArgumentException("Invalid created at date");
        }

        this.createdAt = createdAt;
    }

    // Setter method for currentStatus
    public void setCurrentStatus(Boolean currentStatus) {
        this.currentStatus = currentStatus;
    }
}
