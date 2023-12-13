package com.example.HOMEWORK.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Travel {

    @Id
    @Column(name="travel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ticket")
    private Reservation reservation;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

}
