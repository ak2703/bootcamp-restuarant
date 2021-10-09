package me.akshayvilekar.bootcamprestuarant;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.akshayvilekar.bootcamprestuarant.menu.Menu;
import me.akshayvilekar.bootcamprestuarant.menu.MenuRepository;

@Configuration
class LoadMenus {

  private static final Logger log = LoggerFactory.getLogger(LoadMenus.class);

  @Bean
  CommandLineRunner initDatabase(MenuRepository repository) {

	  if(((List<Menu>)repository.findAll()).size()==0) {
		  return args -> {
		      log.info("Preloading " + repository.save(new Menu("Sushi", Arrays.asList("Fish", "Rice", "Wasabi"), 10, 100.00)));
		      log.info("Preloading " + repository.save(new Menu("Pizza", Arrays.asList("Pepporoni", "Dough", "Cheese"), 20, 100.00)));
		    };  
	  }
	  return args -> {
	    };
  }
}