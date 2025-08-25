package com.example.springsecurity.Controller;

import com.example.springsecurity.Api.ApiResponse;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.AuthService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("User registered success"));
    }
    @GetMapping("/get")
    public ResponseEntity<?> getALlUSer(){
        return ResponseEntity.status(200).body(authService.getAllUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> upUser(@PathVariable Integer id , @RequestBody @Valid User user){
        authService.upUser(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("done"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id ){
        authService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
    }

}
