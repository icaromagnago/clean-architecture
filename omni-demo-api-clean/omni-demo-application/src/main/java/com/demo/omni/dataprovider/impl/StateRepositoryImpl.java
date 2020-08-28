package com.demo.omni.dataprovider.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import com.demo.omni.core.dataprovider.StateRepository;
import com.demo.omni.core.entity.State;
import com.demo.omni.dataprovider.mapper.StateRepositoryMapper;
import com.demo.omni.dataprovider.repository.StateJpaRepository;

@Repository
public class StateRepositoryImpl implements StateRepository {
	
	private final StateJpaRepository stateJpaRepository;
	
	public StateRepositoryImpl(StateJpaRepository stateJpaRepository) {
		this.stateJpaRepository = stateJpaRepository;
	}

	@Override
	public List<State> getAllStates() {
		var states = stateJpaRepository.findAll(Sort.by(Order.asc("name")));
		
		return states.stream()
				.map(StateRepositoryMapper::toDomainEntity)
				.collect(toList());		
	}

}
