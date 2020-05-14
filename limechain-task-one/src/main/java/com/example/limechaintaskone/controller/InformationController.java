package com.example.limechaintaskone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class InformationController {
    @RequestMapping
    public ResponseEntity getInformation(){
        return new ResponseEntity(
        "<p>You can play with the following options<br>" +
                "/burgers<br>" +
                "with optional parameters for name, page(starting from zero), size.<br>" +
                "Also you can try to get a random burger with its ingredients at /burgers/random.<br>" +
                "There is a burger addition function which accepts name, imageUrl and ingredients.<br>" +
                "The ingredients parameter should be a list of items, separated by a comma and(or) spaces</p>" +
                "<h1>Have fun!!</h1>", HttpStatus.OK);
    }
}
