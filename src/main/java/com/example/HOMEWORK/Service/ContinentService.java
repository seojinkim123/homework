package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Artifact;
import com.example.HOMEWORK.Entity.Continent;
import com.example.HOMEWORK.Repository.ArtifactRepository;
import com.example.HOMEWORK.Repository.ContinentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ContinentService {


    private final ContinentRepository continentRepository;

    public String saveContinent(Continent continent){
        Continent savedContinent=continentRepository.save(continent);
        return savedContinent.getName();
    }

    public void deleteContinent(Continent continent) {
        continentRepository.delete(continent);
    }

    public Continent findByIdcontinent(String name) {
        return continentRepository.findById(name).orElseThrow(EntityExistsException::new);
    }

    public List<Continent> findAllContinent() {
        return continentRepository.findAll();
    }








}
