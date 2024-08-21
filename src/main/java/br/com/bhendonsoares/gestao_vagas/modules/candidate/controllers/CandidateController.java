package br.com.bhendonsoares.gestao_vagas.modules.candidate.controllers;

import br.com.bhendonsoares.gestao_vagas.modules.candidate.entities.Candidate;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.repository.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping
    public Candidate create(@Valid @RequestBody Candidate candidate) {
        return this.candidateRepository.save(candidate);
    }
}
