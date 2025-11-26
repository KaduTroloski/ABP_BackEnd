package com.cinegraudos.controller;

import com.cinegraudos.repository.FilmesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmesController {

    private final FilmesRepository FilmesRepository;

    @GetMapping
    public Object listar() {
        return FilmesRepository.findAll();
    }
}