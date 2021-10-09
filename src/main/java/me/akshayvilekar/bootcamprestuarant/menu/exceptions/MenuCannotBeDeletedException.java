package me.akshayvilekar.bootcamprestuarant.menu.exceptions;

public class MenuCannotBeDeletedException extends Exception {

	public MenuCannotBeDeletedException(Integer id) {
	    super("Cannot delete menu - " + id + ". Pending/Processing orders exist.");
	  }
}
