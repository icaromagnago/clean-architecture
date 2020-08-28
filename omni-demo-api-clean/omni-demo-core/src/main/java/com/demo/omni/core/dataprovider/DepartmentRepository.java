package com.demo.omni.core.dataprovider;

import java.util.List;

import com.demo.omni.core.entity.Department;

public interface DepartmentRepository {
	
	public Department save(Department department);
	
	public void delete(Integer id);
	
	public Department findById(Integer id);
	
	public List<Department> findAllOrderByCode();
}
