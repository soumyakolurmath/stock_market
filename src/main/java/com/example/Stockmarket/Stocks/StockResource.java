package com.example.Stockmarket.Stocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController

public class StockResource {
    @Autowired
    private StockService service;

    @GetMapping(path = "/stocks")
    public List<Stock> retriveAllStock(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "percentage_change", required = false) String percentageChange) {
        if (name == null || name.equals("")) {
            return service.findall();
        }

        List<Stock> filteredStocks = new ArrayList<>();
        for (Stock stk : service.findall()) {
            if (stk.getName().equals(name)) {
                filteredStocks.add(stk);
            }
        }
        return filteredStocks;
    }

    @PostMapping("/stocks")
    public ResponseEntity<Object> createNewTask(@RequestBody Stock stock) {
        try {
            Stock savedStock = service.save(stock);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStock.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (StockAlreadyExists exp) {
            return new ResponseEntity<>("Stock already " +
                    "exists with same name, id is " + exp.getStock().getId(), HttpStatus.BAD_REQUEST);
        } catch (StockPriceIsInvalid exp) {
            return new ResponseEntity<>(new Error(991, "Stock price is not valid, price is " + exp.getStock().getPrice()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<?> retrieveStock(@PathVariable int id) {
        Stock stock = service.findOne(id);
        if (stock == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(service.findOne(id), HttpStatus.OK);
    }
}

class Error {
    int code;
    String detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Error(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }
}