package com.omni.department.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omni.department.api.domain.department.DepartmentEntity;

public interface DepartmentRepository extends  JpaRepository<DepartmentEntity, Integer> {

}
