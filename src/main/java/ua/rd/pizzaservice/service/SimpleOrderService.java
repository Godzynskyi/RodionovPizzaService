package ua.rd.pizzaservice.service;

import ua.rd.pizzaservice.infrustructure.Benchmark;
import ua.rd.pizzaservice.infrustructure.ServiceLocator;
import ua.rd.pizzaservice.repository.InMemOrderRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.repository.InMemPizzaRepository;
import ua.rd.pizzaservice.repository.OrderRepository;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Customer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 *
 * @author andrii
 */
@Service
public class SimpleOrderService implements OrderService {

    // private ServiceLocator locator = ServiceLocator.getInstance();

	@Autowired
    OrderRepository orderRepository;
    //        = (OrderRepository) locator.lookup("orderRepository");
	@Autowired
    PizzaRepository pizzaRepository;
    //        = (PizzaRepository) locator.lookup("pizzaRepository");

	public SimpleOrderService() {}
	
    public SimpleOrderService(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
    }

//    @Benchmark
    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
//        Order newOrder = createOrder(customer, pizzas);
        Order newOrder = createOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    @Lookup
    protected Order createOrder() {
    	return null;
    }
    
//    private Order createOrder(Customer customer, List<Pizza> pizzas) {
//        Order newOrder = new Order(customer, pizzas);
//        return newOrder;
//    }

    private List<Pizza> pizzasByArrOfId(Integer[] pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Integer id : pizzasID) {
            pizzas.add(pizzaRepository.getPizzaByID(id));  // get Pizza from predifined in-memory list
        }
        return pizzas;
    }

}
