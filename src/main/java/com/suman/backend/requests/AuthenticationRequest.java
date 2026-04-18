package com.suman.backend.requests;

public record AuthenticationRequest(
        String email,

        String password
) {

}
