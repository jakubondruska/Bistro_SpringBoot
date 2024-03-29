/*package com.example.bistro_springboot.service;

import com.example.bistro_springboot.dto.UserDto;
import com.example.bistro_springboot.model.User;
import com.example.bistro_springboot.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {

        // Set neutral role to "USER", if Admin role is needed to create, then must be created manually
        String role = (userDto.getRole() != null && !userDto.getRole().isEmpty()) ? userDto.getRole() : "USER";

        User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), role, userDto.getFullname());
        return userRepository.save(user);
    }
}
*/


