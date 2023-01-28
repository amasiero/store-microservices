package com.andreymasiero.cart.entities;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.andreymasiero.cart.dtos.CartDto;

@Entity(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_identifier")
    private String userIdentifier;
    private Float total;
    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "cart_id"))
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String useIdentifier) {
        this.userIdentifier = useIdentifier;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static Cart from(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setUserIdentifier(cartDto.getUserIdentifier());
        cart.setTotal(cartDto.getTotal());
        cart.setDate(cartDto.getDate());
        cart.setItems(cartDto
            .getItems()
            .stream()
            .map(Item::from)
            .collect(Collectors.toList()));
        return cart;
    }
}
