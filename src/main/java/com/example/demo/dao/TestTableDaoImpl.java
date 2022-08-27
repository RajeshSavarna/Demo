package com.example.demo.dao;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dbmodel.MainTable;
import com.example.demo.dbmodel.TestTable;
import com.example.demo.repo.TestTableRepo;

@Component
public class TestTableDaoImpl implements TestTableDao {

	private TestTableRepo testTableRepo;

	@Autowired
	public TestTableDaoImpl (TestTableRepo testTableRepo) {
		this.testTableRepo = testTableRepo;
	}

	@Override
	public TestTable getAllData(int id) {
		TestTable t = new TestTable();
		t.setId(id);
		t.setAmount(123.0);

//		MainTable mt = new MainTable();
//		mt.setId(6);
//		mt.setTestId(t);
//		mt.setName("hello6");
//
//		Collection<MainTable> mainTable = new HashSet<>();
//		mainTable.add(mt);
//		t.setMainTable(mainTable);

		return testTableRepo.save(t);
	}

}
