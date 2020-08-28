package com.demo.omni.dataprovider.mapper;

import org.springframework.beans.BeanUtils;

import com.demo.omni.core.entity.State;
import com.demo.omni.dataprovider.entity.StateEntity;

public class StateRepositoryMapper {
	
	public static StateEntity toDatabaseEntity(final State state) {
		var stateEntity = new StateEntity();
		BeanUtils.copyProperties(state, stateEntity);
		
		return stateEntity;
	}
	
	public static State toDomainEntity(final StateEntity stateEntity) {
		var state = new State();
		BeanUtils.copyProperties(stateEntity, state);
		
		return state;
	}

}
