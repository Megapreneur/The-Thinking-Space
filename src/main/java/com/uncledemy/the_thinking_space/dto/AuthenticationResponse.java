package com.uncledemy.the_thinking_space.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String accessToken;
    private long userId;

    public static AuthenticationResponse of(String jwtToken, long userId){
        return new AuthenticationResponse(jwtToken,userId);
    }
}
