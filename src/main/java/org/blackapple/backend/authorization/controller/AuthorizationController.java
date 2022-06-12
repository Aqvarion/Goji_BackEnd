package org.blackapple.backend.authorization.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.blackapple.backend.authorization.payload.JwtResponse;
import org.blackapple.backend.authorization.payload.LoginRequest;
import org.blackapple.backend.authorization.payload.MessageResponse;
import org.blackapple.backend.authorization.payload.SignUpRequest;
import org.blackapple.backend.authorization.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
@RequestMapping(value = "/auth")
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

    @PostMapping("/signUp")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authorizationService.registerUser(signUpRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser (@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authorizationService.authenticateUser(loginRequest));
    }

    @PostMapping("/dropPassword")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody ObjectNode json) {
        return ResponseEntity.ok(authorizationService.changePassword(json));
    }


    @PostMapping("/dropAccount")
    public ResponseEntity<MessageResponse> dropAccount(@RequestParam int accountId){
        return ResponseEntity.ok(authorizationService.dropAccount(accountId));
    }
}
