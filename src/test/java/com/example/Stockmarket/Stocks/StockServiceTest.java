package com.example.Stockmarket.Stocks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockServiceTest {
    @Test
    void name() {
        new StockService();
        new StockService();
        new StockService();
        System.out.println(StockService.stocks.size());
    }
}