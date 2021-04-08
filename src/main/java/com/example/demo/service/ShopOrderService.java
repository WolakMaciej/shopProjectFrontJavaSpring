package com.example.demo.service;

import com.example.demo.model.ShopOrder;

import java.util.List;

public interface ShopOrderService {
    List<ShopOrder> getAll();

    ShopOrder createNew(ShopOrder shopOrder);

    ShopOrder getOne(long id);

    void delete(long id);

    void update(ShopOrder shopOrder);
}
