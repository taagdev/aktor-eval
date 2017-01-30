package service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Product;
import repository.ProductRepository;

@Service
public class ProductServiceBean implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public Collection<Product> findAll() {
		Collection<Product> product = productRepository.findAll();
		return product;
	}


	@Override
	public Product findOne(Long id) {
		Product product =productRepository.findOne(id);
		return product;
	}

	@Override
	public Product create(Product product) {
	//	if (product.getId() != null) {
	//		return null;
	//	}
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}	

	@Override
	public Product update(Product product) {
		Product productPersisted = findOne(product.getId());
		if(productPersisted == null){
			return null;			
		}
		
		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}
	
	@Override
	public void delete(Long id) {
		
		productRepository.delete(id);
	}


}

