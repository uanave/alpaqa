package com.alpaqinglist.alpaqa.persistence.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String username;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @ElementCollection
    private Set<Backpack> backpacks = new HashSet<>();

    @ElementCollection
    private List<Item> items = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String password, Set<Backpack> backpacks, List<Item> items) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.backpacks = backpacks;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Backpack> getBackpacks() {
        return backpacks;
    }

    public void setBackpacks(Set<Backpack> backpacks) {
        this.backpacks = backpacks;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(backpacks, user.backpacks) &&
                Objects.equals(items, user.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, backpacks, items);
    }
}
