package me.akshayvilekar.bootcamprestuarant.order;

import java.io.Serializable;

import me.akshayvilekar.bootcamprestuarant.menu.Menu;

public class OrderRequestForChef implements Serializable{
	
	private Order order;
	private Integer totalTimeToPrepare;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Integer getTotalTimeToPrepare() {
		return totalTimeToPrepare;
	}
	public void setTotalTimeToPrepare(Integer totalTimeToPrepare) {
		this.totalTimeToPrepare = totalTimeToPrepare;
	}
	
	
}
