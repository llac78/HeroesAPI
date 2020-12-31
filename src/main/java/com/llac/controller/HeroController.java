package com.llac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.llac.model.Hero;
import com.llac.service.HeroService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/heroes")
public class HeroController {

	@Autowired
	private HeroService heroService;
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Hero insert(@RequestBody Hero hero) {
		return this.heroService.insert(hero);
	}
	
	@GetMapping("/list/{company_id}")
	public ResponseEntity<Page<Hero>> getHeroesByCompany(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) 
			Pageable pageable, 
			@PathVariable 
			Long company_id) {
		Page<Hero> heroPage = heroService.getHeroesByCompany(pageable, company_id);
		if (heroPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Hero>>(heroPage, HttpStatus.OK);
		}
	}
	
	@GetMapping("/list")
	public List<Hero> getAllHeroes(){
		return this.heroService.getAllHeroes();
	}
	
	@PostMapping("update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Hero update(@RequestBody Hero hero,
					   @PathVariable Long id) {
		if(id != null) {
			return this.heroService.update(hero, id);
		} else {
			return this.heroService.insert(hero);
		}
	}
	
	@PostMapping("delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.heroService.delete(id);
	}

}
