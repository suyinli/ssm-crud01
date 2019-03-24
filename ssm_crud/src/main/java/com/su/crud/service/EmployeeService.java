package com.su.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.su.crud.dao.EmployeeMapper;
import com.su.crud.entity.Employee;

@Service("EmployeeService")
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	public List<Employee> getAll(){
		return employeeMapper.selectByExampleWithDept(null);
		
	}
}
