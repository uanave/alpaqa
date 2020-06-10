package com.alpaqinglist.alpaqa.persistence.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Backpack {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    @Column(length = 200, nullable = false)
    @Size(max = 200, message = "description cannot be longer than 200 characters")
    @NotEmpty
    private String description;
    private String category;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Item> items = new ArrayList<>();
    private String image;
    private int weight;
    private double volume;

    public Backpack() {
    }

    public Backpack(@NotEmpty String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty String description) {
        this.name = name;
        this.description = description;
    }

    public Backpack(Long id, @NotEmpty String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Backpack(Long id, @NotEmpty String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty String description, List<Item> items, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
        this.image = image;
    }

    public Backpack(Long id, @NotEmpty String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty String description, List<Item> items) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Backpack backpack = (Backpack) o;
        return weight == backpack.weight &&
                Double.compare(backpack.volume, volume) == 0 &&
                Objects.equals(id, backpack.id) &&
                Objects.equals(name, backpack.name) &&
                Objects.equals(description, backpack.description) &&
                Objects.equals(category, backpack.category) &&
                Objects.equals(items, backpack.items) &&
                Objects.equals(image, backpack.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, items, image, weight, volume);
    }
}
