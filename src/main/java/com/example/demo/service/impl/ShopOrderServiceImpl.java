package com.example.demo.service.impl;


import com.example.demo.model.ShopOrder;
import com.example.demo.repository.ShopOrderRepository;
import com.example.demo.service.ShopOrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Override
    public List<ShopOrder> getAll() {
        return shopOrderRepository.findAll();
    }

    @Override
    public ShopOrder createNew(ShopOrder shopOrder) {
        shopOrder.setId(null);
        return shopOrderRepository.save(shopOrder);
    }

    @Override
    public ShopOrder getOne(long id) {
        return shopOrderRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(long id) {
        shopOrderRepository.deleteById(id);
    }

    @Override
    public void update(ShopOrder shopOrder) {
        shopOrder.setId(shopOrder.getId());
        shopOrderRepository.save(shopOrder);
    }

}
