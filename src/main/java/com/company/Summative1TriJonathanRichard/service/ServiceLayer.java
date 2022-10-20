package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.exception.NotFoundException;
import com.company.Summative1TriJonathanRichard.model.*;
import com.company.Summative1TriJonathanRichard.repository.*;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
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

    public Optional<Game> findGameById(int id) {
        return gameRepository.findById(id);
    }

    public List<Game> findGameByStudio(String studio) {
        return gameRepository.findByStudio(studio);
    }

    public List<Game> findGameByEsrbRating(String esrbRating) {
        return gameRepository.findByEsrbRating(esrbRating);
    }

    public List<Game> findGameByTitle(String title) {
        return gameRepository.findByTitle(title);
    }

    @Transactional
    public Game updateGameById(Game game, int id) {
        Optional<Game> updateGame = gameRepository.findById(id);
        if (updateGame.isPresent()) {
            game.setId(id);
            gameRepository.save(game);
        } else {
            throw new IllegalArgumentException("There is no match for this Game Id.");
        }
        return null;
    }

    @Transactional
    public void deleteGame(int id) {
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

    public Optional<Console> findConsoleById(int id) {
        return consoleRepository.findById(id);
    }

    public List<Console> findConsoleByManufacturer(String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer);
    }

    @Transactional
    public Console updateConsoleById(Console console, int id) {
        Optional<Console> updateConsole = consoleRepository.findById(id);
        if (updateConsole.isPresent()) {
            console.setId(id);
            consoleRepository.save(console);
        } else {
            throw new IllegalArgumentException("There is no match for this Console Id.");
        }
        return null;
    }

    @Transactional
    public void deleteConsole(int id) {
        consoleRepository.deleteById(id);
    }

    // TShirt
    public List<TShirt> tShirtGetAll() {
        return tshirtRepository.findAll();
    }

    public Optional<TShirt> tShirtById(int id) {
        return tshirtRepository.findById(id);
    }

    public List<TShirt> tShirtByColor(String color) {
        return tshirtRepository.findByColor(color);
    }

    public List<TShirt> tShirtBySize(String size) {
        return tshirtRepository.findBySize(size);
    }

    public TShirt tShirtCreate(TShirt tShirt) {
        return tshirtRepository.save(tShirt);
    }

    public TShirt updateTShirtById(TShirt tShirt, int id) {
        Optional<TShirt> updateTShirt = tshirtRepository.findById(id);
        if (updateTShirt.isPresent()) {
            tShirt.setId(id);
            tshirtRepository.save(tShirt);
        } else {
            throw new IllegalArgumentException("No matches for this Id.");
        }
        return null;
    }

    public void deleteTShirtById(int id) {
        Optional<TShirt> deleteThis = tshirtRepository.findById(id);
        if (deleteThis.isPresent()) {
            tshirtRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No matches for this Id");
        }
    }

    // Invoice

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        SalesTaxRate newRate = new SalesTaxRate(invoice.getState());
        ProcessingFee newProcessingFee = new ProcessingFee(invoice.getItemType());

        switch (invoice.getItemType()){
            case "game": {
                Game fee = gameRepository.findById(invoice.getItemId()).get();
                //need to set the invoice's item type to calculate quantity correctly
                invoice.setUnitPrice(fee.getPrice());
                invoice.setSubtotal(invoice.getQuantity() * invoice.getUnitPrice());
                invoice.setTax(invoice.getSubtotal() * newRate.getRate());

                if (invoice.getQuantity() > 10){
                    invoice.setProcessingFee(15.49 + newProcessingFee.getFee());
                } else {
                    invoice.setProcessingFee(newProcessingFee.getFee());
                }

                invoice.setTotal(invoice.getSubtotal() + invoice.getTax() + invoice.getProcessingFee());
                return invoiceRepository.save(invoice);


                //
            } case "console":{
                Console fee = consoleRepository.findById(invoice.getItemId()).get();
                //need to set the invoice's item type to calculate quantity correctly
                invoice.setUnitPrice(fee.getPrice());
                invoice.setSubtotal(invoice.getQuantity() * invoice.getUnitPrice());
                invoice.setTax(invoice.getSubtotal() * newRate.getRate());

                if (invoice.getQuantity() > 10){
                    invoice.setProcessingFee(15.49 + newProcessingFee.getFee());
                } else {
                    invoice.setProcessingFee(newProcessingFee.getFee());
                }

                invoice.setTotal(invoice.getSubtotal() + invoice.getTax() + invoice.getProcessingFee());
                return invoiceRepository.save(invoice);

            } case "tshirt":{
                TShirt fee = tshirtRepository.findById(invoice.getItemId()).get();
                //need to set the invoice's item type to calculate quantity correctly
                invoice.setUnitPrice(fee.getPrice());
                invoice.setSubtotal(invoice.getQuantity() * invoice.getUnitPrice());
                invoice.setTax(invoice.getSubtotal() * newRate.getRate());

                if (invoice.getQuantity() > 10){
                    invoice.setProcessingFee(15.49 + newProcessingFee.getFee());
                } else {
                    invoice.setProcessingFee(newProcessingFee.getFee());
                }

                invoice.setTotal(invoice.getSubtotal() + invoice.getTax() + invoice.getProcessingFee());
                return invoiceRepository.save(invoice);

            }
            default:
                return null;
        }
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
            throw new IllegalArgumentException("There is no match for this Invoice Id");
        }
    }

    public List<Invoice> findInvoiceByName(String name){
        return invoiceRepository.findByName(name);
    }


}
