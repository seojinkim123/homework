package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Reservation;
import com.example.HOMEWORK.Entity.TimeMachine;
import com.example.HOMEWORK.Repository.ReservationRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public Long saveReservation(Reservation reservation) {
        Reservation savedReservation=reservationRepository.save(reservation);
        return savedReservation.getId();

    }

    public void deleteReservation(Reservation Reservation) {

        reservationRepository.delete(Reservation);
    }

    public Reservation findByIdReservation(Long id) {
        Reservation foundReservation = reservationRepository.findById(id).orElseThrow(EntityExistsException::new);
        return foundReservation;
    }

    public List<Reservation> findAllReservation() {
        return reservationRepository.findAll();
    }





}
