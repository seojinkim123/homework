package com.example.HOMEWORK.Dto;

import com.example.HOMEWORK.Entity.Artifact;
import com.example.HOMEWORK.Entity.Customer;
import com.example.HOMEWORK.Entity.Travel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class ArtifactFormDto {


    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="found")
//    private Travel travel;
//
//    @ManyToOne(fetch =FetchType.LAZY)
//    @JoinColumn(name="owner")
//    private Customer customer;

    private String name;
    private LocalDate createdDate;
    private LocalDate discoveryDate;

    private String imgUrl; // 이건 내가 구냥 추가했음
    private static ModelMapper modelMapper = new ModelMapper();

    private Long price;
    public Artifact createArtifact() {
        return modelMapper.map(this, Artifact.class);
    }

    public static ArtifactFormDto createArtifactDto(Artifact artifact) {

        return modelMapper.map(artifact, ArtifactFormDto.class);

    }

}
