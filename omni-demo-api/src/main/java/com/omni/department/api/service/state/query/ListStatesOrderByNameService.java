package com.omni.department.api.service.state.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.omni.department.api.domain.state.ListStatesOrderByNameUseCase;
import com.omni.department.api.domain.state.StateEntity;
import com.omni.department.api.repository.StateRepository;

@Service
public class ListStatesOrderByNameService implements ListStatesOrderByNameUseCase {
	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<StateEntity> execute() {
		return stateRepository.findAll(Sort.by(Order.asc("name")));
	}
	
}
