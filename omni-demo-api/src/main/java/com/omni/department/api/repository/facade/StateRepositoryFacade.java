package com.omni.department.api.repository.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.omni.department.api.domain.state.StateEntity;
import com.omni.department.api.repository.StateRepository;

public class StateRepositoryFacade {
	
	@Autowired
	StateRepository stateRepository;
	
	public List<StateEntity> findAllOrderByName(Integer id) {
		return stateRepository.findAll(Sort.by(Order.asc("name")));
	}
	
}
