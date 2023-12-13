package com.example.HOMEWORK.Entity;


import com.example.HOMEWORK.Dto.EmployeeFormDto;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @Column(name="employee_id")
    private String id;


    @JoinColumn(name="part")
    @ManyToOne(fetch= FetchType.LAZY)
    private Department department;


    private String password;

    private String name;


    private LocalDate birthDate;

    private LocalDate deathDate;

    private String status;

    private String role;

    public void updateEmployee(EmployeeFormDto employeeFormDto) {
        this.id=employeeFormDto.getId();
        this.password =employeeFormDto.getPassword();
        this.name=employeeFormDto.getName();
        this.birthDate = employeeFormDto.getBirthDate();
        this.deathDate = employeeFormDto.getDeathDate();
        this.status = employeeFormDto.getStatus();
        this.role =employeeFormDto.getRole();
    }


}
