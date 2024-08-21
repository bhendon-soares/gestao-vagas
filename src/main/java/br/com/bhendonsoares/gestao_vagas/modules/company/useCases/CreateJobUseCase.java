package br.com.bhendonsoares.gestao_vagas.modules.company.useCases;

import br.com.bhendonsoares.gestao_vagas.modules.company.entities.Job;
import br.com.bhendonsoares.gestao_vagas.modules.company.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        return this.jobRepository.save(job);
    }
}
