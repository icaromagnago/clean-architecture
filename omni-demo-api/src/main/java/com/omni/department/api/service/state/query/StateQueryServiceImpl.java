package com.omni.department.api.service.state.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.omni.department.api.model.StateEntity;
import com.omni.department.api.repository.StateRepository;

@Service
public class StateQueryServiceImpl implements StateQueryService {
	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<StateEntity> findAll() {
		return stateRepository.findAll(Sort.by(Order.asc("name")));
	}
	
}
