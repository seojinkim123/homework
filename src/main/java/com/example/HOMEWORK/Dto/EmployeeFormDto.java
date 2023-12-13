package com.example.HOMEWORK.Dto;

import com.example.HOMEWORK.Entity.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;


@Getter
@Setter
public class EmployeeFormDto {

    @NotBlank(message="아이디는 필수입니다.")
    @Email
    private String id;

    @NotBlank(message="비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message="이름은 필수입니다.")
    private String name;


    private LocalDate birthDate;

    private LocalDate deathDate;

    private String status;

    private String role;

    private static ModelMapper modelMapper = new ModelMapper();

    public Employee createEmployee() {

        return modelMapper.map(this, Employee.class);
    }


    public static EmployeeFormDto createEmployeeFormDto(Employee employee) {
        return modelMapper.map(employee, EmployeeFormDto.class);
    }

}
