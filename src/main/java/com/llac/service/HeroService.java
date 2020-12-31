package com.llac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.llac.model.Hero;
import com.llac.repository.HeroRepository;

@Service
public class HeroService {

	@Autowired
	private HeroRepository heroRepository;

	public Hero insert(Hero hero) {
		return this.heroRepository.save(hero);
	}

	public Page<Hero> getAllHeroes(Pageable pageable) {
		return heroRepository.findAll(pageable);
	}
	
	public List<Hero> getAllHeroes() {
		return heroRepository.findAll();
	}
	
	public Page<Hero> getHeroesByCompany(Pageable pageable, Long company_id){
		return heroRepository.getHeroesByCompany(pageable, company_id);
	}
	
	public Hero update(Hero hero, Long id) {
		Hero newHero = this.heroRepository.findById(id).get();
		newHero.setName(hero.getName());
		newHero.setCompany(hero.getCompany());
		
		return this.heroRepository.save(newHero);
	}
	
	public void delete(Long id) {
		heroRepository.deleteById(id);
	}
	
	
	

}
