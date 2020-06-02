package com.alpaqinglist.alpaqa.data;

import com.alpaqinglist.alpaqa.persistence.domain.Item;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class BackpackDTO {
    private long id;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @Size(max = 200, message = "description cannot be longer than 200 characters")
    @NotEmpty(message = "description cannot be empty")
    private String description;
    private String category;
    private List<Item> items;
    private String imagePath;
    private int totalWeight;
    private double volume;


    public BackpackDTO() {
    }

    public BackpackDTO(long id, @NotEmpty(message = "name cannot be empty") String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty(message = "description cannot be empty") String description, String category, List<Item> items, String imagePath, int totalWeight, double volume) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.items = items;
        this.imagePath = imagePath;
        this.totalWeight = totalWeight;
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public BackpackDTO(long id, @NotEmpty(message = "name cannot be empty") String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty(message = "description cannot be empty") String description, List<Item> items, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
        this.imagePath = imagePath;
    }

    public BackpackDTO(long id, @NotEmpty(message = "name cannot be empty") String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty(message = "description cannot be empty") String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public BackpackDTO(@NotEmpty(message = "name cannot be empty") String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty(message = "description cannot be empty") String description) {
        this.name = name;
        this.description = description;
    }

    public BackpackDTO(long id, @NotEmpty(message = "name cannot be empty") String name, @Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty(message = "description cannot be empty") String description, List<Item> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
    }

    public BackpackDTO(long id, @NotEmpty(message = "name cannot be empty") String name,@Size(max = 200, message = "description cannot be longer than 200 characters") @NotEmpty(message = "description cannot be empty") String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BackpackDTO that = (BackpackDTO) o;
        return id == that.id &&
                totalWeight == that.totalWeight &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(items, that.items) &&
                Objects.equals(imagePath, that.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, items, imagePath, totalWeight);
    }
}