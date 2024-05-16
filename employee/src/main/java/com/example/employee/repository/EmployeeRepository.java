package com.example.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT fullname FROM Employee WHERE salary = (SELECT MAX(salary) FROM Employee)", nativeQuery = true)
    String findEmployeeNameWithHighestSalary();
    
   

}