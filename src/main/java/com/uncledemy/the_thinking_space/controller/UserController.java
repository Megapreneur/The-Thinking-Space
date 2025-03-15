package com.uncledemy.the_thinking_space.controller;

import com.uncledemy.the_thinking_space.constants.StatusConstant;
import com.uncledemy.the_thinking_space.dto.ResponseDto;
import com.uncledemy.the_thinking_space.dto.UserDto;
import com.uncledemy.the_thinking_space.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(
        name = "User Controller for registration",
        description = "This class implements user's registration on the application, in order to be able to create post in the blog."
)
public class UserController {
    private final UserService userService;

    @PostMapping("/register")

    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody UserDto userDto) {
        userService.registration(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(StatusConstant.STATUS_201, StatusConstant.MESSAGE_201));
    }
}
