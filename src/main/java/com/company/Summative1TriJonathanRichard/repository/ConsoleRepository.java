package com.company.Summative1TriJonathanRichard.repository;

import com.company.Summative1TriJonathanRichard.model.Console;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository {
    void saveConsole(Console console);
}
