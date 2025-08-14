package com.palombetas.api.gerartimes.service;

import com.palombetas.api.gerartimes.domain.dto.request.RegisterDTO;
import com.palombetas.api.gerartimes.domain.entity.UserEntity;
import com.palombetas.api.gerartimes.domain.repository.UserRepository;
import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }

    private boolean userExists(String login) {
        return userRepository.existsByLogin(login);
    }

    @Transactional
    public void registerUser(RegisterDTO registerDTO) {
        System.out.println("Registering user: " + registerDTO.login() + " with password: " + registerDTO.password());
        if (userExists(registerDTO.login())) {
            throw new GenericCustomException(
                    "about:blank",
                    "User already exists",
                    HttpStatus.BAD_REQUEST.value(),
                    "A user with this login already exists."
            );
        }
        //var encryptedPassword = BCrypt.hashpw(registerDTO.password(), BCrypt.gensalt());
        var encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        var user = new UserEntity(registerDTO.login(), encryptedPassword);
        userRepository.save(user);
    }


}
