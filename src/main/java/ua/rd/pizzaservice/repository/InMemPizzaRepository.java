package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ua.rd.pizzaservice.infrustructure.PostConstruction;

/**
 *
 * @author andrii
 */
public class InMemPizzaRepository implements PizzaRepository {

    private List<Pizza> pizzas = new ArrayList<>();

    
//    public InMemPizzaRepository() {
//		cookPizzas();
//	}

	public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }    

    @PostConstruct
    public void cookPizzas() {
        pizzas.add(new Pizza(1, "Pizza 1", 102.5, Pizza.PizzaType.SEA));
        pizzas.add(new Pizza(2, "Pizza 2", 83.4, Pizza.PizzaType.VEGETERIAN));
        pizzas.add(new Pizza(3, "Pizza 3", 183.4, Pizza.PizzaType.MEAT));
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        int index = id - 1;
        return pizzas.get(index);
    }

	@Override
	public Integer addPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePizza(Pizza pizza) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePizza(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
