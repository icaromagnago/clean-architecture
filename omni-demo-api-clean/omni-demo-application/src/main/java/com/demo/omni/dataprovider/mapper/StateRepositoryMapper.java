package com.demo.omni.dataprovider.mapper;

import com.demo.omni.core.entity.State;
import com.demo.omni.dataprovider.entity.StateEntity;

public class StateRepositoryMapper {
	
	public static StateEntity toDatabaseEntity(final State state) {
		return StateEntity.builder()
				.id(state.getId())
				.name(state.getName())
				.uf(state.getUf())
				.build();
	}
	
	public static State toDomainEntity(final StateEntity stateEntity) {
		return State.builder()
				.id(stateEntity.getId())
				.name(stateEntity.getName())
				.uf(stateEntity.getUf())
				.build();
	}

}
