package com.demo.omni.dataprovider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.omni.dataprovider.entity.DepartmentEntity;

public interface DepartmentJpaRepository extends JpaRepository<DepartmentEntity, Integer> {
	Optional<DepartmentEntity> findByCode(Integer code);
}
