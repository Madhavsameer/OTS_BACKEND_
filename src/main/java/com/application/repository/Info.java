package com.application.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.application.model.Data;

public interface Info extends CrudRepository<Data,Integer>{
	

}