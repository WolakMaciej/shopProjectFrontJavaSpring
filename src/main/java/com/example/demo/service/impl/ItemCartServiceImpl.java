package com.example.demo.service.impl;



import com.example.demo.model.ItemCart;
import com.example.demo.repository.ItemCartRepository;
import com.example.demo.service.ItemCartService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ItemCartServiceImpl implements ItemCartService {

    @Autowired
    private ItemCartRepository itemCartRepository;

    @Override
    public List<ItemCart> getAll() {
        return itemCartRepository.findAll();
    }

    @Override
    public ItemCart createNew(ItemCart itemCart) {
        itemCart.setId(null);
        return itemCartRepository.save(itemCart);
    }

    @Override
    public ItemCart getOne(long id) {
        return itemCartRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(long id) {
        itemCartRepository.deleteById(id);
    }

    @Override
    public void update(ItemCart itemCart) {
        itemCart.setId(itemCart.getId());
        itemCartRepository.save(itemCart);
    }

}
