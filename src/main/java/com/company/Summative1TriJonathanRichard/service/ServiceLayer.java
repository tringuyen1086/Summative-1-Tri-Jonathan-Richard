package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceLayer {

    private GameRepository gameRepository;

    private ConsoleRepository consoleRepository;

    private TShirtRepository tshirtRepository;

    private ProcessingFeeRepository processingFeeRepository;

    private SalesTaxRateRepository salesTaxRateRepository;

    private InvoiceRepository invoiceRepository;

    @Autowired
    public ServiceLayer(GameRepository gameRepository, ConsoleRepository consoleRepository,
                        TShirtRepository tshirtRepository, ProcessingFeeRepository processingFeeRepository, SalesTaxRateRepository salesTaxRateRepository, InvoiceRepository invoiceRepository) {
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tshirtRepository = tshirtRepository;
        this.processingFeeRepository = processingFeeRepository;
        this.salesTaxRateRepository = salesTaxRateRepository;
        this.invoiceRepository = invoiceRepository;
    }

// Game
    @Transactional
    public Game saveGame (Game model){
        return null;
    }

    public List<Game> findAllGames(){
        return null;
    }

    public Game findGameById (Integer id){
        return null;

    }

    public List<Game> findGameByStudio(String studio){
        return null;
    }

    public List<Game> findGameByEsrbRating(String esrbRating){
        return null;
    }

    public List<Game> findGameByTitle(String title){
        return null;
    }

    @Transactional
    public void updateGame(Game model){
        Game game = new Game();
        game.setId(model.getId());
        game.setTitle(model.getTitle());
        game.setEsrbRating(model.getEsrbRating());
        game.setDescription(model.getDescription());
        game.setPrice(model.getPrice());
        game.setStudio(model.getStudio());
        game.setQuantity(model.getQuantity());

        gameRepository.save(game);
    }

    @Transactional
    public void deleteGame(int id){
        gameRepository.deleteById(id);
    }

// Console
    public void saveConsole(Console console) {
        consoleRepository.saveConsole(console);
    }



// TShirt
public void deleteTShirtById(int id) {
    Optional<TShirt> deleteThis = tshirtRepository.findById(id);
    if(deleteThis.isPresent()) {
        tshirtRepository.deleteById(id);
    }else {
        throw new IllegalArgumentException("No matches for this Id");
    }
}

// Invoice

}
