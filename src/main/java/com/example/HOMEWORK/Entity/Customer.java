package com.example.HOMEWORK.Entity;

import com.example.HOMEWORK.Dto.CustomerFormDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @Column(name="customer_id")
    private String id;

    private String password;

    private String name;

    private String grade;

    private LocalDateTime registrationDate;

    public void updateCustomer(CustomerFormDto customerFormDto) {
        this.id=customerFormDto.getId();
        this.password=customerFormDto.getPassword();
        this.name=customerFormDto.getName();
        this.grade=customerFormDto.getGrade();
        this.registrationDate=customerFormDto.getRegistrationDate();


    }


}
