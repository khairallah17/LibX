package com.mundia.Users.service;

import com.mundia.Users.dto.UserDTO;
import com.mundia.Users.entity.User;
import com.mundia.Users.mappers.UserMapper;
import com.mundia.Users.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO cerateUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User saveUser = userRepository.save(user);
        return userMapper.toDTo(saveUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setAdress(user.getAdress());

            User updateuser = userRepository.save(user);
            return userMapper.toDTo(updateuser);
        }else{
            throw new RuntimeException("this user not found");
        }
    }

    @Override
    public UserDTO getUser(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("this user not found"));
        return userMapper.toDTo(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTo).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("this book with id "+id+" is not existe"));
        userRepository.delete(user);
    }
}
