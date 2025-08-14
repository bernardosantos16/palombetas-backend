package com.palombetas.api.gerartimes.domain.repository;

import com.palombetas.api.gerartimes.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserDetails findByLogin(String login);

    Boolean existsByLogin(String login);

}
