package br.com.bhendonsoares.gestao_vagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import br.com.bhendonsoares.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.bhendonsoares.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.bhendonsoares.gestao_vagas.modules.company.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO authenticate(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(company.getUuid().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponse = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCompanyResponse;
    }
}
