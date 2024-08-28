package br.com.bhendonsoares.gestao_vagas.modules.company.useCases;

import br.com.bhendonsoares.gestao_vagas.exceptions.AlreadyExistsException;
import br.com.bhendonsoares.gestao_vagas.modules.company.entities.Company;
import br.com.bhendonsoares.gestao_vagas.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Company createCompany(Company company) {
        this.companyRepository
                .findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((user) -> {
                    throw new AlreadyExistsException("Empresa já existe!");
                });

        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);

        return this.companyRepository.save(company);
    }
}
