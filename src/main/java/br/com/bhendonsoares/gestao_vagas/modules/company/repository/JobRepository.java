package br.com.bhendonsoares.gestao_vagas.modules.company.repository;

import br.com.bhendonsoares.gestao_vagas.modules.company.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
}
