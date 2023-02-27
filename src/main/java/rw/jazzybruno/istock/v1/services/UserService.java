package rw.jazzybruno.istock.v1.services;

import rw.jazzybruno.istock.v1.dto.CreateUserDTO;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<ApiResponse> getAllUsers() throws Exception;
    public ResponseEntity<ApiResponse> getUserById(Long user_id) throws Exception;
    public ResponseEntity<ApiResponse> createUser(CreateUserDTO createUserDTO) throws Exception;
}
