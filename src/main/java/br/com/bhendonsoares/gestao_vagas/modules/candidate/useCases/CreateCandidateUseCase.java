package br.com.bhendonsoares.gestao_vagas.modules.candidate.useCases;

import br.com.bhendonsoares.gestao_vagas.exceptions.AlreadyExistsException;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.entities.Candidate;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Candidate createCandidate(Candidate candidate) {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new AlreadyExistsException("Usuário já existe!");
                });

        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return this.candidateRepository.save(candidate);
    }
}
