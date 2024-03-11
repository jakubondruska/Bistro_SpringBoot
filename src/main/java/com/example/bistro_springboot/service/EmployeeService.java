package com.example.bistro_springboot.service;

import com.example.bistro_springboot.model.Employee;
import com.example.bistro_springboot.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);

    }
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }
    public Optional<Employee> findEmployeeById(long id) {
        return employeeRepo.findById(id);

    }


}
