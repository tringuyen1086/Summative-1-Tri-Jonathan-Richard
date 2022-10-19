package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.exception.NotFoundException;
import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.model.Invoice;
import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.*;
import net.bytebuddy.pool.TypePool;
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
    public Game saveGame(Game game) {
        game = gameRepository.save(game);
        return game;
    }

    public List<Game> findAllGames() {

        List<Game> gameList = gameRepository.findAll();
        return gameList;
    }

    public Game findGameById(int id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            return game.get();
        } else {
            throw new IllegalArgumentException("There is no match for this Game Id");
        }
    }

    public List<Game> findGameByStudio(String studio){
        return gameRepository.findByStudio(studio);
    }
    public List<Game> findGameByEsrbRating(String esrbRating){
        return gameRepository.findByEsrbRating(esrbRating);
    }

    public List<Game> findGameByTitle(String title){
        return gameRepository.findByTitle(title);
    }

    @Transactional
    public Game updateGame(Game game){
        Optional<Game> updateGame = gameRepository.findById(game.getId());
        if(updateGame.isPresent()) {
           return gameRepository.save(game);

        } else {
            throw new IllegalArgumentException("There is no match for this game.");
        }
    }


    @Transactional
    public void deleteGame(int id){
        gameRepository.deleteById(id);
    }

// Console
@Transactional
public Console saveConsole(Console console) {
    console = consoleRepository.save(console);
    return console;
}

    public List<Console> findAllConsoles() {

        List<Console> consoleList = consoleRepository.findAll();
        return consoleList;
    }
    public Console findConsoleById(int id) {
        Optional<Console> console = consoleRepository.findById(id);
        if (console.isPresent()) {
            return console.get();
        } else {
            throw new IllegalArgumentException("There is no match for this Game Id");
        }
    }
    public Optional<List<Console>> findConsoleByManufacturer(String manufacturer){
        Optional<List<Console>> foundConsole = consoleRepository.findByManufacturer(manufacturer);
        if(foundConsole.get().isEmpty()){
            throw new RuntimeException();
        } else {
            System.out.println(foundConsole);
            return consoleRepository.findByManufacturer(manufacturer);
        }

    }

    @Transactional
    public Console updateConsole(Console console){
        Optional<Console> updateConsole = consoleRepository.findById(console.getId());
        if(updateConsole.isPresent()) {
            return consoleRepository.save(console);

        } else {
            throw new IllegalArgumentException("There is no match for this game.");
        }
    }
    @Transactional
    public void deleteConsole(int id){
        consoleRepository.deleteById(id);
    }

// TShirt
    public List<TShirt> tShirtGetAll() {
        return tshirtRepository.findAll();
    }
    public Optional<TShirt> tShirtById(int id){
        return tshirtRepository.findById(id);
    }
    public List<TShirt> tShirtByColor(String color){
        return tshirtRepository.findByColor(color);
    }
    public List<TShirt> tShirtBySize(String size){
        return tshirtRepository.findBySize(size);
    }
    public TShirt tShirtCreate(TShirt tShirt){
        return tshirtRepository.save(tShirt);
    }
    public void deleteTShirtById(int id) {
        Optional<TShirt> deleteThis = tshirtRepository.findById(id);
        if(deleteThis.isPresent()) {
            tshirtRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("No matches for this Id");
        }
    }

// Invoice
    @Transactional
    public Invoice saveInvoice(Invoice invoice){
        invoice = invoiceRepository.save(invoice);
        if (invoice.getQuantity() == 0){
            throw new IllegalArgumentException("Your Quantity can");
        }
        return invoice;
    }

    public List<Invoice> findAllInvoices() {

        List<Invoice> invoiceList = invoiceRepository.findAll();
        return invoiceList;
    }

    public Invoice findInvoiceById(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            return invoice.get();
        } else {
            throw new IllegalArgumentException("There is no match for this Game Id");
        }
    }
    public TShirt updateTShirtById( TShirt model, int id){
        Optional<TShirt> updateThis = tshirtRepository.findById(id);
        if(updateThis.isPresent()) {
            model.setId(id);
            tshirtRepository.save(model);
        }else{
            throw new IllegalArgumentException("No matches for this Id.");
        }
        return null;
    }

// Invoice

}
