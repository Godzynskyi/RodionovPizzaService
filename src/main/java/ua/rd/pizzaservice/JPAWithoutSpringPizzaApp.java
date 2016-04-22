package ua.rd.pizzaservice;

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
//		customer.setId(new Integer(1));
		customer.setName("Ivan");
		customer.setAddress(address);
		
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		
		System.out.println("persisted");
		
		System.out.println(em.find(Customer.class, 1));
		
			
			em.close();
			emf.close();
	}
}