package com.example.HOMEWORK.Repository;

import com.example.HOMEWORK.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,String> {
}
