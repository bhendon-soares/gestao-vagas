package br.com.bhendonsoares.gestao_vagas.modules.company.controllers;

import br.com.bhendonsoares.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.bhendonsoares.gestao_vagas.modules.company.entities.Job;
import br.com.bhendonsoares.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public Job createJob(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

//        job.setCompanyId(UUID.fromString(companyId.toString()));
        var job = Job.builder()
                .description(createJobDTO.description())
                .benefits(createJobDTO.benefits())
                .level(createJobDTO.level())
                .companyId(UUID.fromString(companyId.toString())).build();

        return this.createJobUseCase.createJob(job);
    }
}
