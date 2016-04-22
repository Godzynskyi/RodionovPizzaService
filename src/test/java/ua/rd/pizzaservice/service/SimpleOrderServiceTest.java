package ua.rd.pizzaservice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("appContext.xml")
public class SimpleOrderServiceTest {
	
//	@Autowired
//	Customer customer;
//	@Autowired
//	OrderService orderService;
//	
//	@Test
//	public void placeNewOrderTest() {
//		
//		Order order = orderService.placeNewOrder(customer, 1, 2, 2);
//		
//		int actual = order.getPizzas().size();
//		int expected = 3;
//		
//		assertEquals(expected, actual);
//	}
	
}
