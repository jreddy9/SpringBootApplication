package com.example.employee.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;


@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFullname(employee.getFullname());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setDob(employee.getDob());
            return employeeRepository.save(existingEmployee);
        } else {
            return null;
        }
    }

    public List<Employee> filterEmployeesBySalary(BigDecimal minSalary) {
        List<Employee> allEmployees = employeeRepository.findAll();
        return allEmployees.stream()
                           .filter(employee -> employee.getSalary().compareTo(minSalary) >= 0)
                           .collect(Collectors.toList());
    }
}