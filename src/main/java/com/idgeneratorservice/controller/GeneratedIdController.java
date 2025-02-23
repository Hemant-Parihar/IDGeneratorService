package com.idgeneratorservice.controller;

import com.idgeneratorservice.entity.GeneratedId;
import com.idgeneratorservice.repository.GeneratedIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/unique-ids")
public class GeneratedIdController {

    private final GeneratedIdRepository generatedIdRepository;

    @Autowired
    public GeneratedIdController(GeneratedIdRepository generatedIdRepository) {
        this.generatedIdRepository = generatedIdRepository;
    }

    @PostMapping("/generate")
    public ResponseEntity<GeneratedId> generateUniqueId() {
        GeneratedId generatedId = new GeneratedId();
        generatedId = generatedIdRepository.save(generatedId);
        return ResponseEntity.ok(generatedId);
    }

}
