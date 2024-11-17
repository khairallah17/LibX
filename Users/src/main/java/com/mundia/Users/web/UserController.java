package com.mundia.Users.web;


import com.mundia.Users.dto.UserDTO;
import com.mundia.Users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

    @PostMapping("/add")
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        return userService.cerateUser(userDTO);
    }

    @PostMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO){
        return userService.updateUser(id,userDTO);
    }

    @GetMapping("/{id}")
    public UserDTO getUserbuId(@PathVariable Long id){
        return  userService.getUser(id);
    }

    @GetMapping()
    public List<UserDTO> getAllUsers(){
        return  userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
