package br.com.bhendonsoares.gestao_vagas.modules.candidate.repository;

import br.com.bhendonsoares.gestao_vagas.modules.candidate.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
}
