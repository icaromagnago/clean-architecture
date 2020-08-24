package com.omni.department.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omni.department.api.domain.state.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Integer> {

}
