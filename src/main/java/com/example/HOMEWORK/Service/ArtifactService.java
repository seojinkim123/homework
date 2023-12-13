package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Artifact;
import com.example.HOMEWORK.Repository.ArtifactRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtifactService {

    private final ArtifactRepository artifactRepository;

    public Long saveArtifact(Artifact artifact){
        Artifact  savedArtifact=artifactRepository.save(artifact);
        return savedArtifact.getId();
    }

    public void deleteArtifact(Artifact artifact) {
        artifactRepository.delete(artifact);
    }

    public Artifact findByIdArtifact(Long id) {
        return artifactRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public List<Artifact> findAllArtifact() {
        return artifactRepository.findAll();
    }
}
