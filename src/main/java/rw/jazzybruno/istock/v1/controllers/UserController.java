package rw.jazzybruno.istock.v1.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.jazzybruno.istock.v1.serviceImpls.UserServiceImpl;

import java.security.PublicKey;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers() throws Exception{
        return userService.getAllUsers();
    }

    @GetMapping("/id/{user_id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long user_id) throws Exception{
        return userService.getUserById(user_id);
    }
}
