package com.example.demo.service.impl;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product createNew(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @Override
    public Product getOne(long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void update(Product product) {
        product.setId(product.getId());
        productRepository.save(product);
    }


}
