package com.mundia.Users.mappers;

import com.mundia.Users.dto.UserDTO;
import com.mundia.Users.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public class UserMapper {
    public UserDTO toDTo(User user){
        UserDTO userDTO= new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public User toEntity(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }
}
