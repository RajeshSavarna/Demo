package com.example.demo.dbmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "main_table")
public class MainTable {
	
	@Id
	Integer id;
	String name;
	
	@ManyToOne
	@JoinColumn(name = "testId")
	TestTable testId;

}
