package br.com.bhendonsoares.gestao_vagas.modules.candidate.useCases;

import br.com.bhendonsoares.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.bhendonsoares.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(() -> {
           throw new UsernameNotFoundException("User not found!");
        });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .id(candidate.getUuid())
                .username(candidate.getUsername())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .build();
        return candidateDTO;
    }
}
