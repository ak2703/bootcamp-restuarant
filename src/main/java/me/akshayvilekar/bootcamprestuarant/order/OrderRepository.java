package me.akshayvilekar.bootcamprestuarant.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

	List<Order> findAllByItemsIn(List<OrderMenuItemEntity> orderItems);

}
