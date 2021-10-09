package me.akshayvilekar.bootcamprestuarant.order;

import java.io.Serializable;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.akshayvilekar.bootcamprestuarant.menu.Menu;


public class OrderItem implements Serializable {

	private Integer menuId;
	private Integer quantity;
	
	private Menu menu;
	
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
