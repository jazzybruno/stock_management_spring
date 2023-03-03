package rw.jazzybruno.istock.v1.dto.User;
import javax.management.relation.Role;

public record UserDTO(
        Long userId,
        String email,
        String username,
        String national_id) {
}
