package com.uncledemy.the_thinking_space.service;


import com.uncledemy.the_thinking_space.dto.AuthenticationResponse;
import com.uncledemy.the_thinking_space.dto.LoginDto;

public interface AuthService {
    AuthenticationResponse login(LoginDto loginDto);
}
