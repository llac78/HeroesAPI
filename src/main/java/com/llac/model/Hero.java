package com.llac.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "hero")
public class Hero implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToOne
    @JoinColumn(name = "file_id")
	private FileDB file;
	
	public Hero() {
	}

	public Hero(String name, Company company, FileDB file) {
		this.name = name;
		this.company = company;
		this.file = file;
		this.file.setHero(this);
	}

}
