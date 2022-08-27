package com.example.demo.dbmodel;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "test_table")
public class TestTable {

	@Id
	Integer id;
	double amount;
	
//	@OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
//    private Collection<MainTable> mainTable = new HashSet<>(0);
}
