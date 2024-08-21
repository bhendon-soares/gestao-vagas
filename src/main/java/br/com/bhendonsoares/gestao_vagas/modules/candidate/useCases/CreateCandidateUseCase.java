package br.com.bhendonsoares.gestao_vagas.modules.candidate.useCases;

import br.com.bhendonsoares.gestao_vagas.exceptions.AlreadyExistsException;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.entities.Candidate;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate createCandidate(Candidate candidate) {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new AlreadyExistsException("Usuário já existe!");
                });
        
        return this.candidateRepository.save(candidate);
    }
}
