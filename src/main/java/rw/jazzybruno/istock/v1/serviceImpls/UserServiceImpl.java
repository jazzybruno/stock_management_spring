package rw.jazzybruno.istock.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.jazzybruno.istock.v1.dto.User.CreateUserDTO;
import rw.jazzybruno.istock.v1.dto.User.UserDTOMapper;
import rw.jazzybruno.istock.v1.models.User;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import rw.jazzybruno.istock.v1.repositories.UserRepository;
import rw.jazzybruno.istock.v1.services.UserService;
import rw.jazzybruno.istock.v1.utils.Hash;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public ResponseEntity<ApiResponse> getAllUsers() throws Exception{
      try {
          List<User> users = userRepository.findAll();
          return  ResponseEntity.status(500).body(new ApiResponse(
                  true,
                  "Successfully fetched the users",
                  users.stream().map(userDTOMapper).collect(Collectors.toList())
          ));
      }catch (Exception e){
          return ResponseEntity.status(500).body(new ApiResponse(
                  false,
                  "Failed to fetch the users"
          ));
      }
    }

    public ResponseEntity<ApiResponse> getUserById(Long user_id) throws Exception{
        if(userRepository.existsById(user_id)){
            try {
                Optional<User> user = userRepository.findById(user_id);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully fetched the users",
                        user.map(userDTOMapper)
                ));
            }catch (Exception e){
                return ResponseEntity.status(500).body(new ApiResponse(
                        false,
                        "Failed to fetch the user"
                ));
            }
        }else{
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The user with the id:" + user_id + " does not exist"
            ));
        }
    }

    public ResponseEntity<ApiResponse> createUser(CreateUserDTO createUserDTO) throws Exception{
         User user1 = userRepository.findUserByEmail(createUserDTO.getEmail()).get();
         if(user1 == null){
             User user = new User(
                     createUserDTO.getEmail(),
                     createUserDTO.getUsername(),
                     createUserDTO.getNational_id(),
                     createUserDTO.getRole(),
                     createUserDTO.getPassword()
             );

             Hash hash = new Hash();
             user.setPassword(hash.hashPassword(user.getPassword()));
             try {
                 userRepository.save(user);
                 return ResponseEntity.ok().body(new ApiResponse(
                         true,
                         "Successfully saved the user",
                         user
                 ));
             }catch (Exception e){
                 return ResponseEntity.status(500).body(new ApiResponse(
                         false,
                         "Failed to create the user"
                 ));
             }
         }else{
             return ResponseEntity.status(401).body(new ApiResponse(
                     false,
                     "The user with the email:" + createUserDTO.getEmail() + " already exists"
             ));
         }

    }

    public void updateUserMapper(Optional<User> user, CreateUserDTO createUserDTO){
        user.get().setRole(createUserDTO.getRole());
        user.get().setEmail(createUserDTO.getEmail());
        user.get().setUsername(createUserDTO.getUsername());
        user.get().setNational_id(createUserDTO.getNational_id());
        user.get().setPassword(createUserDTO.getPassword());
    }

    @Transactional
    public ResponseEntity<ApiResponse> updateUser(Long user_id ,  CreateUserDTO createUserDTO) throws Exception {
        if (userRepository.existsById(user_id)) {
            Optional<User> user = userRepository.findById(user_id);
            updateUserMapper(user, createUserDTO);
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully updated the user",
                    user.map(userDTOMapper)
            ));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The user with the id:" + user_id + " does not exist"
            ));
        }
    }

    public ResponseEntity<ApiResponse> deleteUser(Long user_id) throws Exception{
        if (userRepository.existsById(user_id)) {
            Optional<User> user = userRepository.findById(user_id);
            userRepository.deleteById(user_id);
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully deleted the user",
                    user.map(userDTOMapper)
            ));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The user with the id:" + user_id + " does not exist"
            ));
        }
    }
}
