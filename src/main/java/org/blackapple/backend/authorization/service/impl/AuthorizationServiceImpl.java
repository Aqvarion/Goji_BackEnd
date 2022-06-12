package org.blackapple.backend.authorization.service.impl;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.blackapple.backend.authorization.model.Account;
import org.blackapple.backend.authorization.payload.JwtResponse;
import org.blackapple.backend.authorization.payload.LoginRequest;
import org.blackapple.backend.authorization.payload.MessageResponse;
import org.blackapple.backend.authorization.payload.SignUpRequest;
import org.blackapple.backend.authorization.repository.AccountRepository;
import org.blackapple.backend.authorization.repository.RoleRepository;
import org.blackapple.backend.authorization.sequrity.jwt.JWTUtils;
import org.blackapple.backend.authorization.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTUtils jwtUtils;

    @Override
    public MessageResponse registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        // проверка на уникальность имени пользователя или почты
        if(accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }
        if (accountRepository.existsByEmail(signUpRequest.getEmail())){
            return new MessageResponse("Error: Email is already taken!");
        }

        // создание нового пользователя
        Account user = new Account(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );

        accountRepository.save(user);
        return new MessageResponse("User registered successfully!");
    }

    @Override
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public MessageResponse changePassword(@RequestBody ObjectNode json) {
        int userId = json.get("accountId").asInt();
        if (!accountRepository.existsById(userId)) {
            return new MessageResponse("Error: Account with this id not exist!");
        }
        String newPassword = json.get("newPassword").asText();
        accountRepository.changePassword(userId, passwordEncoder.encode(newPassword));
        return new MessageResponse("Succes: password was changed!");
    }

    @Override
    public MessageResponse dropAccount(int accountId){
        if (!accountRepository.existsById(accountId)) {
            return new MessageResponse("Error: Account with this id not exist!");
        }
        accountRepository.deleteById(accountId);
        return new MessageResponse("Success: account dropping!");
    }
}
