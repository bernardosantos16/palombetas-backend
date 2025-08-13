package com.palombetas.api.gerartimes.domain.business;

import com.palombetas.api.gerartimes.domain.entity.TeamEntity;
import com.palombetas.api.gerartimes.domain.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de negócio responsável por operações de busca e validação de TeamEntity.
 * Segue o Single Responsibility Principle (SRP) - responsável apenas por operações de Team.
 */
@Service
public class TeamBusinessService {

    @Autowired
    private TeamRepository teamRepository;

    /**
     * Busca um time por ID com tratamento de exceção padronizado.
     * 
     * @param id ID do time
     * @return TeamEntity encontrada
     * @throws RuntimeException se o time não for encontrado
     */
    public TeamEntity getTeamEntityById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));
    }

    /**
     * Verifica se um time existe.
     * 
     * @param id ID do time
     * @return true se existir, false caso contrário
     */
    public boolean teamExists(Long id) {
        return teamRepository.existsById(id);
    }
}
