package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public abstract class Product implements Searchable {
    private  final UUID id;
    private final String name;

    public Product(UUID id, String name) throws IllegalArgumentException {
        if ((id == null)) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.id = id;
        this.name = name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + ": " + getPrice() + " руб.";
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return name;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getName());
    }
}

