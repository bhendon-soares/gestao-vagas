package br.com.bhendonsoares.gestao_vagas.modules.candidate.controllers;

import br.com.bhendonsoares.gestao_vagas.exceptions.AlreadyExistsException;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.entities.Candidate;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Candidate candidate) {
        try {
            var result = this.createCandidateUseCase.createCandidate(candidate);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
