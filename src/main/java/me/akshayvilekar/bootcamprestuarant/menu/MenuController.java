package me.akshayvilekar.bootcamprestuarant.menu;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import me.akshayvilekar.bootcamprestuarant.menu.exceptions.MenuCannotBeDeletedException;

@RestController()
@RequestMapping(path="/menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@GetMapping("/")
	public @ResponseBody Iterable<Menu> getAll() {
		return menuService.getAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Menu get(@PathVariable("id") Integer id) {
		return menuService.getById(id);
	}
	
	@PostMapping("/")
	public @ResponseBody Menu save(@RequestBody MenuRequest menuRequest) {
		return menuService.save(menuRequest);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody Menu update(@PathVariable("id") Integer id, @RequestBody MenuRequest menuRequest) {
		return menuService.update(id, menuRequest);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody String delete(@PathVariable("id") Integer id) throws MenuCannotBeDeletedException {
		return menuService.delete(id);
	}
}
