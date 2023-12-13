package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Appraisal;
import com.example.HOMEWORK.Entity.Customer;
import com.example.HOMEWORK.Repository.AppraisalRepository;
import com.example.HOMEWORK.Repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppraisalService {
    private final AppraisalRepository appraisalRepository;

    public Long saveAppraisal(Appraisal Appraisal) {
        Appraisal savedAppraisal =appraisalRepository.save(Appraisal);
        return savedAppraisal.getId();
    }

    public void deleteAppraisal(Appraisal appraisal) {
        appraisalRepository.delete(appraisal);
    }

    public Appraisal findAppraisalById(Long id) {
        Appraisal foundAppraisal = appraisalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return foundAppraisal;

    }


    public List<Appraisal> findAllAppraisal() {
        List<Appraisal> appraisalList = appraisalRepository.findAll();
        return appraisalList;
    }

}
