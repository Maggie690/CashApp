package com.project.secure.capita.service;

import com.project.secure.capita.domain.User;
import com.project.secure.capita.dto.UserDTO;
import com.project.secure.capita.exception.ApiException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO create(User user) throws ApiException;
}
