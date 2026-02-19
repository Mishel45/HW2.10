package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(UUID id, String name, int price) throws IllegalArgumentException {
        super(id, name);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена товара должна быть больше нуля");
        }
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " руб.";
    }
}
