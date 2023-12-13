package com.example.HOMEWORK.Repository;

import com.example.HOMEWORK.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<Employee,String> {



}
