package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.TimeMachine;
import com.example.HOMEWORK.Repository.TimeMachineRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeMachineService {
    private final TimeMachineRepository timeMachineRepository;

    public String saveTimeMachine(TimeMachine timeMachine) {
        TimeMachine savedTimeMachine=timeMachineRepository.save(timeMachine);
        return savedTimeMachine.getId();

    }

    public void deleteTimeMachine(TimeMachine timeMachine) {

        timeMachineRepository.delete(timeMachine);
    }

    public TimeMachine findByIdTimeMachine(String id) {
        TimeMachine foundTimeMachine = timeMachineRepository.findById(id).orElseThrow(EntityExistsException::new);
        return foundTimeMachine;
    }

    public List<TimeMachine> findAllTimeMachine() {
        return timeMachineRepository.findAll();
    }




}
