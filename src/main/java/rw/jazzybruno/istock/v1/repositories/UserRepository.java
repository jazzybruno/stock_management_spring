package rw.jazzybruno.istock.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.jazzybruno.istock.v1.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findUserByEmail(String email);
}
