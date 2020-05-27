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

    private double volume;

    @Column(length = 200)
    private String description;
    private String category;
    private String image;
    private List<Item> items = new ArrayList<>();

    public Backpack() {
    }

    public Backpack(String name, double volume, String description, String category, String image, List<Item> items) {
        this.name = name;
        this.volume = volume;
        this.description = description;
        this.category = category;
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return Double.compare(backpack.volume, volume) == 0 &&
                Objects.equals(id, backpack.id) &&
                Objects.equals(name, backpack.name) &&
                Objects.equals(description, backpack.description) &&
                Objects.equals(category, backpack.category) &&
                Objects.equals(image, backpack.image) &&
                Objects.equals(items, backpack.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, volume, description, category, image, items);
    }
}
