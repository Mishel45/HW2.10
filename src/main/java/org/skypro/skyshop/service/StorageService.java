package org.skypro.skyshop.service;


import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        fillStorage();
    }

    private void fillStorage() {
        UUID id1 = UUID.randomUUID();
        products.put(id1, new SimpleProduct(id1, "Ножовка", 150));
        UUID id2 = UUID.randomUUID();
        products.put(id2, new DiscountedProduct(id2, "Мастерок", 180, 25));
        UUID id3 = UUID.randomUUID();
        products.put(id3, new FixPriceProduct(id3, "Топор"));
        UUID id4 = UUID.randomUUID();
        articles.put(id4, new Article(id4, "Применение ножовки", "Ножовка нужна для распилки досок"));

    }

    public Collection<Product> getAllProduct() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getSearchable() {
        List<Searchable> all = new ArrayList<>();
        all.addAll(products.values());
        all.addAll(articles.values());
        return all;
    }

    public Optional<Product> getProductById (UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
