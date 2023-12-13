package com.example.HOMEWORK.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ReservationFormDto {

    LocalDate startDate;

    Long duration;

    String purpose;

    String destination;


}
