package rw.jazzybruno.istock.v1.services;

import rw.jazzybruno.istock.v1.dto.User.CreateUserDTO;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<ApiResponse> getAllUsers() throws Exception;
    public ResponseEntity<ApiResponse> getUserById(Long user_id) throws Exception;
    public ResponseEntity<ApiResponse> createUser(CreateUserDTO createUserDTO) throws Exception;
    public ResponseEntity<ApiResponse> updateUser(Long user_id ,  CreateUserDTO createUserDTO) throws Exception;
    ResponseEntity<ApiResponse> deleteUser(Long user_id) throws Exception;
}
