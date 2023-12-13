package com.example.HOMEWORK.Dto;

import com.example.HOMEWORK.Entity.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerFormDto {

    @Email
    @NotBlank(message="아이디는 필수입력 사항입니다.")
    private String id;
    @NotBlank(message="비밀번호는 필수입력 사항입니다.")
    private String password;


    private String name;

    private String grade;

    private LocalDateTime registrationDate;



    private static  ModelMapper modelMapper = new ModelMapper();

    public Customer createCustomer() {
        return modelMapper.map(this, Customer.class);
    }

    public static CustomerFormDto createCustomerFormDto(Customer customer) {
        return modelMapper.map(customer, CustomerFormDto.class);
    }

}
