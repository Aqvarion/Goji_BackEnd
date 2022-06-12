package org.blackapple.backend.authorization.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.blackapple.backend.authorization.payload.JwtResponse;
import org.blackapple.backend.authorization.payload.LoginRequest;
import org.blackapple.backend.authorization.payload.MessageResponse;
import org.blackapple.backend.authorization.payload.SignUpRequest;


public interface AuthorizationService {

    MessageResponse registerUser(SignUpRequest signUpRequest);

    JwtResponse authenticateUser(LoginRequest loginRequest);

    MessageResponse changePassword(ObjectNode json);

    MessageResponse dropAccount(int accountId);

}
