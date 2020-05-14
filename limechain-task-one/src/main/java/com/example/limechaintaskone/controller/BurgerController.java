package com.example.limechaintaskone.controller;

import com.example.limechaintaskone.dto.BurgerDTO;
import com.example.limechaintaskone.model.Burger;
import com.example.limechaintaskone.service.BurgerService;
import org.hibernate.annotations.Type;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/burgers")
public class BurgerController {
    private ModelMapper modelMapper;
    private BurgerService burgerService;

    @Autowired
    public BurgerController(ModelMapper modelMapper, BurgerService burgerService) {
        this.modelMapper = modelMapper;
        this.burgerService = burgerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addBurger(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String imageUrl, @RequestParam(required = false, defaultValue = "") String ingredients){
        Optional<BurgerDTO> burger = this.burgerService.addBurger(name, imageUrl, ingredients.split("[, ]+"));

        if(burger.isPresent()){
            return new ResponseEntity<>("Successfully created a burger with a name " + name, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not create a burger with a name " + name, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllBurgers(@RequestParam(required = false) String name,
                                        @RequestParam(required = false, defaultValue = "0") int page,
                                        @RequestParam(required = false, defaultValue = "25") int size,
                                        @RequestParam(required = false, defaultValue = "id") String sort,
                                        Pageable pageable,
                                        PagedResourcesAssembler assembler)
    {
        if(name!=null && !name.equals("")) return getOneBurger(name);

        List<BurgerDTO> burgers = this.burgerService.getBurgers(page,size,sort);

        if(burgers.size() < 1){
            return new ResponseEntity<>("Could not find any burgers.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(burgers, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getOneBurger(@PathVariable("id") int id){
        Optional<BurgerDTO> burger = this.burgerService.getBurger(id);

        if(burger.isPresent()){
            return new ResponseEntity<>(modelMapper.map(burger.get(),BurgerDTO.class), HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find a burger with an id of " + id, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity getOneBurger(String name){
        Optional<BurgerDTO> burger = this.burgerService.getBurger(name);

        if(burger.isPresent()){
            return new ResponseEntity<>(modelMapper.map(burger.get(),BurgerDTO.class), HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find a burger with a name of " + name, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public ResponseEntity getRandomBurger(){
        Optional<BurgerDTO> burger = this.burgerService.getRandom();

        if(burger.isPresent()){
            return new ResponseEntity<>(modelMapper.map(burger.get(),BurgerDTO.class), HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get a random burger. Try again later.", HttpStatus.NOT_FOUND);
    }
}
