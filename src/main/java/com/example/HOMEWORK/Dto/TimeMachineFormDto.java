package com.example.HOMEWORK.Dto;

import com.example.HOMEWORK.Entity.TimeMachine;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class TimeMachineFormDto {


    private String id;


    private LocalDate pastTripLimit;


    private LocalDate futureTripLimit;

    private String name;


    private String status;
    private String grade;

    private static ModelMapper modelMapper= new ModelMapper();


    public static TimeMachineFormDto createTimeMachineFormDto(TimeMachine timeMachine) {
        return modelMapper.map(timeMachine, TimeMachineFormDto.class);
    }

    public TimeMachine createTimeMachine() {
        return modelMapper.map(this, TimeMachine.class);
    }




}
