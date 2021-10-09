package me.akshayvilekar.bootcamprestuarant.order.exceptions;

import java.util.function.Supplier;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException(Integer id) {
	    super("Could not find order " + id);
	  }

}
