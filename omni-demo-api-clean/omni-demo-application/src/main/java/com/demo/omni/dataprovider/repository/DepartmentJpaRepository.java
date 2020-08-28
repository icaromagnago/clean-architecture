package com.demo.omni.dataprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.omni.dataprovider.entity.DepartmentEntity;

public interface DepartmentJpaRepository extends JpaRepository<DepartmentEntity, Integer> {

}
