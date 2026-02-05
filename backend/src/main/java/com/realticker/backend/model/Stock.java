package com.realticker.backend.model;

import java.util.List;

public class Stock {

    private String ticker;
    private String company;
    private double price;
    private double change;
    private long volume;
    private List<Double> history;

    public Stock(String ticker, String company, double price, double change, long volume, List<Double> history) {
        this.ticker = ticker;
        this.company = company;
        this.price = price;
        this.change = change;
        this.volume = volume;
        this.history = history;
    }

    public String getTicker() { return ticker; }
    public String getCompany() { return company; }
    public double getPrice() { return price; }
    public double getChange() { return change; }
    public long getVolume() { return volume; }
    public List<Double> getHistory() { return history; }
}

