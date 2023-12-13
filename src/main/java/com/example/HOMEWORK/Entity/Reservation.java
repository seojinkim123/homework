package com.example.HOMEWORK.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @Column(name="reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher")
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approver")
    Employee employee2;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="reservation")
    TimeMachine timeMachine;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="destination")
    Continent continent;

    private LocalDateTime createTime;
    private LocalDateTime destinationTime;
    private LocalDate startTime;
    private LocalDate endTime;
    private String purpose;

    private String status;


}
