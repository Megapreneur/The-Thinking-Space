package com.uncledemy.the_thinking_space.service;


import com.uncledemy.the_thinking_space.dto.UserDto;
import com.uncledemy.the_thinking_space.model.User;

public interface UserService {
    void registration(UserDto userDto);
    User loadUser(String username);
    

}
