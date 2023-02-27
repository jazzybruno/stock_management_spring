package rw.jazzybruno.istock.v1.dto;

import rw.jazzybruno.istock.v1.models.User;

import java.util.function.Function;

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
