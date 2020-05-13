package com.example.limechaintaskone.service;

import com.example.limechaintaskone.dto.BurgerDTO;
import com.example.limechaintaskone.model.Burger;
import com.example.limechaintaskone.model.Ingredient;
import com.example.limechaintaskone.repository.BurgerRepository;
import com.example.limechaintaskone.repository.IngredientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BurgerServiceImpl implements BurgerService, IngredientService {
    private BurgerRepository burgerRepository;
    private IngredientRepository ingredientRepository;
    private ModelMapper mapper;
    @Autowired
    public BurgerServiceImpl(BurgerRepository burgerRepository, IngredientRepository ingredientRepository, ModelMapper mapper) {
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<BurgerDTO> addBurger(String name, String ...ingredients) {
        return addBurger(name,Burger.defaultImageUrl,ingredients);
    }

    @Override
    public Optional<BurgerDTO> addBurger(String name, String imageUrl, String... ingredients) {
        if(this.getBurger(name).isPresent()) return Optional.empty();

        List<Ingredient> ingredientList = new ArrayList<Ingredient>();
        Arrays.stream(ingredients).forEach(n -> {
            if(this.getIngredientByName(n).isPresent()) ingredientList.add(this.getIngredientByName(n).get());
            else ingredientList.add(new Ingredient(n));
        });

        Burger burger = new Burger(name, ingredientList, imageUrl);

        return Optional.ofNullable(this.mapper.map(this.burgerRepository.save(burger), BurgerDTO.class));
    }

    @Override
    public Optional<BurgerDTO> getBurger(Integer id) {
        Optional<Burger> burger = this.burgerRepository.findById(id);

        return Optional.ofNullable(this.mapper.map(burger.get(),BurgerDTO.class));
    }

    @Override
    public Optional<BurgerDTO> getRandom() {
        Burger burger = this.burgerRepository.getRandomBurger();
        if(burger != null) {
            return Optional.ofNullable(this.mapper.map(burger,BurgerDTO.class));
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<BurgerDTO> getBurger(String name) {
        Optional<Burger> burger = this.burgerRepository.getBurgerByName(name);
        if(burger.isPresent())
        return Optional.of(
                this.mapper.map(
                                this.burgerRepository.getBurgerByName(name).get(),
                                BurgerDTO.class
                            ));
        else return Optional.empty();
    }

    @Override
    public List<BurgerDTO> getBurgers() {
        Page<Burger> burgers = this.burgerRepository.findAll(PageRequest.of(0,25));

        List<BurgerDTO> burgerPageResults = new ArrayList<>();

        return getBurgerDTOS(burgers, burgerPageResults);
    }

    private List<BurgerDTO> getBurgerDTOS(Page<Burger> burgers, List<BurgerDTO> burgerPageResults) {
        burgers.forEach(a->{
            burgerPageResults.add(this.mapper.map(a,BurgerDTO.class));
        });
        return burgerPageResults;
    }

    @Override
    public List<BurgerDTO> getBurgers(Integer pageNo, Integer pageSize, String sortBy) {
        Page<Burger> burgers = this.burgerRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by(sortBy)));
        return getBurgerDTOS(burgers,new ArrayList<BurgerDTO>());
    }

    @Override
    public Optional<Ingredient> getIngredientByName(String name) {
        return this.ingredientRepository.getIngredientByName(name);
    }
}
