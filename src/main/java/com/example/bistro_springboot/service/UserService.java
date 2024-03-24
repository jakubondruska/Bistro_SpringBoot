package com.example.bistro_springboot.service;

import com.example.bistro_springboot.dto.UserDto;
import com.example.bistro_springboot.model.User;

public interface UserService {

    User save(UserDto userDto);
}
