package me.akshayvilekar.bootcamprestuarant.waiter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.akshayvilekar.bootcamprestuarant.order.Order;
import me.akshayvilekar.bootcamprestuarant.order.OrderItem;
import me.akshayvilekar.bootcamprestuarant.order.OrderRequest;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

	@Autowired
	WaiterService waiterService;
	
	@PostMapping("/order")
	public @ResponseBody String placeOrder(@RequestBody OrderRequest orderRequest) throws InterruptedException {
		return waiterService.placeOrder(orderRequest);
	}
	
	@GetMapping("/order/{id}")
	public @ResponseBody Order getOrder(@PathVariable("id") Integer orderId) throws InterruptedException {
		return waiterService.getOrder(orderId);
	}
	
	@GetMapping("/order/status/{id}")
	public @ResponseBody String getOrderStatus(@PathVariable("id") Integer orderId) throws InterruptedException {
		return waiterService.getOrderStatus(orderId);
	}
}
