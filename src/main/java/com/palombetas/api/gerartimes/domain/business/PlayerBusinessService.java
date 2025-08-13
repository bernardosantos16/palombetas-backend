package com.palombetas.api.gerartimes.domain.business;

import com.palombetas.api.gerartimes.domain.entity.PlayerEntity;
import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import com.palombetas.api.gerartimes.domain.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe de negócio responsável por operações de busca e validação de PlayerEntity.
 * Segue o Single Responsibility Principle (SRP) - responsável apenas por operações de Player.
 */
@Service
public class PlayerBusinessService {

    @Autowired
    private PlayerRepository playerRepository;

    /**
     * Busca um jogador por ID com tratamento de exceção padronizado.
     * 
     * @param id ID do jogador
     * @return PlayerEntity encontrado
     * @throws RuntimeException se o jogador não for encontrado
     */
    public PlayerEntity getPlayerEntityById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new GenericCustomException(
                        "about:blank",
                        "Player not found",
                        HttpStatus.NOT_FOUND.value(),
                        "Jogador não encontrado com ID: " + id
                ));
    }

    /**
     * Verifica se um jogador existe.
     * 
     * @param id ID do jogador
     * @return true se existir, false caso contrário
     */
    public boolean playerExists(Long id) {
        return playerRepository.existsById(id);
    }

    /**
     * Busca uma lista de jogadores pelos IDs.
     * 
     * @param playersIds Lista de IDs dos jogadores
     * @return Lista de PlayerEntity
     */
    public List<PlayerEntity> getListOfPlayersByIds(List<Long> playersIds) {
        return playerRepository.findAllById(playersIds);
    }

    /**
     * Busca todos os jogadores ativos.
     * 
     * @return Lista de PlayerEntity ativos
     */
    public List<PlayerEntity> getAllActivePlayers() {
        return playerRepository.findAllByIsActiveTrue();
    }

    /**
     * Verifica se jogador está ativo
     *
     * @return Boolean de jogador ativo
     */
//    public boolean playerIsActive(Long id){
//        return playerRepository.existsByIdAndActiveTrue(id);
//    }

}
