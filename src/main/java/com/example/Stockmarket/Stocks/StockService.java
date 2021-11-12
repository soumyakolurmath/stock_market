package com.example.Stockmarket.Stocks;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StockService {
    public static List<Stock> stocks = new ArrayList<>();

    static {
        stocks.add(new Stock(stocks.size() + 1, "HDFC", 1500, 3));
        stocks.add(new Stock(2, "Bajaj", 6200, 1));
        stocks.add(new Stock(3, "SBI", 600, -2));
        stocks.add(new Stock(4, "MindTree", 1570, 5));
        stocks.add(new Stock(5, "UjjivanSFB", 25, 3));
    }

    public List<Stock> findall() {
        return stocks;
    }

    public Stock save(Stock newStock) {
//        checkIfDuplicate(ne)
//        for (Stock stock : stocks) {
//            if (stock.getName().equals(newStock.getName())) {
//                throw new StockAlreadyExists(stock);
//            }
//        }

        if (newStock.getPrice() <= 0) {
            throw new StockPriceIsInvalid(newStock);
        }

        int newId = stocks.size() + 1;
        newStock.setId(newId);
        stocks.add(newStock);
        return newStock;
    }

    public Stock findOne(int id) {
        for (Stock stock : stocks) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }

    public Stock findSameName(String stockName) {
        for (Stock stock : stocks) {
            if (!stock.getName().equals(stockName)) {
                return stock;
            }
        }
        return null;
    }

}

class StockAlreadyExists extends RuntimeException {
    private Stock stock;

    public StockAlreadyExists(Stock stock) {
        super();
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }
}

class StockPriceIsInvalid extends RuntimeException {
    private Stock stock;

    public StockPriceIsInvalid(Stock stock) {
        super();
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }
}