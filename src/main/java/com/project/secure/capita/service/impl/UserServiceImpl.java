package com.project.secure.capita.service.impl;

import com.project.secure.capita.domain.User;
import com.project.secure.capita.dto.UserDTO;
import com.project.secure.capita.dto.UserDtoMapper;
import com.project.secure.capita.exception.ApiException;
import com.project.secure.capita.repository.UserRepository;
import com.project.secure.capita.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO create(User user) throws ApiException {
        return UserDtoMapper.fromUser(userRepository.create(user));
    }
}
