package service;

import java.util.Collection;

import model.Product;

public interface ProductService {
	
    Collection<Product> findAll();

    Product findOne(Long id);

    Product create(Product product);

    Product update(Product product);

    void delete(Long id);

}
