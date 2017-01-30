package service;

import java.util.Collection;

import model.Order;

public interface OrderService {
	
	   Collection<Order> findAll();

	   Order findOne(Long id);

	   Order create(Order order);

	   Order update(Order order);

	   void delete(Long id);

}
