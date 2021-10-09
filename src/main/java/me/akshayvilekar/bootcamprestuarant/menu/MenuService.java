package me.akshayvilekar.bootcamprestuarant.menu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.akshayvilekar.bootcamprestuarant.menu.exceptions.MenuCannotBeDeletedException;
import me.akshayvilekar.bootcamprestuarant.menu.exceptions.MenuNotFoundException;
import me.akshayvilekar.bootcamprestuarant.order.Order;
import me.akshayvilekar.bootcamprestuarant.order.OrderMenuItemEntity;
import me.akshayvilekar.bootcamprestuarant.order.OrderMenuItemEntityRepository;
import me.akshayvilekar.bootcamprestuarant.order.OrderRepository;
import me.akshayvilekar.bootcamprestuarant.order.OrderStatus;

@Service
public class MenuService {

	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	OrderMenuItemEntityRepository orderMenuItemEntityRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	public Iterable<Menu> getAll() {
		return menuRepository.findAll();
	}

	public Menu getById(Integer id) {
		return menuRepository.findById(id).orElseThrow(()-> new MenuNotFoundException(id));
	}

	public Menu save(MenuRequest menuRequest) {
		Menu menu = new Menu();
		menu.setIngredients(menuRequest.getIngredients());
		menu.setName(menuRequest.getName());
		menu.setPrice(menuRequest.getPrice());
		menu.setTimeToPrepare(menuRequest.getTimeToPrepare());
		
		menu = menuRepository.save(menu);
		return menu;
	}

	public Menu update(Integer id, MenuRequest menuRequest) {
		Menu menu =  menuRepository.findById(id).orElseThrow(()-> new MenuNotFoundException(id));
		menu.setIngredients(menuRequest.getIngredients());
		menu.setName(menuRequest.getName());
		menu.setPrice(menuRequest.getPrice());
		menu.setTimeToPrepare(menuRequest.getTimeToPrepare());
		
		menu = menuRepository.save(menu);
		return menu;
		
	}

	public String delete(Integer id) throws MenuCannotBeDeletedException{
		
		Menu menu =  menuRepository.findById(id).orElseThrow(()-> new MenuNotFoundException(id));
		
		// check if there are any pending or processing orders with this menu item
		List<OrderMenuItemEntity> orderItems = orderMenuItemEntityRepository.findAllByMenu(menu);
		
		List<Order> orders = orderRepository.findAllByItemsIn(orderItems);
		
		// if any order or pending / processing status exist, we can not delete this menu
		List<Order> unprocessed = orders.stream().
		filter(o -> o.getStatus().equals(OrderStatus.PENDING) ||
				o.getStatus().equals(OrderStatus.PROCESSING)).collect(Collectors.toList());
		if(unprocessed.size() != 0) {
			throw new MenuCannotBeDeletedException(id);
		}
		else {
			 orders.stream().forEach((o)->{
				orderMenuItemEntityRepository.deleteAll(o.getItems());
			 });
			orderRepository.deleteAll(orders);
 		 menuRepository.delete(menu);
			return "Menu deleted ";
		}
	
	}
	
	
}
