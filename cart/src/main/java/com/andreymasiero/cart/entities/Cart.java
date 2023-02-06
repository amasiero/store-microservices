package com.andreymasiero.cart.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.andreymasiero.dtos.cart.CartDto;


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
