package com.example.demo.dbmodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "child_table")
public class ChildTable {
	
	@Id
	int id;
	int mainId;
	String address;

}
