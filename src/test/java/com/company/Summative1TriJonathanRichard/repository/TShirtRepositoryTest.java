package com.company.Summative1TriJonathanRichard.repository;

import com.company.Summative1TriJonathanRichard.model.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtRepositoryTest {

    @Autowired
    TShirtRepository tShirtRepository;

    @Before
    public void setUp() throws Exception{
        tShirtRepository.deleteAll();
    }

    @Test
    public void shouldCreateTShirtUsingTShirtRepository(){
        TShirt tShirt = new TShirt("small","blue","sample description",10.88,50);
        tShirt = tShirtRepository.save(tShirt);
        Optional<TShirt> tShirt1 = tShirtRepository.findById(tShirt.getId());
        assertEquals(tShirt1.get(),tShirt);
    }

    @Test
    public void shouldDeleteTShirtUsingTShirtRepository() {
        TShirt tShirt = new TShirt("small","blue","sample description",10.88,50);
        tShirt = tShirtRepository.save(tShirt);
        Optional<TShirt> tShirt1 = tShirtRepository.findById(tShirt.getId());
        assertEquals(tShirt1.get(),tShirt);
        tShirtRepository.deleteById(tShirt.getId());
        tShirt1 =tShirtRepository.findById(tShirt.getId());
        assertFalse(tShirt1.isPresent());
    }

    @Test
    public void getAllTShirts() {
        TShirt tShirt = new TShirt("small","blue","sample description",10.88,50);
        tShirt = tShirtRepository.save(tShirt);
        List<TShirt> tShirtList = tShirtRepository.findAll();
        assertEquals(tShirtList.size(),1);
        tShirt = new TShirt("medium","red","sample description",10.88,50);
        tShirt = tShirtRepository.save(tShirt);
        tShirtList = tShirtRepository.findAll();
        assertEquals(tShirtList.size(),2);
    }

    @Test
    public void updateTShirt(){
        TShirt tShirt = new TShirt("small","blue","sample description",10.88,50);
        tShirt = tShirtRepository.save(tShirt);

        tShirt.setColor("red");
        tShirt.setSize("large");
        tShirtRepository.save(tShirt);

        Optional<TShirt> tShirt1 = tShirtRepository.findById(tShirt.getId());
        assertEquals(tShirt1.get(),tShirt);
    }

    @Test
    public void getTShirtBySize(){
        TShirt tShirt1 = new TShirt("small","blue","sample description",10.88,50);
        TShirt tShirt2 = new TShirt("small","blue","sample description",10.88,50);
        TShirt tShirt3 = new TShirt("medium","blue","sample description",10.88,50);
        TShirt tShirt4 = new TShirt("medium","blue","sample description",10.88,50);
        tShirtRepository.save(tShirt1);
        tShirtRepository.save(tShirt2);
        tShirtRepository.save(tShirt3);
        tShirtRepository.save(tShirt4);
        List<TShirt> smallShirts = tShirtRepository.findBySize("small");
        List<TShirt> mediumShirts = tShirtRepository.findBySize("medium");
        assertEquals(smallShirts.size(),2);
        assertEquals(mediumShirts.size(),2);
    }

    @Test
    public void getTShirtByColor(){
        TShirt tShirt1 = new TShirt("small","blue","sample description",10.88,50);
        TShirt tShirt2 = new TShirt("small","blue","sample description",10.88,50);
        TShirt tShirt3 = new TShirt("medium","red","sample description",10.88,50);
        TShirt tShirt4 = new TShirt("small","red","sample description",10.88,50);
        tShirtRepository.save(tShirt1);
        tShirtRepository.save(tShirt2);
        tShirtRepository.save(tShirt3);
        tShirtRepository.save(tShirt4);
        List<TShirt> redShirts = tShirtRepository.findByColor("red");
        List<TShirt> blueShirts = tShirtRepository.findByColor("blue");
        assertEquals(redShirts.size(),2);
        assertEquals(blueShirts.size(),2);
    }
}