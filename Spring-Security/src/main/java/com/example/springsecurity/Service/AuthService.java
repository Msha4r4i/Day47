package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.Config.ConfigSecurity;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public void register (User user){
        user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    public List<User> getAllUser(){
        return authRepository.findAll();
    }


    public void upUser(Integer id ,User user){
        User oldUser = authRepository.findUserById(id);
        if (oldUser == null){
            throw new ApiException("User not found ");
        }
        oldUser.setUsername(user.getUsername());
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        String oldPassWord = bCrypt.encode(user.getPassword());
        oldUser.setPassword(oldPassWord);
        authRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = authRepository.findUserById(id);

        if (user == null){
            throw new ApiException("User not found");
        }

        authRepository.delete(user);
    }



}
