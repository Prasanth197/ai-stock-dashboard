package com.realticker.backend.data;

import com.realticker.backend.model.Stock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockRepository {

    private List<Stock> stocks = new ArrayList<>();

    public StockRepository() {

        stocks.add(new Stock("AAPL","Apple",185.4,1.2,78000000, generate(180)));
        stocks.add(new Stock("MSFT","Microsoft",412.3,0.8,45000000, generate(380)));
        stocks.add(new Stock("GOOGL","Google",145.2,1.5,62000000, generate(130)));
        stocks.add(new Stock("AMZN","Amazon",170.1,0.6,56000000, generate(150)));
        stocks.add(new Stock("TSLA","Tesla",210.7,-1.3,69000000, generate(200)));

        stocks.add(new Stock("META","Meta",320.5,1.1,48000000, generate(300)));
        stocks.add(new Stock("NFLX","Netflix",610.2,0.9,36000000, generate(580)));
        stocks.add(new Stock("NVDA","Nvidia",890.4,2.3,82000000, generate(850)));
        stocks.add(new Stock("ORCL","Oracle",130.8,0.4,29000000, generate(125)));
        stocks.add(new Stock("INTC","Intel",48.6,-0.5,41000000, generate(50)));

        stocks.add(new Stock("JPM","JP Morgan",190.2,0.7,52000000, generate(175)));
        stocks.add(new Stock("WMT","Walmart",165.3,0.3,46000000, generate(155)));
    }

    // 📈 Realistic market-like history generator (6 months)
    private List<Double> generate(double start) {

        List<Double> list = new ArrayList<>();
        double value = start;

        for(int i = 0; i < 180; i++) {

            double dailyChange = (Math.random() * 2 - 1) * (start * 0.01);
            value += dailyChange;

            // prevent unrealistic crash
            value = Math.max(value, start * 0.7);

            list.add(Math.round(value * 100.0) / 100.0);
        }

        return list;
    }

    public List<Stock> getAll() {
        return stocks;
    }

    public Stock getByTicker(String ticker) {
        return stocks.stream()
                .filter(s -> s.getTicker().equalsIgnoreCase(ticker))
                .findFirst()
                .orElse(null);
    }
}
