package com.omni.department.api.service.state.query;

import java.util.List;

import com.omni.department.api.model.StateEntity;

public interface StateQueryService {
	List<StateEntity> findAll();
}
