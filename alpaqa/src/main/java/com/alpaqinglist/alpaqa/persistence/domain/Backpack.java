package com.alpaqinglist.alpaqa.persistence.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Backpack {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany
    private User user;
    private Long weight;

    @Column(length = 200)
    private String description;
    private String image;
    private List<Item> items = new ArrayList<>();

    public Backpack() {
    }

    public Backpack(String name, User user, Long weight, String description, String image, List<Item> items) {
        this.name = name;
        this.user = user;
        this.weight = weight;
        this.description = description;
        this.image = image;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        Backpack backpack = (Backpack) o;
        return Objects.equals(id, backpack.id) &&
                Objects.equals(name, backpack.name) &&
                Objects.equals(user, backpack.user) &&
                Objects.equals(weight, backpack.weight) &&
                Objects.equals(description, backpack.description) &&
                Objects.equals(image, backpack.image) &&
                Objects.equals(items, backpack.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user, weight, description, image, items);
    }
}
