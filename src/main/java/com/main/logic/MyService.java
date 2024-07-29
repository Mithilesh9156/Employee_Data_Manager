package com.main.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Employee;
import com.main.repository.MyRepository;

@Service
public class MyService {

	@Autowired
	MyRepository myRepo;
	
	public Employee saveEmp(Employee emp) {
		return myRepo.save(emp);
	}
	
	public void deleteEmpById(int id) {
		myRepo.deleteById(id);
	}
	
	public List<Employee> findAllEmployee(){
		List<Employee> list = myRepo.findAll();
		return list;
	}
	
	public Optional<Employee> findById(int id) {
				
		return myRepo.findById(id);
	}
	

	
	
}
