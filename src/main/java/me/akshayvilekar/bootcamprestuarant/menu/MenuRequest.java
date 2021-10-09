package me.akshayvilekar.bootcamprestuarant.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

public class MenuRequest {
	@NonNull
	private String name;
	@NonNull
	private List<String> ingredients = new ArrayList<>();
	@NonNull
	private Integer timeToPrepare;
	@NonNull
	private Double price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public Integer getTimeToPrepare() {
		return timeToPrepare;
	}
	public void setTimeToPrepare(Integer timeToPrepare) {
		this.timeToPrepare = timeToPrepare;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}
