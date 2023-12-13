package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Continent;
import com.example.HOMEWORK.Entity.Department;
import com.example.HOMEWORK.Repository.ContinentRepository;
import com.example.HOMEWORK.Repository.DepartmentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public String saveDepartment(Department department){
        Department savedDepartment=departmentRepository.save(department);
        return savedDepartment.getName();
    }

    public void deleteDepartment(Department department) {
        departmentRepository.delete(department);
    }

    public Department findByIdDepartment(String name) {
        return departmentRepository.findById(name).orElseThrow(EntityExistsException::new);
    }

    public List<Department> findAllDepartment() {
        return departmentRepository.findAll();
    }





}
