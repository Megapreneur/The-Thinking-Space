package com.uncledemy.the_thinking_space.service;


import com.uncledemy.the_thinking_space.dto.AuthenticationResponse;
import com.uncledemy.the_thinking_space.dto.LoginDto;
import com.uncledemy.the_thinking_space.exception.InvalidPasswordException;
import com.uncledemy.the_thinking_space.exception.UserNotFoundException;
import com.uncledemy.the_thinking_space.model.User;
import com.uncledemy.the_thinking_space.repo.UserRepository;
import com.uncledemy.the_thinking_space.security.JwtService;
import com.uncledemy.the_thinking_space.security.config.SecureUser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public AuthenticationResponse login(LoginDto loginDto) {
        Optional<User> savedUser = userRepository.findUserByEmail(loginDto.getEmail().toLowerCase());
        if (savedUser.isPresent()){
                try {
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginDto.getEmail().toLowerCase(), loginDto.getPassword())
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (BadCredentialsException e) {
                    logger.info("Authentication failed for : {}", loginDto.getEmail());
                    throw new InvalidPasswordException(e.getLocalizedMessage());
                }
                User foundUser = userRepository.findUserByEmail(loginDto.getEmail().toLowerCase()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
                SecureUser user = new SecureUser(foundUser);
                String jwtToken = jwtService.generateToken(user);
            logger.info("Authentication was successful for : {}", loginDto.getEmail());
                return AuthenticationResponse.of(jwtToken, user.getUserId());
        }
        throw new UserNotFoundException("User not found!!!");
    }
}
