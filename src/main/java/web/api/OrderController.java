package web.api;

import java.util.Collection;

import model.Order;
import service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(
            value = "/api/orders",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Order>> getOrders() {

        Collection<Order> orders = orderService.findAll();

        return new ResponseEntity<Collection<Order>>(orders,
                HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/orders/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {

        Order order = orderService.findOne(id);
        if (order == null) {
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/orders",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(
            @RequestBody Order order) {

        Order savedOrder = orderService.create(order);

        return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/orders/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrder(
            @RequestBody Order order) {

        Order updatedOrder = orderService.update(order);
        if (updatedOrder == null) {
            return new ResponseEntity<Order>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Order>(updatedOrder, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/orders/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long id,
            @RequestBody Order order) {

        orderService.delete(id);

        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

}
