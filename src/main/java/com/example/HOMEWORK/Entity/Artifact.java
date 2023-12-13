package com.example.HOMEWORK.Entity;

import com.example.HOMEWORK.Dto.ArtifactFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Artifact {

    @Id
    @Column(name="artifact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="found")
    private Travel travel;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="owner")
    private Customer customer;

    private String name;
    private LocalDate createdDate;
    private LocalDate discoveryDate;


    @OneToMany(mappedBy = "artifact" ,cascade = CascadeType.REMOVE)
    List<Appraisal> appraisalList = new ArrayList<>();

    // 이건 내가 구냥 추가했음
    private String imgUrl;
    private Long price;
    private String status;

    public void updateArtifact(ArtifactFormDto artifactFormDto) {
        this.id=artifactFormDto.getId();
        this.name=artifactFormDto.getName();
        this.createdDate=artifactFormDto.getCreatedDate();
        this.discoveryDate=artifactFormDto.getDiscoveryDate();
        this.imgUrl=artifactFormDto.getImgUrl();
        this.price=artifactFormDto.getPrice();
    }




}
