package rw.jazzybruno.istock.v1.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import rw.jazzybruno.istock.v1.models.User;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User , UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getUser_id(),
                user.getEmail(),
                user.getUsername(),
                user.getNational_id(),
                user.getRole()
        );
    }
}
