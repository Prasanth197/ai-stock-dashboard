package com.realticker.backend.service;

import com.realticker.backend.data.StockRepository;
import com.realticker.backend.model.Stock;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private final StockRepository repo;

    public StockService(StockRepository repo) {
        this.repo = repo;
    }

    public List<Stock> getTop10() {
        return repo.getAll()
                .stream()
                .sorted(Comparator.comparingLong(Stock::getVolume).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public Stock getHistory(String ticker) {
        return repo.getByTicker(ticker);
    }
}
