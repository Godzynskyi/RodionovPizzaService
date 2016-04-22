package ua.rd.pizzaservice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.Pizza.PizzaType;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.service.OrderService;

/**
 *
 * @author andrii
 */
public class SpringPizzaApp {

    public static void main(String[] args) {

//        ConfigurableApplicationContext repositoryContext
//                = new ClassPathXmlApplicationContext(
//                        "repositoryContext.xml"
//                );

        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"});
//        appContext.setParent(repositoryContext);        
//        appContext.refresh();
        
//        PizzaRepository pr
//                = (PizzaRepository) appContext.getBean("inMemPizzaRepository");
//        System.out.println(pr.getPizzaByID(1));
//
        OrderService orderService = (OrderService) appContext.getBean("simpleOrderService");
        Order order = orderService.placeNewOrder(null, 1);
//        Order order1 = orderService.placeNewOrder(null, 1);
//
        PizzaRepository pizzaRepository = appContext.getBean(PizzaRepository.class); 
//        System.out.println(pizzaRepository.addPizza(new Pizza(56,"Fifth",45.6,PizzaType.VEGETERIAN)));
//        System.out.println(order.equals(order1));
//        Pizza pizza = pizzaRepository.getPizzaByID(5);
//        pizza.setName("");
//        pizzaRepository.updatePizza(pizza);
        System.out.println(pizzaRepository.deletePizza(5));
//        System.out.println(order);

//        Customer customer = appContext.getBean(Customer.class);
//        System.out.println(customer);

        
        
//        Order order = (Order) appContext.getBean("order");
//        System.out.println(order);
        
//        ApplicationContext parent = appContext.getParent();
//        System.out.println("Parent: " + parent);

        appContext.close();
//        repositoryContext.close();

    }
}
