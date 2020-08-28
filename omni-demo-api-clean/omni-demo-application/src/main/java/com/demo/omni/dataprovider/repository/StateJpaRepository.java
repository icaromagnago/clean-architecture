package com.demo.omni.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.omni.dataprovider.entity.StateEntity;

public interface StateJpaRepository extends JpaRepository<StateEntity, Integer> {

}
