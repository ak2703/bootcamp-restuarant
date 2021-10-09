package me.akshayvilekar.bootcamprestuarant.waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.akshayvilekar.bootcamprestuarant.BootcampRestuarantApplication;
import me.akshayvilekar.bootcamprestuarant.chef.ChefService;
import me.akshayvilekar.bootcamprestuarant.menu.Menu;
import me.akshayvilekar.bootcamprestuarant.menu.MenuService;
import me.akshayvilekar.bootcamprestuarant.menu.exceptions.MenuNotFoundException;
import me.akshayvilekar.bootcamprestuarant.order.Order;
import me.akshayvilekar.bootcamprestuarant.order.OrderItem;
import me.akshayvilekar.bootcamprestuarant.order.OrderMenuItemEntity;
import me.akshayvilekar.bootcamprestuarant.order.OrderMenuItemEntityRepository;
import me.akshayvilekar.bootcamprestuarant.order.OrderRepository;
import me.akshayvilekar.bootcamprestuarant.order.OrderRequest;
import me.akshayvilekar.bootcamprestuarant.order.OrderRequestForChef;
import me.akshayvilekar.bootcamprestuarant.order.OrderStatus;
import me.akshayvilekar.bootcamprestuarant.order.exceptions.OrderNotFoundException;

@Service
public class WaiterService {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMenuItemEntityRepository orderMenuItemEntityRepository;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ChefService chefService;

	public String placeOrder(OrderRequest orderRequest) throws InterruptedException {
		Integer totalTimeToWait = 0;

		Order order = new Order();
		order.setStatus(OrderStatus.PENDING);
		
		List<OrderMenuItemEntity> items = new ArrayList();
		
		for(OrderItem item: orderRequest.getItems()) {
			OrderMenuItemEntity orderMenuItem = new OrderMenuItemEntity();
			
			Menu menu = menuService.getById(item.getMenuId());
			if (menu != null) {
				item.setMenu(menu);
				orderMenuItem.setMenu(menu);
				orderMenuItem.setQuantity(item.getQuantity());
				orderMenuItem = orderMenuItemEntityRepository.save(orderMenuItem);
				items.add(orderMenuItem);
			}
		}
		order.setItems(items);
		
		for (OrderItem item : orderRequest.getItems()) {
			totalTimeToWait += item.getQuantity() * item.getMenu().getTimeToPrepare();
		}
		order.setDuration(totalTimeToWait);
		order = orderRepository.save(order);

		OrderRequestForChef orderRequestForChef = new OrderRequestForChef();
		orderRequestForChef.setOrder(order);
		orderRequestForChef.setTotalTimeToPrepare(totalTimeToWait);
		
	    rabbitTemplate.convertAndSend(BootcampRestuarantApplication.topicExchangeName, "foo.bar.baz", orderRequestForChef);
	    chefService.getLatch().await(10, TimeUnit.MICROSECONDS);
		return "Queued order -> " + order.getId();
	}

	public String getOrderStatus(Integer orderId) {
		
		Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException(orderId));
		return order.getStatus().name();
	}

	public Order getOrder(Integer id) {
		return orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException(id));
	}

}
