package ua.rd.pizzaservice;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Pizza;

public class JPAWithoutSpringPizzaApp {

	public static void main(String[] args) {
		EntityManagerFactory emf;
		EntityManager em;
		
		emf = Persistence.createEntityManagerFactory("jpa");
		em = emf.createEntityManager();

		Address address = new Address(); 
		address.setCity("Kyiv");
		
	
		Customer customer = new Customer();
		customer.setName("Ivan");
		customer.setAddress(Arrays.asList(address));
		address.setCustomer(customer);
		
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		
//		Customer c = em.find(Customer.class, 1);
//		Address a = c.getAddress();
		
			
		em.close();
		emf.close();
	}
}
