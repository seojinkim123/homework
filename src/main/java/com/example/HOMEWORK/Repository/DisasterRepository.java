package com.example.HOMEWORK.Repository;

import com.example.HOMEWORK.Entity.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisasterRepository extends JpaRepository<Disaster,Long> {
}
