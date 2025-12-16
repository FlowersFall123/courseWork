package service.impl;

import entity.Product;
import org.springframework.stereotype.Service;
import service.ProductService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {
    private Map<Long, Product> productMap=new HashMap<Long, Product>();
    private AtomicLong generator=new AtomicLong();

    public ProductServiceImpl() {
        Product product=new Product();
        product.setName("product1");
        product.setDescription("product1 description");
        product.setPrice(1.0);
        addProduct(product);
    }

    @Override
    public Product addProduct(Product product) {
        long newId=generator.incrementAndGet();
        product.setId(newId);
        productMap.put(newId, product);
        return product;
    }

    @Override
    public Product getProduct(Long id) {
        return productMap.get(id);
    }
}
