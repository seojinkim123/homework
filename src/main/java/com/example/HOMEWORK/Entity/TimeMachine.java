package com.example.HOMEWORK.Entity;

import com.example.HOMEWORK.Dto.TimeMachineFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TimeMachine {

    @Id
    @Column(name="time_machine_id")
    private String id;

    @Column(name="past_trip_limit")
    private LocalDate pastTripLimit;

    @Column(name="future_trip_limit")
    private LocalDate futureTripLimit;

    @Column(name="model_name")
    private String name;


    private String status;

    private String grade;

    public void updateTimeMachine(TimeMachineFormDto timeMachineFormDto) {
//        this.id= timeMachineFormDto.getId();
        this.pastTripLimit= timeMachineFormDto.getPastTripLimit();
        this.futureTripLimit= timeMachineFormDto.getFutureTripLimit();
        this.name= timeMachineFormDto.getName();
        this.status= timeMachineFormDto.getStatus();
        this.grade= timeMachineFormDto.getGrade();
    }





}
