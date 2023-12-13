package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.EmployeeRole;
import com.example.HOMEWORK.Entity.Employee;
import com.example.HOMEWORK.Repository.EmployeeRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService  {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    public String saveEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Employee savedEmployee =employeeRepository.save(employee);
        return savedEmployee.getId();
    }

    public Employee findEmployeeById(String id){

        Employee foundEmployee=employeeRepository.findById(id).orElseThrow(EntityExistsException::new);

        return foundEmployee;
    }

    public List<Employee> findEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }



}



















