package com.realticker.backend.controller;

import com.realticker.backend.model.Stock;
import com.realticker.backend.service.StockService;
import com.realticker.backend.ai.AIService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin
public class StockController {

    private final StockService stockService;
    private final AIService aiService;

    public StockController(StockService stockService, AIService aiService) {
        this.stockService = stockService;
        this.aiService = aiService;
    }

    // 📊 Top stocks
    @GetMapping("/top10")
    public List<Stock> getTop10Stocks() {
        return stockService.getTop10();
    }

    // 📈 History
    @GetMapping("/{ticker}/history")
    public Stock getStockHistory(@PathVariable String ticker) {
        return stockService.getHistory(ticker);
    }

    // 🤖 AI Analysis (GET for browser testing)
    @GetMapping("/{ticker}/analyze")
    public String analyzeStock(@PathVariable String ticker) {
        Stock stock = stockService.getHistory(ticker);
        return aiService.analyzeStock(stock.getHistory());
    }
}


