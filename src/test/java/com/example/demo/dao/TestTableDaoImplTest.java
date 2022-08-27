package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.repo.TestTableRepo;

@SpringBootTest
class TestTableDaoImplTest {

	@Autowired
	TestTableDao testTableDao;
	
	@Autowired
	TestTableRepo repo;
	
	@Test
	void test() {
		System.out.println(testTableDao.getAllData(5));
	}

}
