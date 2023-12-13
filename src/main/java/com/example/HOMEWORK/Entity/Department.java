package com.example.HOMEWORK.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {

    @Id
    @Column(name="department_name")
    private String name;


    private String location;


    private String description;

}
