package com.example.demo.h2repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.h2dbmodel.H2Table;

public interface H2Repo extends JpaRepository<H2Table, Integer> {

}
