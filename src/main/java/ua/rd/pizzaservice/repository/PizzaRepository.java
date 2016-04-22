package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;

/**
 *
 * @author andrii
 */
public interface PizzaRepository {
	
    Integer addPizza(Pizza pizza);
    Pizza getPizzaByID(Integer id);
    int updatePizza(Pizza pizza);
    int deletePizza(Integer id);
    
}
