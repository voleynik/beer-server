package com.example.beer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BeerController {

    private BeerRepository repository;

    public BeerController(BeerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/good-beers")
    @CrossOrigin(origins = "http://localhost:4200")
    Collection<Map<String, String>> list(){
        return repository.findAll().stream().filter(this::isGreat)
                .map(b -> {
                    Map<String, String> m = new HashMap<>();
                    m.put("id", b.getId().toString());
                    m.put("name", b.getName());
                    return m;
                }).collect(Collectors.toList());
    }

    private boolean isGreat(Beer beer){
        return  ! beer.getName().equals("Coors") &&
                ! beer.getName().equals("Bud Lite");
    }
}
