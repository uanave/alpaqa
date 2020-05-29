package com.alpaqinglist.alpaqa.data;

import com.alpaqinglist.alpaqa.persistence.domain.Item;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class BackpackDTO {

    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;
    private String type;
    private List<Item> items;
    private String imagePath;
    private int totalWeight;

    public BackpackDTO() {
    }

    public BackpackDTO(long id, @NotEmpty String name, @NotEmpty String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public BackpackDTO(@NotEmpty String name, @NotEmpty String description) {
        this.name = name;
        this.description = description;
    }

    public BackpackDTO(long id, @NotEmpty String name, @NotEmpty String description, List<Item> items) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
    }

    public BackpackDTO(long id, @NotEmpty String name, @NotEmpty String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                Objects.equals(type, that.type) &&
                Objects.equals(items, that.items) &&
                Objects.equals(imagePath, that.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, items, imagePath, totalWeight);
    }
}
