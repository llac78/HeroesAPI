package com.llac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.llac.model.Company;
import com.llac.service.CompanyService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/heroes/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Company insert(@RequestBody Company company) {
		return this.companyService.insert(company);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<Company>> getAllCompanies(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) 
			Pageable pageable) {
		Page<Company> companyPage = companyService.getAllCompanies(pageable);
		if (companyPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Company>>(companyPage, HttpStatus.OK);
		}
	}

}
