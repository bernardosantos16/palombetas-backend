package com.palombetas.api.gerartimes.controller;


import com.palombetas.api.gerartimes.domain.dto.request.AuthDTO;
import com.palombetas.api.gerartimes.domain.dto.request.RegisterDTO;
import com.palombetas.api.gerartimes.domain.dto.response.JWTResponseDTO;
import com.palombetas.api.gerartimes.domain.entity.UserEntity;
import com.palombetas.api.gerartimes.infra.security.TokenService;
import com.palombetas.api.gerartimes.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        authenticationService.registerUser(registerDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponseDTO> login(@RequestBody @Valid AuthDTO authDTO) {
        var autenticationToken = new UsernamePasswordAuthenticationToken(
                authDTO.login(),
                authDTO.password()
        );
        var auth = authenticationManager.authenticate(autenticationToken);
        var jwt = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new JWTResponseDTO(jwt));
    }
}
