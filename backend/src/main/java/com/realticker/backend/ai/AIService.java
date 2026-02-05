package com.realticker.backend.ai;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIService {

    public String analyzeStock(List<Double> history) {

        double start = history.get(0);
        double end = history.get(history.size() - 1);

        String trend;
        if (end > start * 1.05) trend = "Strong Upward Trend";
        else if (end > start) trend = "Mild Upward Trend";
        else if (end < start * 0.95) trend = "Strong Downward Trend";
        else trend = "Sideways Market";

        double volatility = 0;
        for (int i = 1; i < history.size(); i++) {
            volatility += Math.abs(history.get(i) - history.get(i - 1));
        }
        volatility /= history.size();

        String risk;
        if (volatility > start * 0.015) risk = "High Risk";
        else if (volatility > start * 0.007) risk = "Medium Risk";
        else risk = "Low Risk";

        String advice;

        if (trend.contains("Upward") && risk.equals("Low Risk")) {
            advice = "Good opportunity for long-term investment with stable growth.";
        } 
        else if (trend.contains("Upward")) {
            advice = "Positive trend but monitor volatility before investing heavily.";
        } 
        else if (trend.contains("Sideways")) {
            advice = "Market is stable — suitable for short-term trading.";
        } 
        else {
            advice = "Downward trend detected — better to wait or avoid for now.";
        }

        return """
        📈 Trend: %s
        ⚠ Risk Level: %s
        💡 AI Recommendation: %s

        ⚠ Disclaimer: This is AI-generated analysis for educational purposes only.
        """.formatted(trend, risk, advice);
    }
}



