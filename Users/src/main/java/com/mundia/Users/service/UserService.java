package com.mundia.Users.service;

import com.mundia.Users.dto.UserDTO;
import com.mundia.Users.entity.User;

import java.util.List;

public interface UserService {
    UserDTO cerateUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    UserDTO getUser(Long id);
    List<UserDTO> getAllUser();
    void deleteUser(Long id);
}
