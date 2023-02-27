package rw.jazzybruno.istock.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rw.jazzybruno.istock.v1.dto.UserDTOMapper;
import rw.jazzybruno.istock.v1.models.User;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import rw.jazzybruno.istock.v1.repositories.UserRepository;
import rw.jazzybruno.istock.v1.services.UserService;

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
}
