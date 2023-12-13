package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Artifact;
import com.example.HOMEWORK.Entity.Disaster;
import com.example.HOMEWORK.Repository.ArtifactRepository;
import com.example.HOMEWORK.Repository.DisasterRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DisasterService {

    private final DisasterRepository disasterRepository;

    public Long saveDisaster(Disaster disaster){
        Disaster savedDisaster=disasterRepository.save(disaster);
        return savedDisaster.getId();
    }

    public void deleteDisaster(Disaster Disaster) {
        disasterRepository.delete(Disaster);
    }

    public Disaster findByIdDisaster(Long id) {
        return disasterRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public List<Disaster> findAllDisaster() {
        return disasterRepository.findAll();
    }







}
