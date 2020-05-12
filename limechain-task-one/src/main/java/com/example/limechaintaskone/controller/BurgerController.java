package com.example.limechaintaskone.controller;

import com.example.limechaintaskone.model.Burger;
import com.example.limechaintaskone.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/burgers")
public class BurgerController {
    private BurgerService burgerService;

    @Autowired
    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addBurger(@RequestParam String name){
        Optional<Burger> burger = this.burgerService.addBurger(name);

        if(burger.isPresent()){
            return new ResponseEntity<>("Successfully created a burger with a name " + name, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not create a burger with a name " + name, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllBurgers(){
        List<Burger> burgers = this.burgerService.getBurgers();
        if(burgers.size() < 1){
            return new ResponseEntity<>("Could not find any burgers.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.burgerService.getBurgers(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneBurger(@PathVariable int id){
        Optional<Burger> burger = this.burgerService.getBurger(id);

        if(burger.isPresent()){
            return new ResponseEntity<>(burger.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find a burger with an id of " + id, HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public ResponseEntity getOneBurger(@PathVariable String name){
        Optional<Burger> burger = this.burgerService.getBurger(name);

        if(burger.isPresent()){
            return new ResponseEntity<>(burger.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find a burger with a name of " + name, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public ResponseEntity getRandomBurger(){
        Optional<Burger> burger = this.burgerService.getRandom();

        if(burger.isPresent()){
            return new ResponseEntity<>(burger.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get a random burger. Try again later.", HttpStatus.BAD_REQUEST);
    }
}
