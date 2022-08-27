package com.example.demo.h2dbmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "h2_table")
public class H2Table {

	@Id
	int id;
	String name;
	String address;
}
