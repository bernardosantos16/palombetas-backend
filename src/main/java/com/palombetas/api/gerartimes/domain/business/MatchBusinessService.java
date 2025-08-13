package com.palombetas.api.gerartimes.domain.business;

import com.palombetas.api.gerartimes.domain.entity.MatchEntity;
import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import com.palombetas.api.gerartimes.domain.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Classe de negócio responsável por operações de busca e validação de MatchEntity.
 * Segue o Single Responsibility Principle (SRP) - responsável apenas por operações de Match.
 */
@Service
public class MatchBusinessService {

    @Autowired
    private MatchRepository matchRepository;

    /**
     * Busca uma partida por ID com tratamento de exceção padronizado.
     * 
     * @param id ID da partida
     * @return MatchEntity encontrada
     * @throws RuntimeException se a partida não for encontrada
     */
    public MatchEntity getMatchEntityById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new GenericCustomException(
                "about:blank",
                "Match not found",
                HttpStatus.NOT_FOUND.value(),
                "Partida não encontrado com ID: " + id
        ));
    }

    /**
     * Verifica se uma partida existe.
     * 
     * @param id ID da partida
     * @return true se existir, false caso contrário
     */
    public boolean matchExists(Long id) {
        return matchRepository.existsById(id);
    }
}
