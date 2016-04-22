package ua.rd.pizzaservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Address {
	private Integer addressId;
	private String city;
	
	public Integer getId() {
		return addressId;
	}
	public void setId(Integer id) {
		this.addressId = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
