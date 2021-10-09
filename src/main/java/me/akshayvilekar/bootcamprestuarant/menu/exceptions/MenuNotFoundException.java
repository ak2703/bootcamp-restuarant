package me.akshayvilekar.bootcamprestuarant.menu.exceptions;

public class MenuNotFoundException extends RuntimeException {

	public MenuNotFoundException(Integer id) {
	    super("Could not find menu " + id);
	  }
}