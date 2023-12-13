package com.example.HOMEWORK.Repository;

import com.example.HOMEWORK.Entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent,String> {
}
