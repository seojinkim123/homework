package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.TimeMachine;
import com.example.HOMEWORK.Entity.Travel;
import com.example.HOMEWORK.Repository.TimeMachineRepository;
import com.example.HOMEWORK.Repository.TravelRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelService {
    private final TravelRepository travelRepository;

    public Long saveTravel(Travel Travel) {
        Travel savedTravel=travelRepository.save(Travel);
        return savedTravel.getId();

    }

    public void deleteTravel(Travel Travel) {

        travelRepository.delete(Travel);
    }

    public Travel findByIdTravel(Long id) {
        Travel foundTravel = travelRepository.findById(id).orElseThrow(EntityExistsException::new);
        return foundTravel;
    }

    public List<Travel> findAllTravel() {
        return travelRepository.findAll();
    }





}
