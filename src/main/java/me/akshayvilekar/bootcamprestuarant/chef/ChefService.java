package me.akshayvilekar.bootcamprestuarant.chef;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP.Channel;

import me.akshayvilekar.bootcamprestuarant.BootcampRestuarantApplication;
import me.akshayvilekar.bootcamprestuarant.order.Order;
import me.akshayvilekar.bootcamprestuarant.order.OrderItem;
import me.akshayvilekar.bootcamprestuarant.order.OrderRepository;
import me.akshayvilekar.bootcamprestuarant.order.OrderRequest;
import me.akshayvilekar.bootcamprestuarant.order.OrderRequestForChef;
import me.akshayvilekar.bootcamprestuarant.order.OrderStatus;

@Component
public class ChefService {

	@Autowired
	private OrderRepository orderRepository;
	
	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveOrder(OrderRequestForChef order) throws InterruptedException {

		System.out.println("Received Order ID : <" + order.getOrder().getId() + ">");
		
		Order orderTemp = order.getOrder();
		orderTemp.setStatus(OrderStatus.PROCESSING);
		orderTemp = orderRepository.save(orderTemp);
		
		System.out.println("Waiting for " + order.getTotalTimeToPrepare()  + " seconds to prepare order " + orderTemp.getId());
		Thread.sleep(order.getTotalTimeToPrepare()*1000);
		
		orderTemp.setStatus(OrderStatus.COMPLETE);
		orderRepository.save(orderTemp);
		
		System.out.println("Processed order ->  " + orderTemp.getId());
        latch.countDown();

	}

	public CountDownLatch getLatch() {
		return latch;
	}

	

}