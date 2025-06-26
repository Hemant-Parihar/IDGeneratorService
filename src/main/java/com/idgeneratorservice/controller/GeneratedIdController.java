package com.idgeneratorservice.controller;

import com.idgeneratorservice.entity.GeneratedId;
import com.idgeneratorservice.repository.GeneratedIdRepository;
import com.idgeneratorservice.service.IdGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/unique-ids")
public class GeneratedIdController {

    private final GeneratedIdRepository generatedIdRepository;
    private final IdGenerationService idGenerationService;

    @Autowired
    public GeneratedIdController(GeneratedIdRepository generatedIdRepository, IdGenerationService idGenerationService) {
        this.generatedIdRepository = generatedIdRepository;
        this.idGenerationService = idGenerationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<GeneratedId> generateUniqueId(
            @RequestParam(defaultValue = "UUID") String strategy) {
        String generatedId = idGenerationService.generateId(strategy);
        GeneratedId idEntity = new GeneratedId(generatedId, strategy);
        idEntity = generatedIdRepository.save(idEntity);
        return ResponseEntity.ok(idEntity);
    }

    @GetMapping("/strategies")
    public ResponseEntity<List<String>> getAvailableStrategies() {
        return ResponseEntity.ok(idGenerationService.getAvailableStrategies());
    }
}
