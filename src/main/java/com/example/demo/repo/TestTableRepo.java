package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dbmodel.TestTable;

@Repository
public interface TestTableRepo extends JpaRepository<TestTable, Integer> {

	
}
