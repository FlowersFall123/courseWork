package service;

import entity.Product;

public interface ProductService {
    Product addProduct(Product product);
    Product getProduct(Long id);
}
