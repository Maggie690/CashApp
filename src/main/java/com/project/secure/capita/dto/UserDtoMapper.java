package com.project.secure.capita.dto;

import com.project.secure.capita.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

//TODO: generic
@Component
public class UserDtoMapper {

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }

    public static User fromUserDto(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);

        return user;
    }
}
