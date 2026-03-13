package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/search")
    public Collection<SearchResult> search(String pattern) {
        return storageService.getSearchable().stream()
                .filter(s -> s.getSearchTerm().contains(pattern))
                .map(SearchResult::fromSearcheble).collect(Collectors.toList());
    }
}
