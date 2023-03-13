package com.checkoutabandonment.recoveryplugin.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Entity Class for Order objects
@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cart_token")
    private String cartToken;

    // Setter method for cartToken
    public void setCartToken(String cartToken) {
        // Validate cartToken
        if (cartToken == null || cartToken.isEmpty()) {
            throw new IllegalArgumentException("Invalid cart token");
        }

        this.cartToken = cartToken;
    }
}
