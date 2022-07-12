package com.stacksimplify.restServices.springbootbuildingblocks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity extends RepresentationModel {

    @Id // responsabila pentru a marca cheia primara
    @GeneratedValue // putem defini strategia de generare a cheii primare (din 4 posibile)
    private Long orderId;

    private String orderDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // Impiedica sa ajungem in infinite loops
    // In cazul de fata pana nu se ajunge sa se gaseasca o instanta de tip order entity nu se face assignarea entitatii de tip utilizator
    private UserEntity user;

    public OrderEntity() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
