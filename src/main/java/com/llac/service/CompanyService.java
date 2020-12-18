package com.llac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.llac.model.Company;
import com.llac.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	public Company insert(Company company) {
		return this.companyRepository.save(company);
	}

	public Page<Company> getAllCompanies(Pageable pageable) {
		return companyRepository.findAll(pageable);
	}

}
