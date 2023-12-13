package com.example.HOMEWORK.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Disaster {

    @Id
    @Column(name="disaster_id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="location")
    private Continent continent;

    private String name;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Long severity;

}
