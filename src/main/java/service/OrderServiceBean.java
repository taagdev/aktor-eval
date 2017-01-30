package service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import model.Order;
import repository.OrderRepository;

public class OrderServiceBean implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Override
	public Collection<Order> findAll() {
		Collection<Order> orders = orderRepository.findAll();
		return orders;
	}


	@Override
	public Order findOne(Long id) {
		Order order =orderRepository.findOne(id);
		return order;
	}

	@Override
	public Order create(Order order) {
	//	if (order.getId() !=null){
	//		return null;
	//	}
		Order savedOrder = orderRepository.save(order);
		return savedOrder;
	}	

	@Override
	public Order update(Order order) {
		Order orderPersisted = findOne(order.getId());
		if(orderPersisted == null){
			return null;			
		}
		
		Order updatedOrder = orderRepository.save(order);
		return updatedOrder;
	}
	
	@Override
	public void delete(Long id) {
		
		orderRepository.delete(id);
	}

}
