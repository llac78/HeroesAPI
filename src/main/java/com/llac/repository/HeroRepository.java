package com.llac.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.llac.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
	
	@Query(value = "SELECT * FROM hero WHERE company_id = ?1 ", nativeQuery = true)
	public Page<Hero> getHeroesByCompany(Pageable pageable, Long company_id);

}
