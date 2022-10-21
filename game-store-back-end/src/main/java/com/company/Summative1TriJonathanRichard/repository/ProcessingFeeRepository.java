package com.company.Summative1TriJonathanRichard.repository;

import com.company.Summative1TriJonathanRichard.model.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFee, String> {
}
