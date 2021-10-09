package me.akshayvilekar.bootcamprestuarant.order;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.akshayvilekar.bootcamprestuarant.menu.Menu;

@Repository
public interface OrderMenuItemEntityRepository extends CrudRepository<OrderMenuItemEntity, Integer> {

	List<OrderMenuItemEntity> findAllByMenu(Menu menu);

}
