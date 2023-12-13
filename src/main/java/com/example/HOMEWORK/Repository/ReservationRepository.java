package com.example.HOMEWORK.Repository;

import com.example.HOMEWORK.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
