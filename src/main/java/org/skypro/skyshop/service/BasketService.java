package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket prductBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.prductBasket = productBasket;
        this.storageService = storageService;
    }
    public void addProduct (UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Товар с id: " + id + " не найден.");

        }
        prductBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> idsMap = prductBasket.getBasket();
        List<BasketItem> items = idsMap.entrySet().stream()
                .map(entry -> {
                    Product product = storageService.getProductById(entry.getKey()).orElseThrow(() -> new IllegalStateException("Товара нет в хранилище"));
                    return new BasketItem(product, entry.getValue());
                }).collect(Collectors.toList());
        return new UserBasket(items);
    }
}
