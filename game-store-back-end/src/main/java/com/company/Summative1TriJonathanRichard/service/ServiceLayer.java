package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.exception.LowInventoryException;
import com.company.Summative1TriJonathanRichard.exception.NotFoundException;
import com.company.Summative1TriJonathanRichard.model.*;
import com.company.Summative1TriJonathanRichard.repository.*;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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

    public static int count = 0;
    public static String[] listOfStates ={"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
    public static String[] listOfProductTypes = {"console","tshirt","game"};


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
        if(gameRepository.findById(id).isPresent()){
            return gameRepository.findById(id);
        }else {
            throw new NotFoundException("unable to find this Game");
        }
    }

    public List<Game> findGameByStudio(String studio) {
        if(gameRepository.findByStudio(studio).size() >= 1){
            return gameRepository.findByStudio(studio);
        }else {
            throw new NotFoundException("unable to find this Game");
        }
    }

    public List<Game> findGameByEsrbRating(String esrbRating) {
        if(gameRepository.findByEsrbRating(esrbRating).size() >= 1){
            return gameRepository.findByEsrbRating(esrbRating);
        }else {
            throw new NotFoundException("unable to find this Game");
        }
    }

    public List<Game> findGameByTitle(String title) {
        if(gameRepository.findByTitle(title).size() >= 1){
            return gameRepository.findByTitle(title);
        }else {
            throw new NotFoundException("unable to find this Game");
        }
    }

    @Transactional
    public Game updateGameById(Game game, int id) {
        Optional<Game> updateGame = gameRepository.findById(id);
        if (updateGame.isPresent()) {
            game.setId(id);
            gameRepository.save(game);
        } else {
            throw new NotFoundException("There is no match for this Game Id.");
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
        if(consoleRepository.findById(id).isPresent()){
            return consoleRepository.findById(id);
        }else {
            throw new NotFoundException("unable to find this Console");
        }
    }

    public List<Console> findConsoleByManufacturer(String manufacturer) {
        if(consoleRepository.findByManufacturer(manufacturer).size() >= 1){
            return consoleRepository.findByManufacturer(manufacturer);
        }else {
            throw new NotFoundException("unable to find this Console");
        }    }

    @Transactional
    public Console updateConsoleById(Console console, int id) {
        Optional<Console> updateConsole = consoleRepository.findById(id);
        if (updateConsole.isPresent()) {
            console.setId(id);
            consoleRepository.save(console);
        } else {
            throw new NotFoundException("There is no match for this Console Id.");
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
            if(tshirtRepository.findById(id).isPresent()){
             return tshirtRepository.findById(id);
            }else {
                throw new NotFoundException("unable to find this T-Shirt");
            }
    }

    public List<TShirt> tShirtByColor(String color) {
        if(tshirtRepository.findByColor(color).size() >= 1){
            return tshirtRepository.findByColor(color);
        }else {
            throw new NotFoundException("unable to find this T-Shirt");
        }
}

    public List<TShirt> tShirtBySize(String size) {
        if(tshirtRepository.findBySize(size).size() >= 1){
            return tshirtRepository.findBySize(size);
        }else {
            throw new NotFoundException("unable to find this T-Shirt");
        }    }

    public TShirt tShirtCreate(TShirt tShirt) {
        return tshirtRepository.save(tShirt);
    }

    public TShirt updateTShirtById(TShirt tShirt, int id) {
        Optional<TShirt> updateTShirt = tshirtRepository.findById(id);
        if (updateTShirt.isPresent()) {
            tShirt.setId(id);
            tshirtRepository.save(tShirt);
        } else {
            throw new NotFoundException("No matches for this Id.");
        }
        return null;
    }

    public void deleteTShirtById(int id) {
        Optional<TShirt> deleteThis = tshirtRepository.findById(id);
        if (deleteThis.isPresent()) {
            tshirtRepository.deleteById(id);
        } else {
            throw new NotFoundException("No matches for this Id");
        }
    }

    // Invoice

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        System.out.println(count);
        if(count==0){
            Arrays.stream(listOfStates).forEach(state->{
                SalesTaxRate createThis = new SalesTaxRate(state);
                salesTaxRateRepository.save(createThis);
            });
            Arrays.stream(listOfProductTypes).forEach(type->{
                ProcessingFee createThis = new ProcessingFee(type);
                processingFeeRepository.save(createThis);
            });
            count++;
        }
        SalesTaxRate newRate = new SalesTaxRate(invoice.getState());
        ProcessingFee newProcessingFee = new ProcessingFee(invoice.getItemType());

        switch (invoice.getItemType()){
            case "game": {
                Game fee = gameRepository.findById(invoice.getItemId()).get();
                if(fee.getQuantity()>=invoice.getQuantity()) {
                    fee.setQuantity(fee.getQuantity() - invoice.getQuantity());
                    gameRepository.save(fee);
                }else{
                    throw new LowInventoryException("inventory low, please check stock");
                }
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
                if(fee.getQuantity()>=invoice.getQuantity()) {
                    fee.setQuantity(fee.getQuantity() - invoice.getQuantity());
                    consoleRepository.save(fee);
                }else{
                    throw new LowInventoryException("inventory low, please check stock");
                }
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
                if(fee.getQuantity()>=invoice.getQuantity()) {
                    fee.setQuantity(fee.getQuantity() - invoice.getQuantity());
                    tshirtRepository.save(fee);
                }else{
                    throw new LowInventoryException("inventory low, please check stock");
                }
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
            throw new NotFoundException("There is no match for this Invoice Id");
        }
    }

    public List<Invoice> findInvoiceByName(String name){
        if(invoiceRepository.findByName(name).size() >= 1){
            return invoiceRepository.findByName(name);
        }else {
            throw new NotFoundException("unable to find a match for this name!");
        }    }


}
