package com.alpaqinglist.alpaqa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @Positive
    @NotNull
    private Long weight;

    @Positive
    private double volume;

    @NotEmpty
    private String description;
    private int quantity;

    @NotEmpty
    private String priority;
    private String image;

    public Item() {
    }

    public Item(@NotEmpty String name, @Positive @NotNull Long weight, @Positive double volume, @NotEmpty String description, int quantity, @NotEmpty String priority, String image) {
        this.name = name;
        this.weight = weight;
        this.volume = volume;
        this.description = description;
        this.quantity = quantity;
        this.priority = priority;
        this.image = image;
    }

    public Item(@NotEmpty String name) {
        this.name = name;
    }

    public Item(@NotEmpty String name, @Positive @NotNull Long weight) {
        this.name = name;
        this.weight = weight;
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

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.volume, volume) == 0 &&
                quantity == item.quantity &&
                Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(weight, item.weight) &&
                Objects.equals(description, item.description) &&
                Objects.equals(priority, item.priority) &&
                Objects.equals(image, item.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, volume, description, quantity, priority, image);
    }
}
