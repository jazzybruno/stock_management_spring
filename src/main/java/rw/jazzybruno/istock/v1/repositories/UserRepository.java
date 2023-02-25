package rw.jazzybruno.istock.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.jazzybruno.istock.v1.models.User;

public interface UserRepository extends JpaRepository<User , Long> {
}
