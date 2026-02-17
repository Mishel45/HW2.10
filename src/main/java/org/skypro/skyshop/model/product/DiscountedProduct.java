package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discount;

    public DiscountedProduct(UUID id, String name, int basePrice, int discount) throws IllegalArgumentException {
        super(id, name);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Базовая цена должна быть больше 0");
        }
        this.basePrice = basePrice;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть в пределах от 0 до 100% включительно");
        }
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return (int) (basePrice * (1.0 - discount / 100.0));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": со скидкой " + getPrice() + " (скидка " + discount + "%)";
    }
}
