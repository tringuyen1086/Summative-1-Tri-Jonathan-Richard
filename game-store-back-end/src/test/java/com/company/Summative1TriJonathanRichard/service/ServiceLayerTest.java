package com.company.Summative1TriJonathanRichard.service;

import com.company.Summative1TriJonathanRichard.model.Console;
import com.company.Summative1TriJonathanRichard.model.Game;
import com.company.Summative1TriJonathanRichard.model.Invoice;
import com.company.Summative1TriJonathanRichard.model.TShirt;
import com.company.Summative1TriJonathanRichard.repository.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer serviceLayer;
    ConsoleRepository consoleRepository;
    GameRepository gameRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeeRepository processingFeeRepository;
    SalesTaxRateRepository salesTaxRateRepository;
    TShirtRepository tShirtRepository;
    private Game game1;
    private Game game2;
    private Console console1;
    private Console console2;
    private TShirt tShirt1;
    private TShirt tShirt2;
    private Invoice invoice1;
    private Invoice invoice2;
    private List<Invoice> invoiceList;
    private List<Game> gameList;
    private List<Console> consoleList;
    private List<TShirt> tShirtList;


    @Before
    public void setUp() throws Exception {
        setUpConsoleRepositoryMock();
        setUpGameRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpSalesTaxRepositoryMock();
        setUpProcessingFeeRepositoryMock();
        setUpTShirtRepositoryMock();
        serviceLayer = new ServiceLayer(gameRepository,consoleRepository,tShirtRepository,processingFeeRepository,salesTaxRateRepository,invoiceRepository);
    }

    public void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);
        console1 = new Console(1,"ps","sony","256gb","I5",300.99,10);
        console2 = new Console("ps","sony","256gb","I5",300.99,10);
        consoleList = new ArrayList<>();
        consoleList.add(console1);

        doReturn(console1).when(consoleRepository).save(console2);
        doReturn(Optional.of(console1)).when(consoleRepository).findById(1);
        doReturn(consoleList).when(consoleRepository).findAll();
        doReturn(consoleList).when(consoleRepository).findByManufacturer("sony");
    }

    public void setUpGameRepositoryMock() {
        gameRepository = mock(GameRepository.class);
        game1 = new Game(1,"game1","e","description",20.99,"studio",10);
        game2 = new Game("game1","e","description",20.99,"studio",10);
        gameList = new ArrayList<>();
        gameList.add(game1);

        doReturn(game1).when(gameRepository).save(game2);
        doReturn(Optional.of(game1)).when(gameRepository).findById(1);
        doReturn(gameList).when(gameRepository).findAll();
        doReturn(gameList).when(gameRepository).findByStudio("studio");
        doReturn(gameList).when(gameRepository).findByTitle("game1");
        doReturn(gameList).when(gameRepository).findByEsrbRating("e");
    }
    public void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);
        invoice1 = new Invoice("customer1","the street","thecity","DE","12345","game",1,10);
        invoice1.setId(1);
        invoice2 = new Invoice("customer1","the street","thecity","DE","12345","game",1,10);
        invoiceList = new ArrayList<>();
        invoiceList.add(invoice1);

        doReturn(invoice1).when(invoiceRepository).save(invoice2);
        doReturn(Optional.of(invoice1)).when(invoiceRepository).findById(1);
        doReturn(invoiceList).when(invoiceRepository).findAll();
        doReturn(invoiceList).when(invoiceRepository).findByName("customer1");
    }
    public void setUpProcessingFeeRepositoryMock() {
        processingFeeRepository = mock(ProcessingFeeRepository.class);
    }
    public void setUpSalesTaxRepositoryMock() {
        salesTaxRateRepository = mock(SalesTaxRateRepository.class);
    }
    public void setUpTShirtRepositoryMock() {
        tShirtRepository = mock(TShirtRepository.class);
        tShirt1 = new TShirt("large","blue","description",10.99,10);
        tShirt1.setId(1);
        tShirt2 = new TShirt("large","blue","description",10.99,10);
        tShirtList = new ArrayList<>();
        tShirtList.add(tShirt1);

        doReturn(tShirt1).when(tShirtRepository).save(tShirt2);
        doReturn(Optional.of(tShirt1)).when(tShirtRepository).findById(1);
        doReturn(tShirtList).when(tShirtRepository).findAll();
        doReturn(tShirtList).when(tShirtRepository).findBySize("large");
        doReturn(tShirtList).when(tShirtRepository).findByColor("blue");
    }
    @Test
    public void shouldSaveAConsole(){
        console2 = serviceLayer.saveConsole(console2);
        assertEquals(console1,console2);
    }
    @Test
    public void shouldFindConsoleById(){
        console2 = serviceLayer.findConsoleById(1).get();
        assertEquals(console1,console2);
    }
    @Test
    public void shouldFindAllConsole(){
        List<Console> console2 = serviceLayer.findAllConsoles();
        assertEquals(consoleList,console2);
    }
    @Test
    public void shouldFindConsoleByManufacture(){
        List<Console> console2 = serviceLayer.findConsoleByManufacturer("sony");
        assertEquals(consoleList,console2);
    }
    @Test
    public void shouldSaveAGame() {
        game2 = serviceLayer.saveGame(game2);
        assertEquals(game1,game2);
    }
    @Test
    public void shouldFindGameById(){
        game2 = serviceLayer.findGameById(1).get();
        assertEquals(game1,game2);
    }
    @Test
    public void shouldFindAllGames() {
        List<Game> game2 = serviceLayer.findAllGames();
        assertEquals(gameList,game2);
    }
    @Test
    public void shouldFindGameByStudio(){
        List<Game> game2 = serviceLayer.findGameByStudio("studio");
        assertEquals(gameList,game2);
    }
    @Test
    public void shouldFindGameByTitle() {
        List<Game> game2 = serviceLayer.findGameByTitle("game1");
        assertEquals(gameList,game2);
    }
    @Test
    public void shouldFindGameByRating() {
        List<Game> game2 = serviceLayer.findGameByEsrbRating("e");
        assertEquals(gameList,game2);
    }
    @Test
    public void shouldSaveInvoice(){
        invoice2 = serviceLayer.saveInvoice(invoice2);
        assertEquals(invoice1,invoice2);
    }
    @Test
    public void shouldFindInvoiceById() {
        invoice2 = serviceLayer.findInvoiceById(1);
        assertEquals(invoice1,invoice2);
    }
    @Test
    public void shouldFindAllInvoices() {
        List<Invoice> invoice2 = serviceLayer.findAllInvoices();
        assertEquals(invoiceList, invoice2);
    }
    @Test
    public void shouldFindByName() {
        List<Invoice> invoice2 = serviceLayer.findInvoiceByName("customer1");
        assertEquals(invoiceList, invoice2);
    }
    @Test
    public void saveTShirt() {
        tShirt2 = serviceLayer.tShirtCreate(tShirt2);
        assertEquals(tShirt1,tShirt2);
    }
    @Test
    public void shouldFindTShirtById(){
        tShirt2 = serviceLayer.tShirtById(1).get();
        assertEquals(tShirt1,tShirt2);
    }
    @Test
    public void shouldFindAllTShirts() {
        List<TShirt> tShirt2 = serviceLayer.tShirtGetAll();
        assertEquals(tShirtList,tShirt2);
    }
    @Test
    public void shouldFindTShirtBySize(){
        List<TShirt> tShirt2 = serviceLayer.tShirtBySize("large");
        assertEquals(tShirtList,tShirt2);
    }
    @Test
    public void shouldFindTShirtByColor(){
        List<TShirt> tShirt2 = serviceLayer.tShirtByColor("blue");
        assertEquals(tShirtList,tShirt2);
    }
}