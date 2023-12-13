package com.example.HOMEWORK.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Continent {

    @Id
    @Column(name="continent_name")
    private String name;

    LocalDateTime beginDate;

    LocalDateTime endDate;



}
