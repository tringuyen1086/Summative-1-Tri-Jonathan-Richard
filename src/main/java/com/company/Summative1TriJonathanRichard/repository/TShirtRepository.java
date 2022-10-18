package com.company.Summative1TriJonathanRichard.repository;

import com.company.Summative1TriJonathanRichard.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TShirtRepository extends JpaRepository<TShirt,Integer> {
    public List<TShirt> findByColor(String color);
    public List<TShirt> findBySize(String size);
}

